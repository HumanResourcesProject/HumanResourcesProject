package com.hrp.service;

import com.hrp.rabbitmq.model.ModelEmployeePermissionRequest;
import com.hrp.repository.IPermissionRepository;
import com.hrp.repository.entity.Permission;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public class PermissionService extends ServiceManagerImpl<Permission, Long> {
    private final IPermissionRepository permissionRepository;
    public PermissionService(IPermissionRepository permissionRepository) {
        super(permissionRepository);
        this.permissionRepository = permissionRepository;
    }



}
