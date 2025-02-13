package com.Policy.CarInsuranceApplicationAPI.Policy;

import com.Policy.CarInsuranceApplicationAPI.Driver.DriverResponse;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Insured;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO.CoverageResponse;
import com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO.InsuredResponse;
import com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO.PolicyResponse;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.Vehicle;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.VehicleResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PolicyConversion {

    public static List<PolicyResponse> PolicyToPolicyResponse(List<Policy> policies) {
        return policies.stream()
                .map(policy -> {
                            PolicyResponse policyResponse = new PolicyResponse();
                            policyResponse.setPolicyNumber(policy.getPolicyNumber());
                            policyResponse.setPolicyType(policy.getPolicyNumber());
                            policyResponse.setPolicyTerm(policy.getPolicyNumber());
                            policyResponse.setPolicyEffectiveDate(policy.getPolicyEffectiveDate());
                            policyResponse.setPolicyExpirationDate(policy.getPolicyExpirationDate());
                            policyResponse.setCoverageType(policy.getCoverageType());
                            policyResponse.setIsActive(policy.getIsActive());
                            policyResponse.setPolicyStatus(policy.getPolicyStatus());
                            policyResponse.setCreatedBy(policy.getCreatedBy());
                            policyResponse.setIsActive(policy.getIsActive());
                            return policyResponse;
                        }
                )
                .collect(Collectors.toList());

    }


    public static PolicyResponse PolicyEntitytoPolicyResponse(Policy policy) {
        PolicyResponse policyResponse = new PolicyResponse();
        policyResponse.setPolicyNumber(policy.getPolicyNumber());
        policyResponse.setPolicyType(policy.getPolicyType());
        policyResponse.setPolicyTerm(policy.getPolicyTerm());
        policyResponse.setPolicyEffectiveDate(policy.getPolicyEffectiveDate());
        policyResponse.setPolicyExpirationDate(policy.getPolicyExpirationDate());
        policyResponse.setCoverageType(policy.getCoverageType());
        policyResponse.setIsActive(policy.getIsActive());
        policyResponse.setPolicyStatus(policy.getPolicyStatus());
        policyResponse.setCreatedBy(policy.getCreatedBy());

        // Map Insured object
        if (policy.getInsured() != null) {
            policyResponse.setInsured(toInsuredResponseDTO(policy.getInsured()));
        }

        // Map Drivers
        policyResponse.setDrivers(policy.getDrivers().stream()
                .map(DriverResponse::new)
                .collect(Collectors.toList()));

        policyResponse.setVehicles(toVehicleResponseDTO(policy.getVehicles()));

        // Map Coverages
        policyResponse.setCoverages(policy.getCoverages().stream()
                .map(CoverageResponse::new)
                .collect(Collectors.toList()));

        return policyResponse;
    }

    // Mapping Insured
    public static InsuredResponse toInsuredResponseDTO(Insured insured) {
        InsuredResponse insuredResponseDTO = new InsuredResponse();
        insuredResponseDTO.setInsuredType(insured.getInsuredType());
        insuredResponseDTO.setFirstName(insured.getFirstName());
        insuredResponseDTO.setLastName(insured.getLastName());
        insuredResponseDTO.setFullName(insured.getFullName());
        insuredResponseDTO.setPhoneNo(insured.getPhoneNo());
        insuredResponseDTO.setEmailID(insured.getEmailID());
        insuredResponseDTO.setAddress(insured.getAddress());
        return insuredResponseDTO;
    }

    // Mapping Inline List<VehicleResponse> conversion
    public static List<VehicleResponse> toVehicleResponseDTO(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(vehicle -> {
                            VehicleResponse vehicleResponse = new VehicleResponse();
                            vehicleResponse.setVehicleId(vehicle.getVehicleId());
                            vehicleResponse.setVehicleNumber(vehicle.getVehicleNumber());
                            vehicleResponse.setManufacturer(vehicle.getManufacturer());
                            vehicleResponse.setModel(vehicle.getModel());
                            vehicleResponse.setModelYear(vehicle.getModelYear());
                            vehicleResponse.setVehicleEffectiveDate(vehicle.getVehicleEffectiveDate());
                            vehicleResponse.setVehicleExpirationDate(vehicle.getVehicleExpirationDate());
                            vehicleResponse.setRegistrationState(vehicle.getRegistrationState());
                            vehicleResponse.setVehicleAge(vehicle.getVehicleAge());
                            vehicleResponse.setIsPrimaryVehicle(vehicle.getIsPrimaryVehicle());
                            vehicleResponse.setIsActive(vehicle.getIsActive());
                            return vehicleResponse;
                        }
                )
                .collect(Collectors.toList());
    }

}
