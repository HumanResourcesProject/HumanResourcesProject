package com.hrp.mapper;

import com.hrp.dto.request.EmployeeUpdateRequestDto;
import com.hrp.dto.request.requirements.ExpenseRequestDto;
import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class ManuelEmployeeMapper implements IManuelEmployeeMapper {

    public Employee modelToEmployee(ModelRegisterEmployee model){
        return Employee.builder()
                .authId(model.getAuthId())
                .identityNumber(model.getIdentityNumber())
                .name(model.getName())
                .middleName(model.getMiddleName())
                .surname(model.getSurname())
                .birthDate(model.getBirthDate())
                .birthPlace(model.getBirthPlace())
                .jobStart(model.getJobStart())
                .occupation(model.getOccupation())
                .department(model.getDepartment())
                .email(model.getEmail())
                .phone(model.getPhone())
                .address(model.getAddress())
                .company(model.getCompany())
                .build();
    }

    @Override
    public BaseEmployeeResponseDto toBaseEmployeeDto(Employee employee) {
        BaseEmployeeResponseDto baseEmployeeResponseDto = new BaseEmployeeResponseDto();
        baseEmployeeResponseDto.setName(employee.getName());
        baseEmployeeResponseDto.setMiddleName(employee.getMiddleName());
        baseEmployeeResponseDto.setSurname(employee.getSurname());
        baseEmployeeResponseDto.setBirthDate(employee.getBirthDate());
        baseEmployeeResponseDto.setBirthPlace(employee.getBirthPlace());
        baseEmployeeResponseDto.setIdentityNumber(employee.getIdentityNumber());
        baseEmployeeResponseDto.setOccupation(employee.getOccupation());
        baseEmployeeResponseDto.setDepartment(employee.getDepartment());
        baseEmployeeResponseDto.setEmail(employee.getEmail());
        baseEmployeeResponseDto.setSalary(employee.getSalary());
        baseEmployeeResponseDto.setAddress(employee.getAddress());
        baseEmployeeResponseDto.setPhone(employee.getPhone());
        baseEmployeeResponseDto.setCompany(employee.getCompany());
        baseEmployeeResponseDto.setAvatar(employee.getAvatar());
        return baseEmployeeResponseDto;
    }

    @Override
    public ModelEmployeeAdvancePaymentRequest toEmployeeAdvancePaymentModel(Employee employee,AdvancePaymentRequestDto dto) {
        ModelEmployeeAdvancePaymentRequest model = new ModelEmployeeAdvancePaymentRequest();
        model.setEmployeeId(employee.getId());
        model.setAmount(dto.getAmount());
        model.setCompany(employee.getCompany());
        model.setComment(dto.getComment());
        model.setCurrency(dto.getCurrency());
        model.setAdvancedPaymentDate(dto.getAdvancedPaymentDate());
        return model;
    }

    @Override
    public ModelEmployeeLeave toEmployeeLeaveModel(Employee employee, LeaveRequestDto dto) {
        ModelEmployeeLeave modelEmployeeLeave= new ModelEmployeeLeave();
        modelEmployeeLeave.setType(dto.getType());
        modelEmployeeLeave.setFinishDate(dto.getFinishDate());
        modelEmployeeLeave.setStartDate(dto.getStartDate());
        modelEmployeeLeave.setEmployeeId(employee.getId());
        modelEmployeeLeave.setCompany(employee.getCompany());
        return modelEmployeeLeave;
    }

    @Override
    public ModelEmployeeExpense toEmployeeExpenseModel(Employee employee, ExpenseRequestDto dto) {
        ModelEmployeeExpense modelEmployeeExpense = new ModelEmployeeExpense();
        modelEmployeeExpense.setAmount(dto.getAmount());
        modelEmployeeExpense.setCurrency(dto.getCurrency());
        modelEmployeeExpense.setType(dto.getType());
        modelEmployeeExpense.setRequestDate(dto.getRequestDate());
        //modelEmployeeExpense.setInvoiceUrl(dto.getInvoiceUrl());
        modelEmployeeExpense.setSpendingDate(dto.getSpendingDate());
        modelEmployeeExpense.setRequestDate(dto.getRequestDate());
        modelEmployeeExpense.setComment(dto.getComment());
        modelEmployeeExpense.setEmployeeId(employee.getId());
        modelEmployeeExpense.setCompany(employee.getCompany());

        return modelEmployeeExpense;
    }

    @Override
    public Employee toEmployee(Employee employee,EmployeeUpdateRequestDto dto) {
        employee.setAddress(dto.getAddress());
        employee.setPhone(dto.getPhone());
        return employee;
    }
}
