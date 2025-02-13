package com.Policy.CarInsuranceApplicationAPI.Driver;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByPolicy_PolicyNumber(String policyNumber);

    List<Driver> getAllDriversByPolicy_PolicyNumber(String policyNumber);

    Driver getDriverByPolicy_PolicyNumberAndDriverId(String policyNumber, String driverId);

    Boolean existsByDriverId(@NotNull(message = "Driver ID cannot be null") String driverId);

    Driver getDriverByDriverId(@NotNull(message = "Driver ID cannot be null") String driverId);

    void deleteByDriverIdAndPolicy_PolicyNumber(@NotNull(message = "Driver ID cannot be null") String driverId, String policyNumber);

    Boolean existsByPolicy_PolicyNumber(String PolicyNumber);

    Driver getDriverByPolicy_PolicyNumber(String PolicyNumber);


    Driver getDriverByFirstName(@NotNull(message = "First name cannot be null")
                                @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
                                String firstName);

    Driver getDriverByPolicy_PolicyNumberAndIsInsuredTrue(String policyNumber);

    Optional<Driver> findByPolicy_PolicyNumberAndIsInsuredTrue(String policyNumber);
}
