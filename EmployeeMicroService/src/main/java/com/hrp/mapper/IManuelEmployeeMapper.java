package com.hrp.mapper;

import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.entity.Employee;

public interface IManuelEmployeeMapper {
     Employee modelToEmployee(ModelRegisterEmployee model);
     BaseEmployeeResponseDto toBaseEmployeeDto(Employee employee);
     ModelEmployeeAdvancePaymentRequest toEmployeeAdvancePaymentModel(Employee employee,AdvancePaymentRequestDto dto) ;
     ModelEmployeeLeave  toEmployeeLeaveModel(Employee employee, LeaveRequestDto dto);

    }
