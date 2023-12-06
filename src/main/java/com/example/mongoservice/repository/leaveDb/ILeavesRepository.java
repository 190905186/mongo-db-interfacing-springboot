package com.example.mongoservice.repository.leaveDb;

import com.example.mongoservice.model.leaveDb.LeavesTemp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILeavesRepository extends MongoRepository<LeavesTemp, Integer> {

}
