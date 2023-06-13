package com.rocmerchbackend.rocmerchbackend.model;

import com.rocmerchbackend.rocmerchbackend.FeedbackListener;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(FeedbackListener.class)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedback;
    private LocalDateTime timestamp;

    @PrePersist
    private void setTimestampAtInstance() {
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
