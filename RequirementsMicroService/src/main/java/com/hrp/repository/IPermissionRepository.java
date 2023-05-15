package com.hrp.repository;
import com.hrp.repository.entity.Admin;
import com.hrp.repository.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission,Long>{


}
