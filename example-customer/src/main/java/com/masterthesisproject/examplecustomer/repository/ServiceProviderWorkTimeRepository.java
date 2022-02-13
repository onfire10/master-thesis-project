package com.masterthesisproject.examplecustomer.repository;

import com.masterthesisproject.examplecustomer.domain.ServiceProviderWorkTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderWorkTimeRepository extends MongoRepository<ServiceProviderWorkTime, String> {
}
