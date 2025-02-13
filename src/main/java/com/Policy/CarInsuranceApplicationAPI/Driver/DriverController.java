package com.Policy.CarInsuranceApplicationAPI.Driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("Api/Driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("getAllDriverDetailsByPolicyNumber")
    public ResponseEntity<?> getAllDriverDetailsByPolicyNumber(@RequestParam String PolicyNumber) {
        return driverService.getAllDriverDetailsByPolicyNumber(PolicyNumber);
    }

    @GetMapping("getDriverDetailsByPolicyNumberAndDriverId")
    public ResponseEntity<?> getDriverDetailsByPolicyNumberAndDriverId(@RequestParam String PolicyNumber,
                                                                       @RequestParam String DriverId) {
        return driverService.getDriverDetailsByPolicyNumberAndDriverId(PolicyNumber, DriverId);
    }

    @PostMapping("addDriverByPolicyNumber")
    public ResponseEntity<?> addDriverByPolicyNumber(@RequestBody DriverPayload driverRequest,
                                                     @RequestParam String PolicyNumber) {
        return driverService.addDriverByPolicyNumber(driverRequest, PolicyNumber);
    }

    @PostMapping("updateDriverByPolicyNumberAndDriverId")
    public ResponseEntity<?> updateDriverByPolicyNumberAndDriverId(@RequestBody DriverPayload driverRequest,
                                                                   @RequestParam String PolicyNumber) {
        return driverService.updateDriverByPolicyNumberAndDriverId(driverRequest, PolicyNumber);
    }

    @Transactional
    @DeleteMapping("DeleteDriverByPolicyNumberAndDriverId")
    public ResponseEntity<?> DeleteDriverByPolicyNumberAndDriverId(@RequestParam String PolicyNumber,
                                                                   @RequestParam String DriverId) {
        return driverService.DeleteDriverByPolicyNumberAndDriverId(PolicyNumber, DriverId);
    }

}
