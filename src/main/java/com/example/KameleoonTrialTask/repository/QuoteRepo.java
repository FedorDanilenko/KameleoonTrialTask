package com.example.KameleoonTrialTask.repository;

import com.example.KameleoonTrialTask.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepo extends JpaRepository<QuoteEntity, Long> {
}
