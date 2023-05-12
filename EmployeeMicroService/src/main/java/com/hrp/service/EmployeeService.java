package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.response.GetAllEmployeeResponseDto;
import com.hrp.mapper.IEmployeeMapper;
import com.hrp.rabbitmq.model.ModelSendToEmployeeMs;
import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.CodeGenerator;
import com.hrp.utility.ServiceManagerImpl;
import com.hrp.utility.StaticValues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends ServiceManagerImpl<Employee,String> {

    private final IEmployeeRepository employeeRepository;
    public EmployeeService(IEmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }


    public List<GetAllEmployeeResponseDto> findAllEmployee() {
        return findAll().stream()
                .map(x-> IEmployeeMapper.INSTANCE.toGetAllEmployeeResponseDto(x))
                .collect(Collectors.toList());
    }
    @Transactional
    public Boolean createEmployee(ModelSendToEmployeeMs model) {
        System.out.println(model.toString());
        String avatarUrl = toTurnStringAvatar(model.getAvatar());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Iscisin sen isci kal!");
        save(Employee.builder()
                .identityNumber(model.getIdentityNumber())
                .name(model.getName())
                .middleName(model.getMiddleName())
                .surname(model.getSurname())
                .dateOfBirth(model.getDateOfBirth())
                .placeOfBirth(model.getPlaceOfBirth())
                .email(model.getEmail())
                .address(model.getAddress())
                .phone(model.getPhone())
                .company(model.getCompany())
                .job(model.getJob())
                .department(model.getDepartment())
                .jobStart(model.getJobStart())
                .authId(StaticValues.authId)
                .avatar(avatarUrl)
                .build());
        return true;
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
}
