package com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload;

import com.Policy.CarInsuranceApplicationAPI.Driver.DriverPayload;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.VehiclePayload;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicyPayload {

    @NotNull(message = "Policy number cannot be null")
    @Size(min = 7, max = 7, message = "Policy number must have 7 characters")
    @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
    private String policyNumber;

    @NotNull(message = "Policy type cannot be null")
    private String policyType;

    @NotNull(message = "Policy term cannot be null")
    private String policyTerm;

    @NotNull(message = "Policy effective date cannot be null")
    @PastOrPresent(message = "Policy effective date must be in the past or present")
    private LocalDate policyEffectiveDate;

    @NotNull(message = "Policy expiration date cannot be null")
    @Future(message = "Policy expiration date must be in the future")
    private LocalDate policyExpirationDate;

    @NotNull(message = "Coverage type cannot be null")
    private String coverageType;

    @NotNull(message = "IsActive cannot be null")
    private Boolean isActive;

    @NotNull(message = "Policy status cannot be null")
    private String policyStatus;

    @NotNull(message = "Insured information cannot be null")
    private InsuredPayload insured;

    @NotEmpty(message = "Drivers list cannot be empty")
    private List<DriverPayload> drivers;

    @NotEmpty(message = "Vehicles list cannot be empty")
    private List<VehiclePayload> vehicles;

    @NotEmpty(message = "Coverages list cannot be empty")
    private List<CoveragePayload> coverages;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;

}
