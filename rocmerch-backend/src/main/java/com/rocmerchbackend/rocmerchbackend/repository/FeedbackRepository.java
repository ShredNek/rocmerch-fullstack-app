package com.rocmerchbackend.rocmerchbackend.repository;

import com.rocmerchbackend.rocmerchbackend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
