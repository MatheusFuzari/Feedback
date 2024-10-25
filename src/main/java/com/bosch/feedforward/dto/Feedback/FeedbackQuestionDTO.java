package com.bosch.feedforward.dto.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackQuestionDTO implements Serializable {

    @NotBlank
    @NotNull
    private String question;

    @NotBlank
    @NotNull
    private String subText;

    @NotBlank
    @NotNull
    private UUID feedbackCategory;

    @JsonIgnore
    public FeedbackCategory mapToCategory(){
        return FeedbackCategory.builder()
                .id(this.feedbackCategory)
                .build();
    }
}
