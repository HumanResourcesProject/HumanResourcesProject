package com.hrp.mapper;

import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.repository.entity.Employee;

public interface IManuelEmployeeMapper {
     Employee modelToEmployee(ModelRegisterEmployee model);
     BaseEmployeeResponseDto toBaseEmployeeDto(Employee employee);

    }
