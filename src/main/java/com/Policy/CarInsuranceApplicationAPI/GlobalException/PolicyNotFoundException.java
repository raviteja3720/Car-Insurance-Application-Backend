package com.Policy.CarInsuranceApplicationAPI.GlobalException;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}