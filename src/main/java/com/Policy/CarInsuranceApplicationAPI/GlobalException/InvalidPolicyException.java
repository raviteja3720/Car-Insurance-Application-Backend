package com.Policy.CarInsuranceApplicationAPI.GlobalException;

public class InvalidPolicyException extends RuntimeException {
    public InvalidPolicyException(String message) {
        super(message);
    }
}
