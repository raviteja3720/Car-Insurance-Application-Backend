package com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoveragePayload {

    @NotNull(message = "Vehicle ID cannot be null")
    @Size(min = 7, max = 7, message = "VehicleId must have 7 characters")
    @Pattern(regexp = "^V\\d{6}$", message = "VehicleId must follow the format V000000")
    private String vehicleId;

    @NotNull(message = "Vehicle number cannot be null")
    @Size(min = 6, max = 12, message = "Vehicle Number must be between 6 and 12 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Vehicle number must be alphanumeric")
    private String vehicleNumber;

    @NotNull(message = "Coverage code cannot be null")
    private Integer coverageCode;

    @NotNull(message = "Coverage description cannot be null")
    @Size(min = 1, max = 100, message = "Coverage description must be between 1 and 100 characters")
    private String coverageDesc;

    @NotNull(message = "Coverage premium cannot be null")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Coverage premium must be a valid amount")
    private String coveragePremium;

    @NotNull(message = "Coverage limit cannot be null")
    @DecimalMin(value = "0.0", message = "Coverage limit must be greater than zero")
    private BigDecimal coverageLimit;

    @NotNull(message = "Coverage status cannot be null")
    @Size(min = 1, max = 50, message = "Coverage status must be between 1 and 50 characters")
    private String coverageStatus;

    @NotNull(message = "Effective date cannot be null")
    private LocalDate effectiveDate;

    @NotNull(message = "Expiration date cannot be null")
    private LocalDate expirationDate;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;
}
