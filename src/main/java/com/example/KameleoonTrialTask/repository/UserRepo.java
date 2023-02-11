package com.example.KameleoonTrialTask.repository;

import com.example.KameleoonTrialTask.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
