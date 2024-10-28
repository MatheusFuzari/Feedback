package com.bosch.feedforward.controllers.Feedback;

import com.bosch.feedforward.dto.Feedback.FeedbackDTO;
import com.bosch.feedforward.entity.Feedback.Feedback;
import com.bosch.feedforward.services.Impl.Feedback.FeedbackServiceImpl;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @GetMapping("/feedback")
    private ResponseEntity<Page<Feedback>> getAllResponses(Pageable page, Specification<Feedback> spec){
        return ResponseEntity.status(HttpStatus.OK).body(this.feedbackService.getAllFeedbacks(spec, page));
    }

    @PostMapping("/feedback")
    private ResponseEntity<Feedback> saveFeedback(@RequestBody @Valid FeedbackDTO data){
        Feedback convertedFeedback = new Feedback();
        BeanUtils.copyProperties(data, convertedFeedback);
        convertedFeedback.setUser(data.mapToUser());
        convertedFeedback.setSemester(data.mapToSemester());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.feedbackService.createFeedback(convertedFeedback));
    }

    @PutMapping("/feedback/{id}")
    private ResponseEntity<Feedback> updateFeedback(@RequestBody @Valid FeedbackDTO data, @PathVariable UUID id){
        Feedback convertedFeedback = new Feedback();
        BeanUtils.copyProperties(data, convertedFeedback);
        convertedFeedback.setUser(data.mapToUser());
        convertedFeedback.setSemester(data.mapToSemester());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.feedbackService.updateFeedback(id, convertedFeedback));
    }

    @DeleteMapping("/feedback/{id}")
    private void deleteFeedback(@PathVariable UUID id){
        this.feedbackService.deleteFeedback(id);
    }
}
