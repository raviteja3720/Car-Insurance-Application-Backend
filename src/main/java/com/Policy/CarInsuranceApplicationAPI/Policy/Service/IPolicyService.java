package com.Policy.CarInsuranceApplicationAPI.Policy.Service;

import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CreatePolicyPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.InsuredPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IPolicyService {

    CompletableFuture<ResponseEntity<?>> CreatePolicy( CreatePolicyPayload policyPayload);

    CompletableFuture<?> getAllPolicies(int page, int size);

    CompletableFuture<ResponseEntity<?>> GetPolicyDetailsByPolicyNumber(String policyNumber);

    ResponseEntity<?> updateInsuredDetailsByInsuredName(InsuredPayload insured, String PolicyNumber);

    ResponseEntity<?> cancelPolicy(@Valid String policyNumber);

    ResponseEntity<?> activatePolicy(String policyNumber);

    ResponseEntity<?> getCoverageDetails(String policyNumber);

//    List<PolicyResponse> searchPolicyDetails(PolicySearchRequest request);
}
