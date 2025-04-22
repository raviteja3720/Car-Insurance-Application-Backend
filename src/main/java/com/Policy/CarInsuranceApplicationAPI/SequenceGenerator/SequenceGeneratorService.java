package com.Policy.CarInsuranceApplicationAPI.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SequenceGeneratorService {

    @Autowired
    private SequenceRepository sequenceRepository;

    public String generatePolicyNumber() {
        return generateSequence("PolicyNumber", "P");
    }

    public String generateDriverId() {
        return generateSequence("DriverId", "D");
    }

    public String generateVehicleId() {
        return generateSequence("VehicleId", "V");
    }

    @Transactional
    private String generateSequence(String type, String prefix) {
        Sequence sequence = sequenceRepository.findById(type)
                .orElseThrow(() -> new RuntimeException("Sequence not found for type: " + type));

        int newValue = sequence.getValue() + 1;
        sequence.setValue(newValue);
        sequenceRepository.save(sequence);

        return prefix + String.format("%06d", newValue);
    }
}