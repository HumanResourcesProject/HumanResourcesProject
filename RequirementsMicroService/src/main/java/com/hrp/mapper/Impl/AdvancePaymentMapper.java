package com.hrp.mapper.Impl;


import com.hrp.dto.response.BaseAdvancePaymentResponseDto;
import com.hrp.mapper.IAdvancePaymentMapper;
import com.hrp.rabbitmq.model.*;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.repository.entity.enums.ExpenseCurrency;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdvancePaymentMapper implements IAdvancePaymentMapper {


    @Override
    public AdvancedPayment toAdvancePayment(ModelEmployeeAdvancePaymentRequest model) {
        AdvancedPayment advancedPayment= new AdvancedPayment();
        advancedPayment.setAmount(model.getAmount());
        advancedPayment.setEmployeeId(model.getEmployeeId());
        advancedPayment.setCompany(model.getCompany());
        System.out.println("mapper ici tl ye bakıyoruz: "+ExpenseCurrency.valueOf(model.getCurrency().toUpperCase()));
        advancedPayment.setCurrency(ExpenseCurrency.valueOf(model.getCurrency()));
        advancedPayment.setComment(model.getComment());
        advancedPayment.setStatus(0);
        advancedPayment.setAdvancedPaymentDate(model.getAdvancedPaymentDate());
        advancedPayment.setRequestDate(LocalDateTime.now().toString());
        advancedPayment.setManagerId(model.getManagerId());
        advancedPayment.setAuthId(model.getAuthId());
        advancedPayment.setEmployeeName(model.getEmployeeName());
        advancedPayment.setEmployeeSurname(model.getEmployeeSurname());
        return advancedPayment;
    }

    // baseResponse cevirirken tamamen dto odaklı yapıldı.
    @Override
    public BaseAdvancePaymentResponseDto toBaseAdvancePaymentResponse(AdvancedPayment advancedPayment) {
        BaseAdvancePaymentResponseDto baseAdvancePaymentResponseDto = new BaseAdvancePaymentResponseDto();
        baseAdvancePaymentResponseDto.setEmployeeId(advancedPayment.getEmployeeId());
        baseAdvancePaymentResponseDto.setManagerId(advancedPayment.getManagerId());
        baseAdvancePaymentResponseDto.setAuthId(advancedPayment.getAuthId());
        baseAdvancePaymentResponseDto.setAmount(advancedPayment.getAmount());
        baseAdvancePaymentResponseDto.setCompany(advancedPayment.getCompany());
        baseAdvancePaymentResponseDto.setCurrency(advancedPayment.getCurrency().name());
        baseAdvancePaymentResponseDto.setComment(advancedPayment.getComment());
        baseAdvancePaymentResponseDto.setAdvancedPaymentDate(advancedPayment.getAdvancedPaymentDate());
        baseAdvancePaymentResponseDto.setRequestDate(advancedPayment.getRequestDate().split("T")[0]);
        baseAdvancePaymentResponseDto.setApprovalDate(advancedPayment.getApprovalDate()==null ? "No Answer Yet" : advancedPayment.getApprovalDate().split("T")[0]);
        // entity'den gelen statusü dtoya icin string dönüsümü yapildi.
        baseAdvancePaymentResponseDto.setStatus(advancedPayment.getStatus()==0 ?"Pending" : (advancedPayment.getStatus()==1 ? "Approved" : "Rejected"));
        baseAdvancePaymentResponseDto.setEmployeeName(advancedPayment.getEmployeeName());
        baseAdvancePaymentResponseDto.setEmployeeSurname(advancedPayment.getEmployeeSurname());
        baseAdvancePaymentResponseDto.setAdvancePaymentId(advancedPayment.getId());
        return baseAdvancePaymentResponseDto;
    }


}
