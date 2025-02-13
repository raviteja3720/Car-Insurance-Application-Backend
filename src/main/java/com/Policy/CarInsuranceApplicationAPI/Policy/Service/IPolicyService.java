package com.Policy.CarInsuranceApplicationAPI.Policy.Service;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CreatePolicyPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.InsuredPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.PolicySearchRequest;
import com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO.PolicyResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface IPolicyService {
    ResponseEntity<?> CreatePolicy( CreatePolicyPayload policyPayload);

    ResponseEntity<?> getAllPolicies(int page, int size);

    ResponseEntity<?> GetPolicyDetailsByPolicyNumber(String policyNumber);

    ResponseEntity<?> updateInsuredDetailsByInsuredName(InsuredPayload insured, String PolicyNumber);

    ResponseEntity<?> cancelPolicy(@Valid String policyNumber);

    ResponseEntity<?> activatePolicy(String policyNumber);

    ResponseEntity<?> getCoverageDetails(String policyNumber);

//    List<PolicyResponse> searchPolicyDetails(PolicySearchRequest request);
}
