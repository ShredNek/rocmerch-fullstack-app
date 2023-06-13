package com.rocmerchbackend.rocmerchbackend;

import com.rocmerchbackend.rocmerchbackend.model.Feedback;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
public class FeedbackListener {

    @PrePersist
    public void prePersist(Feedback feedback) {
        feedback.setTimestamp(LocalDateTime.now());
    }
}
