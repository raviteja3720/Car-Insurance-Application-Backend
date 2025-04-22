package com.Policy.CarInsuranceApplicationAPI.Policy.Service;

import com.Policy.CarInsuranceApplicationAPI.Driver.Driver;
import com.Policy.CarInsuranceApplicationAPI.Driver.DriverRepository;

import com.Policy.CarInsuranceApplicationAPI.GlobalException.ErrorResponse;
import com.Policy.CarInsuranceApplicationAPI.GlobalException.InsuredNotFoundException;
import com.Policy.CarInsuranceApplicationAPI.GlobalException.PolicyNotFoundException;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Coverage;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Insured;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.Repository.CoverageRepository;
import com.Policy.CarInsuranceApplicationAPI.Policy.Repository.InsuredRepository;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CreatePolicyPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.Repository.PolicyRepository;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.InsuredPayload;
import com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO.PolicyResponse;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.Vehicle;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.VehiclePayload;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.Policy.CarInsuranceApplicationAPI.Policy.PolicyConversion.*;


@Service
public class PolicyService implements IPolicyService {

    @Autowired
    PolicyRepository policyRepository;
    @Autowired
    InsuredRepository insuredRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    CoverageRepository coverageRepository;


    @Transactional
    @Override
    public ResponseEntity<?> CreatePolicy(CreatePolicyPayload policyDTO) {

        if (policyDTO == null) {
            throw new IllegalArgumentException("Policy payload cannot be null.");
        }

        if (policyRepository.existsByPolicyNumber(policyDTO.getPolicyNumber())) {
            throw new IllegalArgumentException("Policy number already exists.");
        }

        if (insuredRepository.existsByEmailID(policyDTO.getInsured().getEmailID())) {
            throw new IllegalArgumentException("Insured person with this email already exists.");
        }

//        if (policyDTO.getVehicles() != null && !policyDTO.getVehicles().isEmpty() &&
//                vehicleRepository.existsByVehicleNumber(policyDTO.getVehicles().get(0).getVehicleNumber())) {
//            throw new IllegalArgumentException("Vehicle with this registration number already exists.");
//        }

        if (policyDTO.getVehicles() != null && !policyDTO.getVehicles().isEmpty()) {
            for (VehiclePayload vehicleDTO : policyDTO.getVehicles()) {
                if (vehicleRepository.existsByVehicleNumber(vehicleDTO.getVehicleNumber())) {
                    throw new IllegalArgumentException("Vehicle with this registration number already exists.");
                }
            }
        }
        Policy policy = new Policy(policyDTO);

//        policyRepository.save(policy);
//
//        if (policyDTO.getInsured() != null) {
//            Insured insured = new Insured(policyDTO.getInsured(), policy);
//            insuredRepository.save(insured);
//        }
//
//        if (policyDTO.getDrivers() != null) {
//            policyDTO.getDrivers().forEach(driverDTO ->
//                    driverRepository.save(new Driver(driverDTO, policy))
//            );
//        }
//
//        if (policyDTO.getVehicles() != null) {
//            policyDTO.getVehicles().forEach(vehicleDTO ->
//                    vehicleRepository.save(new Vehicle(vehicleDTO, policy))
//            );
//        }
//
//        if (policyDTO.getCoverages() != null) {
//            policyDTO.getCoverages().forEach(coverageDTO ->
//                    coverageRepository.save(new Coverage(coverageDTO, policy))
//            );
//        }
        try {
            policyRepository.save(policy);
        } catch (Exception e) {
            throw new RuntimeException("Error saving policy: " + e.getMessage(), e);
        }

        if (policyDTO.getInsured() != null) {
            try {
                Insured insured = new Insured(policyDTO.getInsured(), policy);
                insuredRepository.save(insured);
            } catch (Exception e) {
                throw new RuntimeException("Error saving insured person: " + e.getMessage(), e);
            }
        }

        if (policyDTO.getDrivers() != null) {
            policyDTO.getDrivers().forEach(driverDTO -> {
                try {
                    driverRepository.save(new Driver(driverDTO, policy));
                } catch (Exception e) {
                    throw new RuntimeException("Error saving driver: " + e.getMessage(), e);
                }
            });
        }

        if (policyDTO.getVehicles() != null) {
            policyDTO.getVehicles().forEach(vehicleDTO -> {
                try {
                    vehicleRepository.save(new Vehicle(vehicleDTO, policy));
                } catch (Exception e) {
                    throw new RuntimeException("Error saving vehicle: " + e.getMessage(), e);
                }
            });
        }

        if (policyDTO.getCoverages() != null) {
            policyDTO.getCoverages().forEach(coverageDTO -> {
                try {
                    coverageRepository.save(new Coverage(coverageDTO, policy));
                } catch (Exception e) {
                    throw new RuntimeException("Error saving coverage: " + e.getMessage(), e);
                }
            });
        }

        return ResponseEntity.ok("Policy created successfully with Policy Number " + policyDTO.getPolicyNumber());
    }

