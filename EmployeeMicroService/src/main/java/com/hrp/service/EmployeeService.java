package com.hrp.service;

import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.mapper.IEmployeeMapper;
import com.hrp.mapper.IManuelEmployeeMapper;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends ServiceManagerImpl<Employee,String> {

    private final IEmployeeRepository employeeRepository;
    private final IManuelEmployeeMapper iManuelEmployeeMapper;
    public EmployeeService(IEmployeeRepository employeeRepository, IManuelEmployeeMapper iManuelEmployeeMapper) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
        this.iManuelEmployeeMapper = iManuelEmployeeMapper;
    }

    public void createEmployee(ModelRegisterEmployee model){
        Employee employee= iManuelEmployeeMapper.modelToEmployee(model);
        employee.setAvatar("https://gcavocats.ca/wp-content/uploads/2018/09/man-avatar-icon-flat-vector-19152370-1.jpg");
        save(employee);
    }


    public List<BaseEmployeeResponseDto> findAllEmployee() {
        return findAll().stream()
                .map(x-> iManuelEmployeeMapper.toBaseEmployeeDto(x))
                .collect(Collectors.toList());
    }
}
