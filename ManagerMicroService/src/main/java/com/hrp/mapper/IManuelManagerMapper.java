package com.hrp.mapper;

import com.hrp.dto.request.UpdateManagerNoPhotoRequestDto;
import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.BaseManagerResponseDto;
import com.hrp.dto.response.EmployeeRequestAndResponseDto;
import com.hrp.rabbitmq.model.ModelBaseEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.repository.entity.Manager;

public interface IManuelManagerMapper {
     Manager toManager(ModelRegisterManager model);
     BaseManagerResponseDto toBaseManagerResponseDto (Manager manager);
     Manager toManager (Manager manager,UpdateManagerRequestDto dto);
     ModelBaseEmployee toModelBaseEmployee(Manager manager);
     EmployeeRequestAndResponseDto toEmployeeDto(ModelBaseEmployee model);
      Manager toManager(Manager manager, UpdateManagerNoPhotoRequestDto dto);

    }
