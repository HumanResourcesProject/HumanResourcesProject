package com.hrp.repository;

import com.hrp.repository.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILeaveRepository extends JpaRepository<Leave,Long>{

}
