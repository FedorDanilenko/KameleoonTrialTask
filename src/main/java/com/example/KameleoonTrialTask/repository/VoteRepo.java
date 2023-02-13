package com.example.KameleoonTrialTask.repository;

import com.example.KameleoonTrialTask.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<VoteEntity, Long> {
    VoteEntity findByQuoteIdAndUserId(Long quoteId, Long userId);

    List<VoteEntity> findAllByQuoteIdOrderByTimestampAsc(Long quoteId);
}
