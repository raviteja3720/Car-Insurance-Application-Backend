package com.Policy.CarInsuranceApplicationAPI.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InsuredNotFoundException extends RuntimeException {
    public InsuredNotFoundException(String message) {
        super(message);
    }
}
