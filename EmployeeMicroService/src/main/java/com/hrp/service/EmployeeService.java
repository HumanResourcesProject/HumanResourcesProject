package com.hrp.service;

import com.hrp.dto.response.GetAllEmployeeResponseDto;
import com.hrp.mapper.IEmployeeMapper;
import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
