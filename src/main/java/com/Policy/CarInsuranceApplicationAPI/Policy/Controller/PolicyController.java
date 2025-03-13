package com.Policy.CarInsuranceApplicationAPI.Policy.Controller;

import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CreatePolicyPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.InsuredPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.Service.PolicyService;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Api/Policies")
public class PolicyController {

    @Autowired
    PolicyService policyService;

    @PostMapping("CreatePolicy")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<?>> CreatePolicy(@Valid @RequestBody CreatePolicyPayload policyPayload) {
        return policyService.CreatePolicy(policyPayload);
    }

    @GetMapping("getAllPolicies")
    public CompletableFuture<?> getAllPolicies(@Valid @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size) {
        return policyService.getAllPolicies(page, size);
    }

    @GetMapping("getPolicyDetailsByPolicyNumber/{policyNumber}")
    public CompletableFuture<ResponseEntity<?>> getPolicyDetailsByPolicyNumber(@Valid @PathVariable
                                                            @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                            String policyNumber) {
        return policyService.GetPolicyDetailsByPolicyNumber(policyNumber);
    }

    @Transactional
    @PostMapping("updateInsuredDetails")
    public ResponseEntity<?> updateInsuredDetails(@Valid @RequestBody InsuredPayload insured,
                                                  @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                  String PolicyNumber) {
        return policyService.updateInsuredDetailsByInsuredName(insured, PolicyNumber);
    }

    @GetMapping("cancelPolicy/{PolicyNumber}")
    public ResponseEntity<?> cancelPolicy(@Valid @PathVariable @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                          String PolicyNumber) {
        return policyService.cancelPolicy(PolicyNumber);
    }

    @GetMapping("activatePolicy/{PolicyNumber}")
    public ResponseEntity<?> activatePolicy(@Valid @PathVariable @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                            String PolicyNumber) {
        return policyService.activatePolicy(PolicyNumber);
    }

    @GetMapping("getCoverageDetails/{PolicyNumber}")
    public ResponseEntity<?> getCoverageDetails(@Valid @PathVariable @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                String PolicyNumber) {
        return policyService.getCoverageDetails(PolicyNumber);
    }


//    @PostMapping("")
//    @PostMapping("searchPolicyDetails")
//    public List<PolicyResponse> searchPolicyDetails(@RequestBody PolicySearchRequest policySearchRequest) {
//        try {
//            return policyService.searchPolicyDetails(policySearchRequest);
//        } catch (PolicyNotFoundException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex);
//        }
//    }

}
