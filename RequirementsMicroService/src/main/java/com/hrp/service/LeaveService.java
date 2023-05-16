package com.hrp.service;

import com.hrp.mapper.IManuelRequirementsMapper;
import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.ILeaveRepository;
import com.hrp.repository.entity.Leave;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService extends ServiceManagerImpl<Leave, Long> {
    private final ILeaveRepository leaveRepository;
    private final IManuelRequirementsMapper manuelRequirementsMapper;
    private final DirectProducer directProducer;

    public LeaveService(ILeaveRepository leaveRepository, IManuelRequirementsMapper manuelRequirementsMapper, DirectProducer directProducer) {
        super(leaveRepository);
        this.leaveRepository = leaveRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
        this.directProducer = directProducer;
    }
    public void createLeave(ModelEmployeeLeave modelEmployeeLeave){
        System.out.println("leave kayıt ici");
        save(manuelRequirementsMapper.toLeave(modelEmployeeLeave));
        System.out.println("leave kayıt ici ve kayit sonrasi");

    }


    public void findAllLeaveRequestByCompany(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll) {
        Optional<List<Leave>> leaveList =leaveRepository.findOptionalByCompany(modelBaseRequirmentFindAll.getCompany());
        System.out.println("leaveList..: " + leaveList.get());
        List<ModelTurnAllLeaveRequest> turnAllLeaveRequests = new ArrayList<>();
        for (Leave leave: leaveList.get()){
            turnAllLeaveRequests.add(manuelRequirementsMapper.toModelTurnAllLeaveRequest(leave));
        }
        directProducer.turnAllLeaveEmployee(turnAllLeaveRequests);
    }

    public void myLeaveFindAll(ModelEmployeeLeave model) {
        Optional<List<Leave>> leaves=leaveRepository.findOptionalByEmployeeId(model.getEmployeeId());
        List<ModelTurnAllLeaveRequest> turnAllLeaveRequests = new ArrayList<>();
        for (Leave leave: leaves.get()){
            turnAllLeaveRequests.add( manuelRequirementsMapper.toModelTurnAllLeaveRequest(leave));
        }
        directProducer.turnMyLeaveFindAll(turnAllLeaveRequests);
    }
}
