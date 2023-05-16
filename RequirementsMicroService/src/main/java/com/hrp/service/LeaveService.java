package com.hrp.service;

import com.hrp.mapper.IManuelRequirementsMapper;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.ILeaveRepository;
import com.hrp.repository.entity.Leave;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

@Service
public class LeaveService extends ServiceManagerImpl<Leave, Long> {
    private final ILeaveRepository leaveRepository;
    private final IManuelRequirementsMapper manuelRequirementsMapper;

    public LeaveService(ILeaveRepository leaveRepository, IManuelRequirementsMapper manuelRequirementsMapper) {
        super(leaveRepository);
        this.leaveRepository = leaveRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
    }
    public void createLeave(ModelEmployeeLeave modelEmployeeLeave){
        System.out.println("leave kayıt ici");
        save(manuelRequirementsMapper.toLeave(modelEmployeeLeave));
        System.out.println("leave kayıt ici ve kayit sonrasi");

    }



}
