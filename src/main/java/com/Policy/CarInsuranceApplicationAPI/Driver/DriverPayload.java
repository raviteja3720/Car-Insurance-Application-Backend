package com.Policy.CarInsuranceApplicationAPI.Driver;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverPayload {

    @NotNull(message = "Driver ID cannot be null")
    @Size(min = 7, max = 7, message = "DriverId must have 7 characters")
    @Pattern(regexp = "^D\\d{6}$", message = "DriverId must follow the format D000000")
    private String driverId;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Driver's date of birth must be a past date")
    private LocalDate driverDOB;

    @NotNull(message = "Driver effective date cannot be null")
    @PastOrPresent(message = "Driver effective date must be in the past or present")
    private LocalDate driverEffectiveDate;

    @NotNull(message = "Driver expiration date cannot be null")
    @Future(message = "Driver expiration date must be in the future")
    private LocalDate driverExpirationDate;

    @NotNull(message = "Driver license ID cannot be null")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "License ID must be alphanumeric")
    @Size(min = 6, max = 12, message = "Driver LicenseId must be between 6 and 12 characters")
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

    @NotNull(message = "Insurance status cannot be null")
    private Boolean isInsured;

    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;
}
