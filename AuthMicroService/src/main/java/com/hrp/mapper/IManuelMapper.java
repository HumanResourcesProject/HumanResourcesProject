package com.hrp.mapper;

import com.hrp.dto.request.RegisterAdminRequestDto;
import com.hrp.dto.request.RegisterEmployeeRequestDto;
import com.hrp.dto.request.RegisterManagerRequestDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.repository.entity.Auth;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface IManuelMapper {
    ModelRegisterAdmin authToModelRegisterAdmin(Auth auth, RegisterAdminRequestDto dto) ;
     ModelRegisterManager authToModelRegisterManager(Auth auth, RegisterManagerRequestDto dto) ;
     ModelRegisterEmployee authToModelRegisterEmployee(Auth auth, RegisterEmployeeRequestDto dto,Long managerId) ;

    }
