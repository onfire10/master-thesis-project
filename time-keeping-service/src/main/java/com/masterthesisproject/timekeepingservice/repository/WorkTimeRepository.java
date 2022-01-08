package com.masterthesisproject.timekeepingservice.repository;

import com.masterthesisproject.timekeepingservice.domain.WorkTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTimeRepository extends MongoRepository<WorkTime, String> {
}
