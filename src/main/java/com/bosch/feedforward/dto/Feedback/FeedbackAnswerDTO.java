package com.bosch.feedforward.dto.Feedback;

import com.bosch.feedforward.entity.Feedback.Feedback;
import com.bosch.feedforward.entity.Feedback.FeedbackQuestion;
import com.bosch.feedforward.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class FeedbackAnswerDTO implements Serializable {

    private UUID feedback;

    private List<UUID> feedbackQuestion;

    private List<Integer> answer;

    private UUID respondent;

    public Feedback mapToFeedback(){
        return Feedback.builder()
                .id(feedback)
                .build();
    }

    public List<FeedbackQuestion> mapToQuestion(){
        List<FeedbackQuestion> questionList = new ArrayList<>();

        for (UUID questionId: feedbackQuestion){
            questionList.add(FeedbackQuestion.builder()
                            .id(questionId)
                            .build());
        }

        return questionList;
    }

    public UserEntity mapToUser() {
        return UserEntity.builder()
                .id(respondent)
                .build();
    }
}
