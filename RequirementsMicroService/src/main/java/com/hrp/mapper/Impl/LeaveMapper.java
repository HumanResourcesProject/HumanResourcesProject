package com.hrp.mapper.Impl;

import com.hrp.dto.response.BaseLeaveResponseDto;
import com.hrp.mapper.ILeaveMapper;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.entity.Leave;
import com.hrp.repository.entity.enums.LeaveType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LeaveMapper implements ILeaveMapper {

    @Override
    public Leave toLeave(ModelEmployeeLeave model) {
        Leave leave= new Leave();
        leave.setType(LeaveType.valueOf(model.getType()));
        leave.setEmployeeId(model.getEmployeeId());
        leave.setCompany(model.getCompany());
        leave.setAmountOfDay(model.getAmountOfDay());
        leave.setStatus(0);
        leave.setFinishDate(model.getFinishDate());
        leave.setEmployeeName(model.getEmployeeName());
        leave.setEmployeeSurname(model.getEmployeeSurname());
        leave.setStartDate(model.getStartDate());
        leave.setRequestDate(LocalDateTime.now().toString());
        leave.setManagerId(model.getManagerId());
        leave.setAuthId(model.getAuthId());
        return leave;
    }

    @Override
    public BaseLeaveResponseDto toResponseDto(Leave leave) {
        BaseLeaveResponseDto dto = new BaseLeaveResponseDto();
        dto.setEmployeeId(leave.getEmployeeId());
        dto.setAuthId(leave.getAuthId());
        dto.setManagerId(leave.getManagerId());
        dto.setCompany(leave.getCompany());
        dto.setType(leave.getType().name());
        dto.setRequestDate(leave.getRequestDate().split("T")[0]);
        dto.setStartDate(leave.getStartDate().split("T")[0]);
        dto.setFinishDate(leave.getFinishDate().split("T")[0]);
        dto.setApprovalDate(leave.getApprovalDate()==null ? "No Answer Yet" : leave.getApprovalDate().split("T")[0]);
        // entity'den gelen statusü dtoya icin string dönüsümü yapildi.
        dto.setStatus(leave.getStatus()==0 ? "Pending" : (leave.getStatus()==1 ? "Approved" : "Rejected"));
        dto.setAmountOfDay(leave.getAmountOfDay());
        dto.setEmployeeName(leave.getEmployeeName());
        dto.setEmployeeSurname(leave.getEmployeeSurname());
        dto.setLeaveId(leave.getId());
        return dto;

    }

}
