package com.Policy.CarInsuranceApplicationAPI.Driver;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;


@Entity
@Table(
        name = "drivers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"policyNumber", "isInsured"},
                name = "unique_primary_driver_per_policy")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policyNumber", nullable = false)
    @NotNull(message = "Policy cannot be null")
    private Policy policy;

    @NotNull(message = "Driver ID cannot be null")
    private String driverId;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Driver's date of birth cannot be null")
    @Past(message = "Driver's date of birth must be a past date")
    private LocalDate driverDOB;

    @NotNull(message = "Driver effective date cannot be null")
    @PastOrPresent(message = "Driver effective date must be in the past or present")
    private LocalDate driverEffectiveDate;

    @NotNull(message = "Driver expiration date cannot be null")
    @Future(message = "Driver expiration date must be in the future")
    private LocalDate driverExpirationDate;

    @NotNull(message = "Driver license ID cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "License ID must be alphanumeric")
    private String driverLicenseId;

    @NotNull(message = "Licensed current state cannot be null")
    private String licensedCurrentState;

    @NotNull(message = "License effective date cannot be null")
    @PastOrPresent(message = "License effective date must be in the past or present")
    private LocalDate licenseEffectiveDate;

    @NotNull(message = "License expiration date cannot be null")
    @Future(message = "License expiration date must be in the future")
    private LocalDate licenseExpirationDate;

    @NotNull(message = "Gender cannot be null")
    private String gender;

    @NotNull(message = "Insured status cannot be null")
    private Boolean isInsured;

    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

    public Driver(DriverPayload dto, Policy policy) {
        this.driverId = dto.getDriverId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.driverDOB = dto.getDriverDOB();
        this.driverEffectiveDate = dto.getDriverEffectiveDate();
        this.driverExpirationDate = dto.getDriverExpirationDate();
        this.driverLicenseId = dto.getDriverLicenseId();
        this.licensedCurrentState = dto.getLicensedCurrentState();
        this.licenseEffectiveDate = dto.getLicenseEffectiveDate();
        this.licenseExpirationDate = dto.getLicenseExpirationDate();
        this.gender = dto.getGender();
        this.isActive = dto.getIsActive();
        this.isInsured = dto.getIsInsured();
        this.createdBy = dto.getCreatedBy();
        this.createdDate = LocalDateTime.now();
        this.modifiedBy = dto.getModifiedBy();
        this.modifiedDate = LocalDateTime.now();
        this.policy = policy;
    }
}

