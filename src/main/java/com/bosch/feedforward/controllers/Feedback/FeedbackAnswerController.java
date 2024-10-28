package com.bosch.feedforward.controllers.Feedback;

import com.bosch.feedforward.services.Impl.Feedback.FeedbackAnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class FeedbackAnswerController {

    @Autowired
    private FeedbackAnswerServiceImpl answerService;


}
