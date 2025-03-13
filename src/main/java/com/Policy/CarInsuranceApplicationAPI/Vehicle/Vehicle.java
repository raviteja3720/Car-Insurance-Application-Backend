package com.Policy.CarInsuranceApplicationAPI.Vehicle;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;

@Entity
@Table(
        name = "vehicles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"policyNumber", "isPrimaryVehicle"},
                name = "unique_primary_vehicle_per_policy"),
        indexes = {
                @Index(name = "idx_is_primary_vehicle", columnList = "isPrimaryVehicle"),
                @Index(name = "idx_vehicle_id", columnList = "vehicleId"),
                @Index(name = "idx_vehicle_number", columnList = "vehicleNumber"),
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policyNumber", nullable = false)
    @NotNull(message = "Policy cannot be null")
    private Policy policy;

    @NotNull(message = "Vehicle ID cannot be null")
    @Size(min = 7, max = 7, message = "VehicleId must have 7 characters")
    @Pattern(regexp = "^V\\d{6}$", message = "VehicleId must follow the format V000000")
    private String vehicleId;

    @NotNull(message = "Vehicle number cannot be null")
    @Pattern(regexp = "^[A-Z0-9 ]+$", message = "Vehicle number must be alphanumeric")
    @Size(min = 6, max = 12, message = "Vehicle Number must be between 6 and 12 characters")
    private String vehicleNumber;

    @NotNull(message = "Manufacturer cannot be null")
    @Size(min = 2, max = 50, message = "Manufacturer name must be between 2 and 50 characters")
    private String manufacturer;

    @NotNull(message = "Model cannot be null")
    @Size(min = 2, max = 50, message = "Model name must be between 2 and 50 characters")
    private String model;

    @NotNull(message = "Model year cannot be null")
    @Min(value = 2000, message = "Model year must be after 2000")
    @Max(value = 2025, message = "Model year cannot be in the future")
    private Integer modelYear;

    @NotNull(message = "Vehicle effective date cannot be null")
    @PastOrPresent(message = "Vehicle effective date must be in the past or present")
    private LocalDate vehicleEffectiveDate;

    @NotNull(message = "Vehicle expiration date cannot be null")
    @Future(message = "Vehicle expiration date must be in the future")
    private LocalDate vehicleExpirationDate;

    @NotNull(message = "Registration state cannot be null")
    private String registrationState;

    @NotNull(message = "Primary vehicle status cannot be null")
    private Boolean isPrimaryVehicle;

    @NotNull(message = "Vehicle age cannot be null")
    @Min(value = 0, message = "Vehicle age cannot be negative")
    private Integer vehicleAge;

    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

    // Constructor to map payload to entity
    public Vehicle(VehiclePayload dto, Policy policy) {
        this.vehicleId = dto.getVehicleId();
        this.vehicleNumber = dto.getVehicleNumber();
        this.manufacturer = dto.getManufacturer();
        this.model = dto.getModel();
        this.modelYear = dto.getModelYear();
        this.vehicleEffectiveDate = dto.getVehicleEffectiveDate();
        this.vehicleExpirationDate = dto.getVehicleExpirationDate();
        this.registrationState = dto.getRegistrationState();
        this.isPrimaryVehicle = dto.getIsPrimaryVehicle();
//        this.vehicleAge = dto.getVehicleAge();
        this.vehicleAge = Year.now().getValue() - dto.getModelYear();
        this.isActive = dto.getIsActive();
        this.createdBy = dto.getCreatedBy();
        this.createdDate = LocalDateTime.now();
        this.modifiedBy = dto.getModifiedBy();
        this.modifiedDate = LocalDateTime.now();
        this.policy = policy;
    }
}
