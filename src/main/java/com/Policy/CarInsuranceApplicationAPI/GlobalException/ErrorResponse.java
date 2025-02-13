package com.Policy.CarInsuranceApplicationAPI.GlobalException;


import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private String error;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}