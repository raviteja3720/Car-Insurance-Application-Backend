package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private String vehicleId;
    private String vehicleNumber;
    private String manufacturer;
    private String model;
    private Integer modelYear;
    private LocalDate vehicleEffectiveDate;
    private LocalDate vehicleExpirationDate;
    private String registrationState;
    private Boolean isPrimaryVehicle;
    private Integer vehicleAge;
    private Boolean isActive;

    public VehicleResponse(Vehicle dto) {
        this.vehicleId = dto.getVehicleId();
        this.vehicleNumber = dto.getVehicleNumber();
        this.manufacturer = dto.getManufacturer();
        this.model = dto.getModel();
        this.modelYear = dto.getModelYear();
        this.vehicleEffectiveDate = dto.getVehicleEffectiveDate();
        this.vehicleExpirationDate = dto.getVehicleExpirationDate();
        this.registrationState = dto.getRegistrationState();
        this.isPrimaryVehicle = dto.getIsPrimaryVehicle();
        this.vehicleAge = dto.getVehicleAge();
        this.isActive = dto.getIsActive();

    }
}
