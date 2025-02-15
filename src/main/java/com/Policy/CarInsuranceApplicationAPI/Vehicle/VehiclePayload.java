package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePayload {

    @NotNull(message = "Vehicle ID cannot be null")
    @Size(min = 7, max = 7, message = "VehicleId must have 7 characters")
    @Pattern(regexp = "^V\\d{6}$", message = "VehicleId must follow the format V000000")
    private String vehicleId;

    @NotNull(message = "Vehicle number cannot be null")
    @Size(min = 6, max = 12, message = "Vehicle Number must be between 6 and 12 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Vehicle number must be alphanumeric")
    private String vehicleNumber;

    @NotNull(message = "Manufacturer cannot be null")
    @Size(min = 2, max = 50, message = "Manufacturer name must be between 2 and 50 characters")
    private String manufacturer;

    @NotNull(message = "Model cannot be null")
    @Size(min = 1, max = 50, message = "Model name must be between 1 and 50 characters")
    private String model;

    @NotNull(message = "Model year cannot be null")
    @Min(value = 1900, message = "Model year must be greater than or equal to 1900")
    @Max(value = 2025, message = "Model year must be less than or equal to the current year")
    private Integer modelYear;

    @NotNull(message = "Vehicle effective date cannot be null")
    private LocalDate vehicleEffectiveDate;

    @NotNull(message = "Vehicle expiration date cannot be null")
    private LocalDate vehicleExpirationDate;

    @NotNull(message = "Registration state cannot be null")
    @Size(min = 2, max = 2, message = "Registration state must be a two-letter state code")
    private String registrationState;

    @NotNull(message = "Primary vehicle status cannot be null")
    private Boolean isPrimaryVehicle;

    @NotNull(message = "Vehicle age cannot be null")
    @Min(value = 0, message = "Vehicle age cannot be negative")
    private Integer vehicleAge;

    @NotNull(message = "IsActive cannot be null")
    private Boolean isActive;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

    public VehiclePayload(Vehicle dto, Policy policy) {
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
        this.modifiedBy = dto.getModifiedBy();
        this.modifiedDate = dto.getModifiedDate();


    }
}
