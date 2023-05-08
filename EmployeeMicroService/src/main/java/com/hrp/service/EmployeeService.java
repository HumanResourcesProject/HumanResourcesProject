package com.hrp.service;

import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends ServiceManagerImpl<Employee,String> {

    private final IEmployeeRepository employeeRepository;
    public EmployeeService(IEmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }




}
