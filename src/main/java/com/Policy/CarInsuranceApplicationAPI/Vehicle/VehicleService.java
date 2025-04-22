package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import com.Policy.CarInsuranceApplicationAPI.GlobalException.ErrorResponse;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.Repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static com.Policy.CarInsuranceApplicationAPI.Policy.PolicyConversion.toVehicleResponseDTO;


@Service
public class VehicleService implements IVehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    PolicyRepository policyRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAllVehicleDetailsByPolicyNumber(String policyNumber) {

        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        List<Vehicle> vehicles = vehicleRepository.getAllVehiclesByPolicy_PolicyNumber(policyNumber);

        if (vehicles == null || vehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Vehicles Not Found", "No vehicles found for policy number: " + policyNumber));
        }
        return ResponseEntity.ok(toVehicleResponseDTO(vehicles));
    }

    @Override
    public ResponseEntity<?> getVehicleDetailsByPolicyNumberAndVehicleId(String policyNumber, String vehicleId) {
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        Vehicle vehicle;
        // If vehicleId is not provided, fetch the primary vehicle
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            vehicle = vehicleRepository.getVehicleByPolicy_PolicyNumberAndIsPrimaryVehicleTrue(policyNumber);
        } else {
            // If vehicleId is provided, fetch the vehicle by policy number and vehicleId
            vehicle = vehicleRepository.getVehicleByPolicy_PolicyNumberAndVehicleId(policyNumber, vehicleId);
        }

        // Check if the vehicle is found
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Vehicle Not Found",
                            vehicleId == null ? "No primary vehicle found for policy number: " + policyNumber
                                    : "No vehicle found with Vehicle Id: " + vehicleId));
        }

        // Return the vehicle details
        return ResponseEntity.ok(new VehicleResponse(vehicle));
    }

    @Override
    public ResponseEntity<?> getVehicleDetailsByVehicleNumber(String vehicleNumber) {

        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, "Invalid vehicle Number ", "vehicle Number cannot be null or empty "));
        }

        if (!vehicleRepository.existsByVehicleNumber(vehicleNumber)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Vehicle not found", "No Vehicle found with Vehicle Number: " + vehicleNumber));
        }

        Vehicle vehicle = vehicleRepository.getVehicleByVehicleNumber(vehicleNumber);
        return ResponseEntity.ok(new VehicleResponse(vehicle));
    }

    @Override
    public ResponseEntity<?> addVehicleByPolicyNumber(VehiclePayload vehicleRequest, String policyNumber) {

        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        if (vehicleRepository.existsByVehicleId(vehicleRequest.getVehicleId()) || vehicleRepository.existsByVehicleNumber(vehicleRequest.getVehicleNumber())) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Vehicle already exists", "Cannot add the same Vehicle with VehicleId " + vehicleRequest.getVehicleId() + " or Vehicle Number: " + vehicleRequest.getVehicleNumber()));
        }
        if (vehicleRequest.getIsPrimaryVehicle()) {
            Optional<Vehicle> existingPrimaryVehicle = vehicleRepository.findByPolicy_PolicyNumberAndIsPrimaryVehicleTrue(policyNumber);
            if (existingPrimaryVehicle.isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Vehicle", "A primary vehicle already exists for this policy"));
            }
        }

        Policy policy = policyRepository.findByPolicyNumber(policyNumber);
        Vehicle addedVehicle;
        try {
            addedVehicle = vehicleRepository.save(new Vehicle(vehicleRequest, policy));
        } catch (Exception e) {
            throw new RuntimeException("Error saving Vehicle: " + e.getMessage(), e);
        }

        return ResponseEntity.ok("Vehicle added to PolicyNumber: " + policyNumber + " with VehicleId " + addedVehicle.getVehicleId());
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateVehicleByPolicyNumberAndVehicleId(VehiclePayload vehicleRequest, String policyNumber) {

        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        if (!vehicleRepository.existsByVehicleId(vehicleRequest.getVehicleId())) {
            System.out.println("inside");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Vehicle Not Found", "No Vehicle found with VehicleId: " + vehicleRequest.getVehicleId()));
        }
//        System.out.println("outside");

        Policy policy = policyRepository.findByPolicyNumber(policyNumber);

        Vehicle ExistingVehicle = vehicleRepository.getVehicleByVehicleId(vehicleRequest.getVehicleId());

        ExistingVehicle.setVehicleNumber(vehicleRequest.getVehicleNumber());
        ExistingVehicle.setManufacturer(vehicleRequest.getManufacturer());
        ExistingVehicle.setModel(vehicleRequest.getModel());
        ExistingVehicle.setModelYear(vehicleRequest.getModelYear());
        ExistingVehicle.setVehicleEffectiveDate(vehicleRequest.getVehicleEffectiveDate());
        ExistingVehicle.setVehicleExpirationDate(vehicleRequest.getVehicleExpirationDate());
        ExistingVehicle.setRegistrationState(vehicleRequest.getRegistrationState());
        ExistingVehicle.setIsPrimaryVehicle(vehicleRequest.getIsPrimaryVehicle());
        ExistingVehicle.setVehicleAge(Year.now().getValue() - vehicleRequest.getModelYear());
        ExistingVehicle.setIsActive(vehicleRequest.getIsActive());
        ExistingVehicle.setModifiedBy(vehicleRequest.getModifiedBy());
        ExistingVehicle.setModifiedDate(LocalDateTime.now());

        ExistingVehicle.setPolicy(policy);

        Vehicle updatedVehicle;
        try {
            updatedVehicle = vehicleRepository.save(ExistingVehicle);
        } catch (Exception e) {
            throw new RuntimeException("Error saving Vehicle: " + e.getMessage(), e);
        }
        return ResponseEntity.ok("Vehicle updated with VehicleId: " + updatedVehicle.getVehicleId() + " for Policy Number " + policy.getPolicyNumber());
    }

    @Override
    @Transactional
    public ResponseEntity<?> DeleteVehicleByPolicyNumberAndVehicleId(String policyNumber, String vehicleId) {
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Invalid Policy Number", "Policy number cannot be null or empty"));
        }

        if (!policyRepository.existsByPolicyNumber(policyNumber)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Policy Not Found", "No policy found with policy number: " + policyNumber));
        }

        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, "Invalid vehicleId ", "vehicleId cannot be null or empty "));
        }

        if (!vehicleRepository.existsByVehicleId(vehicleId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Vehicle Not Found", "No vehicle found with vehicleId: " + vehicleId));
        }
        Vehicle vehicle = vehicleRepository.getVehicleByPolicy_PolicyNumberAndVehicleId(policyNumber, vehicleId);

        if (vehicle.getIsPrimaryVehicle()) {
            return ResponseEntity.badRequest().
                    body(new ErrorResponse(400, "Primary Vehicle", "Primary Vehicle cannot be deleted"));
        }
        vehicleRepository.deleteByVehicleIdAndPolicy_PolicyNumber(vehicle.getVehicleId(), policyNumber);

        return ResponseEntity.ok("Vehicle Deleted with VehicleId: " + vehicleId + " for Policy Number " + policyNumber);

    }

}
