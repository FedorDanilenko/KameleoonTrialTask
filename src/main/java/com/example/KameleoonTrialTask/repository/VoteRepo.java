package com.example.KameleoonTrialTask.repository;

import com.example.KameleoonTrialTask.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<VoteEntity, Long> {
    VoteEntity findByQuoteIdAndUserId(Long quoteId, Long userId);

}
