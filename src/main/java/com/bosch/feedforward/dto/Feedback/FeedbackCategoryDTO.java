package com.bosch.feedforward.dto.Feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackCategoryDTO implements Serializable {

    @NotNull
    @NotBlank
    private String type;
}
