package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("Api/Vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("getAllVehicleDetailsByPolicyNumber")
    public ResponseEntity<?> getAllVehicleDetailsByPolicyNumber(@Valid @RequestParam
                                                                @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                String PolicyNumber) {
        return vehicleService.getAllVehicleDetailsByPolicyNumber(PolicyNumber);
    }

    @GetMapping("getVehicleDetailsByPolicyNumberAndVehicleId")
    public ResponseEntity<?> getVehicleDetailsByPolicyNumberAndVehicleId(@RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                         String PolicyNumber,
                                                                         @RequestParam(required = false) @Pattern(regexp = "^V\\d{6}$", message = "VehicleId must follow the format V000000")
                                                                         String VehicleId) {
        return vehicleService.getVehicleDetailsByPolicyNumberAndVehicleId(PolicyNumber, VehicleId);
    }

    @PostMapping("addVehicleByPolicyNumber")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addVehicleByPolicyNumber(@RequestBody VehiclePayload vehicleRequest,
                                                      @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                      String PolicyNumber) {
        return vehicleService.addVehicleByPolicyNumber(vehicleRequest, PolicyNumber);
    }

    @Transactional
    @PostMapping("updateVehicleByPolicyNumberAndVehicleId")
    public ResponseEntity<?> updateVehicleByPolicyNumberAndVehicleId(@RequestBody VehiclePayload vehicleRequest,
                                                                     @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                     String PolicyNumber) {
        return vehicleService.updateVehicleByPolicyNumberAndVehicleId(vehicleRequest, PolicyNumber);
    }

    @Transactional
    @DeleteMapping("DeleteVehicleByPolicyNumberAndVehicleId")
    public ResponseEntity<?> DeleteVehicleByPolicyNumberAndVehicleId(@RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                     String PolicyNumber,
                                                                     @RequestParam @Pattern(regexp = "^V\\d{6}$", message = "VehicleId must follow the format V000000")
                                                                     String VehicleId) {
        return vehicleService.DeleteVehicleByPolicyNumberAndVehicleId(PolicyNumber, VehicleId);
    }


}
