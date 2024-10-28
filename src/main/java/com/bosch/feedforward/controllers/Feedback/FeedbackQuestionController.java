package com.bosch.feedforward.controllers.Feedback;

import com.bosch.feedforward.dto.Feedback.FeedbackQuestionDTO;
import com.bosch.feedforward.entity.Feedback.FeedbackQuestion;
import com.bosch.feedforward.services.Impl.Feedback.FeedbackCategoryServiceImpl;
import com.bosch.feedforward.services.Impl.Feedback.FeedbackQuestionServiceImpl;
import com.bosch.feedforward.specifications.FeedbackQuestionSpecification;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class FeedbackQuestionController {

    @Autowired
    private FeedbackQuestionServiceImpl feedbackQuestionService;

    @GetMapping("/questions/{id}")
    private ResponseEntity<FeedbackQuestion> getQuestionById(@PathVariable UUID id){
            return ResponseEntity.status(HttpStatus.OK).body(this.feedbackQuestionService.getQuestionById(id));
    }

    @GetMapping("/questions")
    private ResponseEntity<Page<FeedbackQuestion>> getAllQuestions(FeedbackQuestionSpecification.QuestionSpec spec, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(this.feedbackQuestionService.getQuestions(spec, page));
    }

    @PostMapping("/questions")
    private ResponseEntity<List<FeedbackQuestion>> createQuestions(@RequestBody @Valid List<FeedbackQuestionDTO> data){
            List<FeedbackQuestion> questionList = data.stream().map(
                    dto -> {
                        FeedbackQuestion convertedQuestion = new FeedbackQuestion();
                        BeanUtils.copyProperties(dto, convertedQuestion, "feedbackCategory");
                        convertedQuestion.setFeedbackCategory(dto.mapToCategory());
                        return convertedQuestion;
                    }
            ).toList();

            return ResponseEntity.status(HttpStatus.CREATED).body(questionList);
    }

    @PutMapping("/question/{id}")
    private ResponseEntity<FeedbackQuestion> updateQuestion(@PathVariable UUID id, @RequestBody @Valid FeedbackQuestionDTO data){
        FeedbackQuestion convertedQuestion = new FeedbackQuestion();
        BeanUtils.copyProperties(data, convertedQuestion, "feedbackCategory");
        convertedQuestion.setFeedbackCategory(data.mapToCategory());
        return ResponseEntity.status(HttpStatus.OK).body(this.feedbackQuestionService.updateQuestion(id, convertedQuestion));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/question/{id}")
    private void deleteQuestion(@PathVariable UUID id) {
        this.feedbackQuestionService.deleteQuestion(id);
    }
}
