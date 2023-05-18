package com.hrp.service;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseAdvancePaymentResponseDto;
import com.hrp.mapper.IAdvancePaymentMapper;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.repository.IAdvancePaymentRepository;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvancePaymentService extends ServiceManagerImpl<AdvancedPayment, Long> {
    private final IAdvancePaymentRepository advancePaymentRepository;
    private final IAdvancePaymentMapper manuelRequirementsMapper;
    private final JwtTokenManager jwtTokenManager;

    public AdvancePaymentService(IAdvancePaymentRepository advancePaymentRepository, IAdvancePaymentMapper manuelRequirementsMapper, JwtTokenManager jwtTokenManager) {
        super(advancePaymentRepository);
        this.advancePaymentRepository = advancePaymentRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void createAdvancePayment(ModelEmployeeAdvancePaymentRequest model) {
        System.out.println("createAdvancePayment metodu ici");
            save(manuelRequirementsMapper.toAdvancePayment(model));

    }
    public List<BaseAdvancePaymentResponseDto> findAllMyAdvancePaymentForEmployee(BaseRequestDto dto){
        Optional<Long> authId= jwtTokenManager.validToken(dto.getToken());
        Optional<List<AdvancedPayment>> advancedPayments= advancePaymentRepository.findOptionalByAuthId(authId.get());
        List<BaseAdvancePaymentResponseDto> dtos = new ArrayList<>();
        for ( AdvancedPayment advance : advancedPayments.get()){
            dtos.add(manuelRequirementsMapper.toBaseAdvancePaymentResponse(advance));
        }
        return dtos;
    }
    public List<BaseAdvancePaymentResponseDto> findAllMyAdvancePaymentForManager(BaseRequestDto dto){
        Optional<Long> authId= jwtTokenManager.validToken(dto.getToken());
        Optional<List<AdvancedPayment>> advancedPayments= advancePaymentRepository.findOptionalByManagerId(authId.get());
        List<BaseAdvancePaymentResponseDto> dtos = new ArrayList<>();
        for ( AdvancedPayment advance : advancedPayments.get()){
            dtos.add(manuelRequirementsMapper.toBaseAdvancePaymentResponse(advance));
        }
        return dtos;
    }

    public List<BaseAdvancePaymentResponseDto> findAllMyAdvancePaymentPendingForManager(BaseRequestDto dto){
        Optional<Long> authId= jwtTokenManager.validToken(dto.getToken());
        Optional<List<AdvancedPayment>> advancedPayments= advancePaymentRepository.findOptionalByManagerId(authId.get());
        List<BaseAdvancePaymentResponseDto> dtos = new ArrayList<>();
        for ( AdvancedPayment advance : advancedPayments.get()){
            dtos.add(manuelRequirementsMapper.toBaseAdvancePaymentResponse(advance));
        }
        return dtos.stream().filter(x->x.getStatus()=="Pending").toList();
    }

    public Boolean approveAdvancePayment(String employeeId) {
        Optional<AdvancedPayment> advancedPayment = advancePaymentRepository.findOptionalByEmployeeId(employeeId);
        if (advancedPayment.isEmpty()){
            System.out.println("Istek bulunamadi");
            return false;
        }
        advancedPayment.get().setStatus(1);
        advancedPayment.get().setApprovalDate(String.valueOf(LocalDateTime.now()));
        return true;
    }

    public Boolean rejectAdvancePayment(String employeeId) {
        Optional<AdvancedPayment> advancedPayment = advancePaymentRepository.findOptionalByEmployeeId(employeeId);
        if (advancedPayment.isEmpty()){
            System.out.println("Istek bulunamadi");
            return false;
        }
        advancedPayment.get().setStatus(2);
        advancedPayment.get().setApprovalDate(String.valueOf(LocalDateTime.now()));
        return true;
    }
}
