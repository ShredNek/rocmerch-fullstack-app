package com.rocmerchbackend.rocmerchbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocmerchbackend.rocmerchbackend.model.Feedback;
import com.rocmerchbackend.rocmerchbackend.repository.FeedbackRepository;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/get-all")
    public final List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @PostMapping("/new")
    public final String newFeedback(@RequestBody Feedback usrFeedback) throws JsonProcessingException {
        feedbackRepository.save(usrFeedback);
        return "Save feedback to id: "+usrFeedback.getId();
    }

    @DeleteMapping("/wipe-all")
    public final String deleteAllFeedback() {
        feedbackRepository.deleteAll();
        return "Deleted all feedback.";
    }
}
