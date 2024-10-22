package com.bosch.feedforward.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenDTO {
    private String token;
    private LocalDateTime date;

}
