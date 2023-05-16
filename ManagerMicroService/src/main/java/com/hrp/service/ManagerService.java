package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.TokenDto;
import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.AllLeaveFormResponseDto;
import com.hrp.dto.response.BaseManagerResponseDto;
import com.hrp.exception.ManagerException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IManuelManagerMapper;
import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IManagerRepository;
import com.hrp.repository.entity.Manager;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import com.hrp.utility.StaticValues;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        System.out.println("asdsadas");
        Manager manager = iManuelManagerMapper.toManager(model);
        manager.setAvatar("https://images.unsplash.com/photo-1534030347209-467a5b0ad3e6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80");
        save(manager);
        return true;
    }

    public List<BaseManagerResponseDto> findAllManager(TokenDto dto) {
        Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        System.out.println("gelen id: find all manager da : "+ id);
        if(dto.getRole().equals("MANAGER")){
            System.out.println("findall manager if den sonra");
            Manager manager = managerRepository.findOptionalByAuthId(id.get()).get();
            return findAll().stream().filter(x->x.getCompany()==manager.getCompany()).
                    map(x-> iManuelManagerMapper
                            .toBaseManagerResponseDto(x)).collect(Collectors.toList());
        }else{
            return findAll().stream().
                    map(x-> iManuelManagerMapper
                            .toBaseManagerResponseDto(x)).collect(Collectors.toList());
        }
    }


    public Boolean updateManager(UpdateManagerRequestDto dto) {
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
        deleteById(companyManagerId.get());
        return true;
    }

    public BaseManagerResponseDto findMe(TokenDto dto) {
        Long id = jwtTokenManager.validToken(dto.getToken()).get();
        Manager manager = managerRepository.findOptionalByAuthId(id).get();
        return iManuelManagerMapper.toBaseManagerResponseDto(manager);
    }

    public String updateImage(MultipartFile file, String token) {
        Long id = jwtTokenManager.validToken(token).get();
        Optional<Manager> admin = managerRepository.findById(id);
        String url = toTurnStringAvatar(file);
       return url;
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

    public List<ModelTurnAllLeaveRequest> findAllLeave(TokenDto dto) {
        Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        System.out.println("findall leave de auth id: "+id);
        Optional<Manager> manager = managerRepository.findOptionalByAuthId(id.get());
        System.out.println("bulma isleminden sonra");
        directProducer.sendfindAllLeave(ModelBaseRequirmentFindAll.builder()
                        .company(manager.get().getCompany())
                .build());
        System.out.println("threadden önce");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return StaticValues.findAllLeave;
    }
}