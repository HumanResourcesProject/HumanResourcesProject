package com.hrp.service;

import com.hrp.dto.request.BaseAnswerDto;
import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseLeaveResponseDto;
import com.hrp.exception.EErrorType;
import com.hrp.exception.RequirementsMicroException;
import com.hrp.mapper.IAdvancePaymentMapper;
import com.hrp.mapper.ILeaveMapper;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.ILeaveRepository;
import com.hrp.repository.entity.Leave;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService extends ServiceManagerImpl<Leave, Long> {
    private final ILeaveRepository leaveRepository;
    private final JwtTokenManager jwtTokenManager;
    private final ILeaveMapper leaveMapper;

    public LeaveService(ILeaveRepository leaveRepository, JwtTokenManager jwtTokenManager, ILeaveMapper leaveMapper) {
        super(leaveRepository);
        this.leaveRepository = leaveRepository;
        this.leaveMapper = leaveMapper;
        this.jwtTokenManager = jwtTokenManager;
    }
    public void createLeave(ModelEmployeeLeave modelEmployeeLeave){
        System.out.println("leave kayıt ici");
        save(leaveMapper.toLeave(modelEmployeeLeave));
        System.out.println("leave kayıt ici ve kayit sonrasi");

    }

    public List<BaseLeaveResponseDto> findAllMyLeavesForEmployee(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsMicroException(EErrorType.INVALID_TOKEN);
        }
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByAuthId(authId.get());
        if (leaves.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(leaveMapper.toResponseDto(leave));
        }
        return dtos;
    }

    public List<BaseLeaveResponseDto> findAllMyLeavesForManager(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsMicroException(EErrorType.INVALID_TOKEN);
        }
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByManagerId(authId.get());
        if (leaves.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(leaveMapper.toResponseDto(leave));
        }
        return dtos;
    }
    public List<BaseLeaveResponseDto> findAllMyLeavesPendingForManager(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsMicroException(EErrorType.INVALID_TOKEN);
        }
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByManagerId(authId.get());
        if (leaves.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(leaveMapper.toResponseDto(leave));
        }
        return dtos.stream().filter(x->x.getStatus()=="Pending").toList();
    }

    public Boolean approveleave(BaseAnswerDto dto) {
        Optional<Leave> leave= leaveRepository.findById(dto.getRequirementId());
        if (leave.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        leave.get().setStatus(1);
        leave.get().setApprovalDate(LocalDateTime.now().toString());
        update(leave.get());
        return true;
    }
    public Boolean rejectleave(BaseAnswerDto dto) {
        Optional<Leave> leave= leaveRepository.findById(dto.getRequirementId());
        if (leave.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        leave.get().setStatus(2);
        leave.get().setApprovalDate(LocalDateTime.now().toString());
        update(leave.get());
        return true;
    }

}
