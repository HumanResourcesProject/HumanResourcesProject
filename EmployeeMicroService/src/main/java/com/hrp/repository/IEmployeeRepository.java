package com.hrp.repository;

import com.hrp.repository.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends MongoRepository<Employee,String> {
    Optional<Employee> findOptionalByAuthId(Long authId);
}
