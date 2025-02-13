package com.Policy.CarInsuranceApplicationAPI.Vehicle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Api/Vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("getAllVehicleDetailsByPolicyNumber")
    public ResponseEntity<?> getAllVehicleDetailsByPolicyNumber(@RequestParam String PolicyNumber) {
        return vehicleService.getAllVehicleDetailsByPolicyNumber(PolicyNumber);
    }

    @GetMapping("getVehicleDetailsByPolicyNumberAndVehicleId")
    public ResponseEntity<?> getVehicleDetailsByPolicyNumberAndVehicleId(@RequestParam String PolicyNumber,
                                                                         @RequestParam(required = false) String VehicleId) {
        return vehicleService.getVehicleDetailsByPolicyNumberAndVehicleId(PolicyNumber, VehicleId);
    }

    @PostMapping("addVehicleByPolicyNumber")
    public ResponseEntity<?> addVehicleByPolicyNumber(@RequestBody VehiclePayload vehicleRequest,
                                                      @RequestParam String PolicyNumber) {
        return vehicleService.addVehicleByPolicyNumber(vehicleRequest, PolicyNumber);
    }

    @Transactional
    @PostMapping("updateVehicleByPolicyNumberAndVehicleId")
    public ResponseEntity<?> updateVehicleByPolicyNumberAndVehicleId(@RequestBody VehiclePayload vehicleRequest,
                                                                     @RequestParam String PolicyNumber) {
        return vehicleService.updateVehicleByPolicyNumberAndVehicleId(vehicleRequest, PolicyNumber);
    }

    @Transactional
    @DeleteMapping("DeleteVehicleByPolicyNumberAndVehicleId")
    public ResponseEntity<?> DeleteVehicleByPolicyNumberAndVehicleId(@RequestParam String PolicyNumber,
                                                                     @RequestParam String VehicleId) {
        return vehicleService.DeleteVehicleByPolicyNumberAndVehicleId(PolicyNumber, VehicleId);
    }


}
