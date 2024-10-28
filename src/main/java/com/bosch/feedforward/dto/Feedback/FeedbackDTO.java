package com.bosch.feedforward.dto.Feedback;


import com.bosch.feedforward.entity.Semester;
import com.bosch.feedforward.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDTO {

    private UUID semester;

    private UUID user;

    private boolean open;


    @JsonIgnore
    public Semester mapToSemester (){
        return Semester.builder()
                .id(this.semester)
                .build();
    }

    @JsonIgnore
    public UserEntity mapToUser() {
        return UserEntity.builder()
                .id(this.user)
                .build();
    }

}
