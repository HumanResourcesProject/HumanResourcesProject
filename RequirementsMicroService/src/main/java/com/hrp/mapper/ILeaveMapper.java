package com.hrp.mapper;

import com.hrp.dto.response.BaseLeaveResponseDto;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.entity.Leave;

public interface ILeaveMapper {
    Leave toLeave(final ModelEmployeeLeave model);
    BaseLeaveResponseDto toResponseDto(final Leave leave);
}
