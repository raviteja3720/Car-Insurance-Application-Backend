package com.Policy.CarInsuranceApplicationAPI.Driver;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("Api/Driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("getAllDriverDetailsByPolicyNumber")
    public ResponseEntity<?> getAllDriverDetailsByPolicyNumber(@Valid @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                               String PolicyNumber) {
        return driverService.getAllDriverDetailsByPolicyNumber(PolicyNumber);
    }

    @GetMapping("getDriverDetailsByPolicyNumberAndDriverId")
    public ResponseEntity<?> getDriverDetailsByPolicyNumberAndDriverId(@Valid @RequestParam
                                                                       @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                       String PolicyNumber,
                                                                       @RequestParam(required = false) @Pattern(regexp = "^D\\d{6}$", message = "DriverId must follow the format D000000")
                                                                       String DriverId) {
        return driverService.getDriverDetailsByPolicyNumberAndDriverId(PolicyNumber, DriverId);
    }

    @PostMapping("addDriverByPolicyNumber")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addDriverByPolicyNumber(@Valid @RequestBody DriverPayload driverRequest,
                                                     @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                     String policyNumber) {
        return driverService.addDriverByPolicyNumber(driverRequest, policyNumber);
    }

    @PostMapping("updateDriverByPolicyNumberAndDriverId")
    public ResponseEntity<?> updateDriverByPolicyNumberAndDriverId(@Valid @RequestBody DriverPayload driverRequest,
                                                                   @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                   String PolicyNumber) {
        return driverService.updateDriverByPolicyNumberAndDriverId(driverRequest, PolicyNumber);
    }


    @DeleteMapping("DeleteDriverByPolicyNumberAndDriverId")
    public ResponseEntity<?> DeleteDriverByPolicyNumberAndDriverId(@Valid @RequestParam
                                                                   @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                   String PolicyNumber,
                                                                   @RequestParam @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
                                                                   String DriverId) {
        return driverService.DeleteDriverByPolicyNumberAndDriverId(PolicyNumber, DriverId);
    }

}