    @Override
    @Async
    @Transactional(readOnly = true)
    public CompletableFuture<?> getAllPolicies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Policy> policies = policyRepository.findAll(pageable);
        if (policies.isEmpty()) {
            throw new PolicyNotFoundException("No policies found. ");

        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(PolicyToPolicyResponse(policies.getContent())));
    }

    @Override
    public ResponseEntity<?> GetPolicyDetailsByPolicyNumber(String policyNumber) {
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }
        Policy policy = policyRepository.findByPolicyNumber(policyNumber);
        if (policy != null) {
            return ResponseEntity.ok(PolicyEntitytoPolicyResponse(policy));
        } else
            throw new PolicyNotFoundException("Policy is not found with Policy Number " + policyNumber);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateInsuredDetailsByInsuredName(InsuredPayload insured, String PolicyNumber) {
        if (PolicyNumber == null || PolicyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(PolicyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + PolicyNumber));
        }
        Policy policy = policyRepository.findByPolicyNumber(PolicyNumber);

        Insured insuredDetails = insuredRepository.findByPolicy_PolicyNumber(PolicyNumber)
                .map(existingInsured -> {
                    // Update fields only if the new value is different
                    if (insured.getInsuredType() != null && !insured.getInsuredType().equals(existingInsured.getInsuredType())) {
                        existingInsured.setInsuredType(insured.getInsuredType());
                    }
                    if (insured.getFirstName() != null && !insured.getFirstName().equals(existingInsured.getFirstName())) {
                        existingInsured.setFirstName(insured.getFirstName());
                    }
                    if (insured.getLastName() != null && !insured.getLastName().equals(existingInsured.getLastName())) {
                        existingInsured.setLastName(insured.getLastName());
                    }
                    if (insured.getFullName() != null && !insured.getFullName().equals(existingInsured.getFullName())) {
                        existingInsured.setFullName(insured.getFirstName() + " " + insured.getLastName());
                    }
                    if (insured.getPhoneNo() != null && !insured.getPhoneNo().equals(existingInsured.getPhoneNo())) {
                        existingInsured.setPhoneNo(insured.getPhoneNo());
                    }
                    if (insured.getEmailID() != null && !insured.getEmailID().equals(existingInsured.getEmailID())) {
                        existingInsured.setEmailID(insured.getEmailID());
                    }
                    if (insured.getAddress() != null && !insured.getAddress().equals(existingInsured.getAddress())) {
                        existingInsured.setAddress(insured.getAddress());
                    }
                    if (insured.getModifiedBy() != null && !insured.getModifiedBy().equals(existingInsured.getModifiedBy())) {
                        existingInsured.setModifiedBy(insured.getModifiedBy());
                    }
                    existingInsured.setModifiedDate(LocalDateTime.now());
                    try {
                        return insuredRepository.save(existingInsured);
                    } catch (Exception e) {
                        throw new RuntimeException("Error saving Insured: " + e.getMessage(), e);
                    }
                })
                .orElseThrow(() -> new InsuredNotFoundException("Insured not found with name: " + insured.getFullName()));

        Driver existingDriver;
        if (driverRepository.existsByPolicy_PolicyNumber(PolicyNumber)) {
            existingDriver = driverRepository.getDriverByFirstName(insured.getFirstName());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Insured/Driver Not Found", "No Insured/Driver found with policy number: " + PolicyNumber));
        }

        if (Objects.equals(existingDriver.getFirstName(), insured.getFirstName())) {
            existingDriver.setFirstName(insured.getFirstName());
            existingDriver.setLastName(insured.getLastName());
            existingDriver.setPolicy(policy);

            try {
                driverRepository.save(existingDriver);
            } catch (Exception e) {
                throw new RuntimeException("Error saving Driver: " + e.getMessage(), e);
            }
        }
        return ResponseEntity.ok(toInsuredResponseDTO(insuredDetails));
    }

    @Override
    public ResponseEntity<?> cancelPolicy(String PolicyNumber) {
        if (PolicyNumber == null || PolicyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }
        if (!policyRepository.existsByPolicyNumber(PolicyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + PolicyNumber));
        }
        Policy policy = policyRepository.findByPolicyNumber(PolicyNumber);

        if (!policy.getIsActive()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(404, "Policy already Cancelled", "Policy cannot cancel again with policy number: " + PolicyNumber));
        }
        policy.setIsActive(false);
        policyRepository.save(policy);
        return ResponseEntity.ok("The policy with policy number " + PolicyNumber + " has been successfully cancelled.");
    }

    @Override
    public ResponseEntity<?> activatePolicy(String PolicyNumber) {
        if (PolicyNumber == null || PolicyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }
        if (!policyRepository.existsByPolicyNumber(PolicyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + PolicyNumber));
        }
        Policy policy = policyRepository.findByPolicyNumber(PolicyNumber);

        if (policy.getIsActive()) {
            return ResponseEntity.ok("Policy is already active.");
        }
        policy.setIsActive(true);
        policyRepository.save(policy);
        return ResponseEntity.ok("The policy with policy number " + PolicyNumber + " has been Activated.");

    }

    @Override
    public ResponseEntity<?> getCoverageDetails(String policyNumber) {
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        Policy policy = policyRepository.findByPolicyNumber(policyNumber);
        PolicyResponse policyResponse = PolicyEntitytoPolicyResponse(policy);

        if (policyResponse.getCoverages() == null || policyResponse.getCoverages().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ErrorResponse(204, "No Coverage Details", "No coverage details found for the policy number: " + policyNumber));
        }
        return ResponseEntity.ok(policyResponse.getCoverages());
    }

}

//    @Transactional
//    public List<PolicyResponse> searchPolicyDetails(PolicySearchRequest request) {
//        List<Policy> policies = policyRepository.searchPolicyDetails(
//                request.getPolicyNumber(),
//                request.getInsuredName(),
//                request.getVehicleNumber(),
//                request.getPolicyType(),
//                request.getEffectiveDate()
//        );
//        if (policies.isEmpty()) {
//            throw new PolicyNotFoundException("No policies found matching the provided search criteria.");
//        }
//        return PolicyToPolicyResponse(policies);
////        return Optional.of(PolicyEntitytoPolicyResponse(policies.get()));
//    }


