package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.TokenDto;
import com.hrp.dto.request.UpdateManagerNoPhotoRequestDto;
import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.BaseManagerResponseDto;
import com.hrp.dto.response.EmployeeRequestAndResponseDto;
import com.hrp.exception.ManagerException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IManuelManagerMapper;
import com.hrp.rabbitmq.model.ModelBaseEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IManagerRepository;
import com.hrp.repository.entity.Manager;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import com.hrp.utility.StaticValues;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManagerService extends ServiceManagerImpl<Manager, Long>{
    private final IManagerRepository managerRepository;
    private final DirectProducer directProducer;
    private final JwtTokenManager jwtTokenManager;
    private final IManuelManagerMapper  iManuelManagerMapper;

    public ManagerService(IManagerRepository managerRepository, DirectProducer directProducer, JwtTokenManager jwtTokenManager, IManuelManagerMapper iManuelManagerMapper) {
        super(managerRepository);
        this.managerRepository = managerRepository;
        this.directProducer = directProducer;
        this.jwtTokenManager = jwtTokenManager;
        this.iManuelManagerMapper = iManuelManagerMapper;
    }

    public Boolean createManager(ModelRegisterManager model) {
        Manager manager = iManuelManagerMapper.toManager(model);
        manager.setAvatar(model.getAvatar());
        save(manager);
        return true;
    }

    public List<BaseManagerResponseDto> findAllManager(TokenDto dto) {
        Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        if(dto.getRole().equals("MANAGER")){
            Manager manager = managerRepository.findOptionalByAuthId(id.get()).get();
            return findAll().stream().filter(x->x.getCompany().equals(manager.getCompany())).
                    map(x-> iManuelManagerMapper
                            .toBaseManagerResponseDto(x)).collect(Collectors.toList());
        }else{
            return findAll().stream().
                    map(x-> iManuelManagerMapper
                            .toBaseManagerResponseDto(x)).collect(Collectors.toList());
        }
    }

    /**
     * update metoduna bakalıcak.
     */
    public Boolean updateManager(UpdateManagerRequestDto dto) {
        Optional<Long> companyManagerId=jwtTokenManager.validToken(dto.getToken());
        if (companyManagerId.isEmpty()){
            throw new ManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Manager> manager = managerRepository.findOptionalByAuthId(companyManagerId.get());
        if (companyManagerId.isEmpty()){
            throw new ManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);
        }

        String url = toTurnStringAvatar(dto.getAvatar());
         manager.get().setAvatar(url);
        update(iManuelManagerMapper.toManager(manager.get(),dto));
        return true;
    }


    public Boolean updateManagerNoPhoto(UpdateManagerNoPhotoRequestDto dto) {
        Optional<Long> companyManagerId=jwtTokenManager.validToken(dto.getToken());
        if (companyManagerId.isEmpty()){
            throw new ManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Manager> manager = managerRepository.findOptionalByAuthId(companyManagerId.get());
        if (companyManagerId.isEmpty()){
            throw new ManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);
        }

        update(iManuelManagerMapper.toManager(manager.get(),dto));
        return true;
    }

    public Boolean deleteManager(TokenDto dto) {
        Optional<Long> companyManagerId=jwtTokenManager.validToken(dto.getToken());
        if (companyManagerId.isEmpty()){
            throw new ManagerException(EErrorType.INVALID_TOKEN);
        }
        deleteById(companyManagerId.get());
        return true;
    }

    public BaseManagerResponseDto findMe(TokenDto dto) {
        Long id = jwtTokenManager.validToken(dto.getToken()).get();
        if (id==null){
            throw new ManagerException(EErrorType.INVALID_TOKEN);
        }
        Manager manager = managerRepository.findOptionalByAuthId(id).get();
        if (manager==null){
            throw new ManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);
        }
        return iManuelManagerMapper.toBaseManagerResponseDto(manager);
    }

    private String toTurnStringAvatar(MultipartFile avatar) {
        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url+" --------------------------");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public List<EmployeeRequestAndResponseDto> findAllMyEmployee(TokenDto dto) {
        Optional<Long> authId= jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()){
            throw new ManagerException(EErrorType.INVALID_TOKEN);
        }
        System.out.println("serviste 113");
        Optional<Manager> manager= managerRepository.findOptionalByAuthId(authId.get());
        if (manager.isEmpty()){
            throw new ManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);
        }
        directProducer.sendFindAllMyEmployee(iManuelManagerMapper.toModelBaseEmployee(manager.get()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<ModelBaseEmployee> employees = StaticValues.modelBaseEmployees;
        List<EmployeeRequestAndResponseDto> dtos= new ArrayList<>();
        for (ModelBaseEmployee model: employees) {
            dtos.add(iManuelManagerMapper.toEmployeeDto(model));
        }
        return dtos;
    }





}