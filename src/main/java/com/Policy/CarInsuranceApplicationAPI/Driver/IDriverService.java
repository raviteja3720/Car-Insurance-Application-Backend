package com.Policy.CarInsuranceApplicationAPI.Driver;

import org.springframework.http.ResponseEntity;

public interface IDriverService {
    ResponseEntity<?> getAllDriverDetailsByPolicyNumber(String policyNumber);

    ResponseEntity<?> getDriverDetailsByPolicyNumberAndDriverId(String policyNumber, String driverId);

    ResponseEntity<?> addDriverByPolicyNumber(DriverPayload driverRequest, String policyNumber);

    ResponseEntity<?> updateDriverByPolicyNumberAndDriverId(DriverPayload driverRequest, String policyNumber);

    ResponseEntity<?> DeleteDriverByPolicyNumberAndDriverId(String policyNumber, String driverId);
}
