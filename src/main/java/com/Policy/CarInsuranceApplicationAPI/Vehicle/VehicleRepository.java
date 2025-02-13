package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Boolean existsByVehicleNumber(String vehicleNumber);

    List<Vehicle> findByPolicy_PolicyNumber(String policyNumber);

    //    ResponseEntity<?> getAllVehiclesByPolicy_PolicyNumber(String policyNumber);
    List<Vehicle> getAllVehiclesByPolicy_PolicyNumber(String policyNumber);

    Boolean existsByVehicleId(String vehicleId);

    Vehicle getVehicleByPolicy_PolicyNumberAndVehicleId(String policyNumber, String vehicleId);

    Vehicle getVehicleByVehicleId(@NotNull(message = "Vehicle ID cannot be null")
                                  String vehicleId);

    void deleteByVehicleIdAndPolicy_PolicyNumber(String vehicleId, String policyNumber);

    Vehicle getVehicleByPolicy_PolicyNumberAndIsPrimaryVehicleTrue(String policyNumber);

    Optional<Vehicle> findByPolicy_PolicyNumberAndIsPrimaryVehicleTrue(String policyNumber);
}
