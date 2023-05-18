package com.hrp.mapper;

import com.hrp.dto.request.EmployeeUpdateNoPhotoRequestDto;
import com.hrp.dto.request.EmployeeUpdateRequestDto;
import com.hrp.dto.request.requirements.ExpenseRequestDto;
import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.rabbitmq.model.*;
import com.hrp.repository.entity.Employee;

public interface IManuelEmployeeMapper {
     Employee ToEmployee(ModelRegisterEmployee model);
     BaseEmployeeResponseDto toBaseEmployeeDto(Employee employee);
     ModelEmployeeAdvancePaymentRequest toEmployeeAdvancePaymentModel(Employee employee,AdvancePaymentRequestDto dto ) ;
     ModelEmployeeLeave  toEmployeeLeaveModel(Employee employee, LeaveRequestDto dto );

    ModelEmployeeExpense toEmployeeExpenseModel(Employee employee, ExpenseRequestDto dto);
    Employee toEmployee(Employee employee,EmployeeUpdateRequestDto dto);
    ModelBaseEmployee toModel(Employee employee);
    Employee toEmployee(Employee employee,EmployeeUpdateNoPhotoRequestDto dto);

}
