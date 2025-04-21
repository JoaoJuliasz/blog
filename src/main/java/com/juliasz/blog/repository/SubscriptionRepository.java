package com.juliasz.blog.repository;

import com.juliasz.blog.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("""
            SELECT s.subscriberId FROM Subscription s
            WHERE s.creatorId = :creatorId
            """)
    List<Long> findAllSubscriber(@Param("creatorId")Long creatorId);
}
