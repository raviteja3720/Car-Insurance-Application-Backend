package com.Policy.CarInsuranceApplicationAPI.Driver;

import com.Policy.CarInsuranceApplicationAPI.GlobalException.ErrorResponse;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.Repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DriverService implements IDriverService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    PolicyRepository policyRepository;

    @Override
    public ResponseEntity<?> getAllDriverDetailsByPolicyNumber(String policyNumber) {

        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        List<Driver> drivers = driverRepository.getAllDriversByPolicy_PolicyNumber(policyNumber);

        if (drivers == null || drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Drivers Not Found", "No drivers found for policy number: " + policyNumber));
        }
        return ResponseEntity.ok(drivers.stream()
                .map(DriverResponse::new)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> getDriverDetailsByPolicyNumberAndDriverId(String policyNumber, String driverId) {
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        Driver driver;
        if (driverId == null || driverId.trim().isEmpty()) {
            driver = driverRepository.getDriverByPolicy_PolicyNumberAndIsInsuredTrue(policyNumber);
        } else {
            // If driverId is provided, fetch the driver by policy number and driverId
            driver = driverRepository.getDriverByPolicy_PolicyNumberAndDriverId(policyNumber, driverId);
        }

        // Check if the driver is found
        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Driver Not Found",
                            driverId == null ? "No primary insured driver found for policy number: " + policyNumber
                                    : "No driver found with Driver Id: " + driverId));
        }
        return ResponseEntity.ok(new DriverResponse(driver));
    }

    @Override
    public ResponseEntity<?> addDriverByPolicyNumber(DriverPayload driverRequest, String policyNumber) {

        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        if (driverRepository.existsByDriverId(driverRequest.getDriverId())) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Driver already exists", "Cannot add the same driver with DriverId: " + driverRequest.getDriverId()));
        }

//        if (driverRequest.getIsInsured()) {
//            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Driver, Insured Driver already exists", "new Driver can not be Insured"));
//        }
//
//        Driver existingDriver = driverRepository.getDriverByDriverId(driverRequest.getDriverId());
//        if (Objects.equals(driverRequest.getDriverId(), existingDriver.getDriverId())) {
//            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Driver already exists", "cannot add same driver: " + driverRequest.getDriverId()));
//        }

        if (driverRequest.getIsInsured()) {
            Optional<Driver> existingDriver = driverRepository.findByPolicy_PolicyNumberAndIsInsuredTrue(policyNumber);
            if (existingDriver.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse(400, "Invalid Driver", "An insured driver already exists for this policy: " + policyNumber));
            }
        }

        Policy policy = policyRepository.findByPolicyNumber(policyNumber);
        Driver addedDriver;
        try {
            addedDriver = driverRepository.save(new Driver(driverRequest, policy));
        } catch (Exception e) {
            throw new RuntimeException("Error saving Driver: " + e.getMessage(), e);
        }
        return ResponseEntity.ok("Driver added to PolicyNumber: " + policyNumber + " with DriverId " + addedDriver.getDriverId());
    }

    @Override
    public ResponseEntity<?> updateDriverByPolicyNumberAndDriverId(DriverPayload driverRequest, String policyNumber) {

        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }
        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        if (driverRepository.existsByDriverId(driverRequest.getDriverId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Driver Not Found", "No driver found with DriverId: " + driverRequest.getDriverId()));
        }

        Policy policy = policyRepository.findByPolicyNumber(policyNumber);

        Driver existingDriver = driverRepository.getDriverByDriverId(driverRequest.getDriverId());

        existingDriver.setFirstName(driverRequest.getFirstName());
        existingDriver.setLastName(driverRequest.getLastName());
        existingDriver.setDriverDOB(driverRequest.getDriverDOB());
        existingDriver.setDriverEffectiveDate(driverRequest.getDriverEffectiveDate());
        existingDriver.setDriverExpirationDate(driverRequest.getDriverExpirationDate());
        existingDriver.setDriverLicenseId(driverRequest.getDriverLicenseId());
        existingDriver.setLicensedCurrentState(driverRequest.getLicensedCurrentState());
        existingDriver.setLicenseEffectiveDate(driverRequest.getLicenseEffectiveDate());
        existingDriver.setLicenseExpirationDate(driverRequest.getLicenseExpirationDate());
        existingDriver.setGender(driverRequest.getGender());
        existingDriver.setIsActive(driverRequest.getIsActive());
        existingDriver.setIsInsured(driverRequest.getIsInsured());
        existingDriver.setModifiedBy(driverRequest.getModifiedBy());
        existingDriver.setModifiedDate(LocalDateTime.now());

        existingDriver.setPolicy(policy);

        Driver updatedDriver;
        try {
            updatedDriver = driverRepository.save(existingDriver);
        } catch (Exception e) {
            throw new RuntimeException("Error saving Driver: " + e.getMessage(), e);
        }
        return ResponseEntity.ok("Driver updated with DriverId: " + updatedDriver.getDriverId() + " for Policy Number " + policy.getPolicyNumber());
    }

    @Override
    public ResponseEntity<?> DeleteDriverByPolicyNumberAndDriverId(String policyNumber, String driverId) {
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        if (driverId == null || driverId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid driverId ", "driverId cannot be null or empty "));
        }
        if (!driverRepository.existsByDriverId(driverId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Driver Not Found", "No Driver found with DriverId: " + driverId));
        }
        Driver driver = driverRepository.getDriverByPolicy_PolicyNumberAndDriverId(policyNumber, driverId);
        if (driver.getIsInsured()) {
            return ResponseEntity.badRequest().
                    body(new ErrorResponse(400, "Insured Driver", "Insured Driver cannot be deleted"));
        }

        driverRepository.deleteByDriverIdAndPolicy_PolicyNumber(driver.getDriverId(), policyNumber);

        return ResponseEntity.ok("Driver Deleted with DriverId: " + driverId + " for Policy Number " + policyNumber);
    }

}
