package com.hrp.repository;
import com.hrp.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyRepository extends MongoRepository<Company,Long> {


    Optional<Company> findOptionalByEmail(String email);
}
