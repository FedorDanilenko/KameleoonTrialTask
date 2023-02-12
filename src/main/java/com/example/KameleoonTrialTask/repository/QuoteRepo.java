package com.example.KameleoonTrialTask.repository;

import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepo extends CrudRepository<QuoteEntity, Long> {

}
