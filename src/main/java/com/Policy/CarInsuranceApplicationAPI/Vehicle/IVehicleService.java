package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import org.springframework.http.ResponseEntity;

public interface IVehicleService {
    ResponseEntity<?> getAllVehicleDetailsByPolicyNumber(String policyNumber);

    ResponseEntity<?> getVehicleDetailsByPolicyNumberAndVehicleId(String policyNumber, String vehicleId);

    ResponseEntity<?> getVehicleDetailsByVehicleNumber(String vehicleNumber);

    ResponseEntity<?> addVehicleByPolicyNumber(VehiclePayload vehicleRequest, String policyNumber);

    ResponseEntity<?> updateVehicleByPolicyNumberAndVehicleId(VehiclePayload vehicleRequest, String policyNumber);

    ResponseEntity<?> DeleteVehicleByPolicyNumberAndVehicleId(String policyNumber, String vehicleId);
}
