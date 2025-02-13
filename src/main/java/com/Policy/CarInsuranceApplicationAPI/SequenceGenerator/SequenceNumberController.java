package com.Policy.CarInsuranceApplicationAPI.SequenceGenerator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/Policies")
public class SequenceNumberController {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/generatePolicyNumber")
    public String generatePolicyNumber() {
        return sequenceGeneratorService.generatePolicyNumber();
    }

    @GetMapping("/generateDriverId")
    public String generateDriverId() {
        return sequenceGeneratorService.generateDriverId();
    }

    @GetMapping("/generateVehicleId")
    public String generateVehicleId() {
        return sequenceGeneratorService.generateVehicleId();
    }
}
