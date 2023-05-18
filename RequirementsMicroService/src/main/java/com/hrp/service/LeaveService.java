package com.hrp.service;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseLeaveResponseDto;
import com.hrp.mapper.IAdvancePaymentMapper;
import com.hrp.mapper.ILeaveMapper;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.ILeaveRepository;
import com.hrp.repository.entity.Leave;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

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
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByAuthId(authId.get());
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(leaveMapper.toResponseDto(leave));
        }
        return dtos;
    }

    public List<BaseLeaveResponseDto> findAllMyLeavesForManager(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByManagerId(authId.get());
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(leaveMapper.toResponseDto(leave));
        }
        return dtos;
    }
    public List<BaseLeaveResponseDto> findAllMyLeavesPendingForManager(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByManagerId(authId.get());
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(leaveMapper.toResponseDto(leave));
        }
        return dtos.stream().filter(x->x.getStatus()=="Pending").toList();
    }

}
