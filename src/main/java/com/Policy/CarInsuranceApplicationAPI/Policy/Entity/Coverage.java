package com.Policy.CarInsuranceApplicationAPI.Policy.Entity;

import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CoveragePayload;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "coverages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policyNumber", nullable = false)
    @NotNull(message = "Policy cannot be null")
    private Policy policy;

    @NotNull(message = "Vehicle ID cannot be null")
    private String vehicleId;

    @NotNull(message = "Vehicle Number cannot be null")
    private String vehicleNumber;

    @NotNull(message = "Coverage code cannot be null")
    private Integer coverageCode;

    @NotNull(message = "Coverage description cannot be null")
    @Size(min = 5, max = 255, message = "Coverage description must be between 5 and 255 characters")
    private String coverageDesc;

    @NotNull(message = "Coverage premium cannot be null")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Coverage premium must be a valid decimal number")
    private String coveragePremium;

    @NotNull(message = "Coverage limit cannot be null")
    @DecimalMin(value = "0.00", inclusive = true, message = "Coverage limit cannot be negative")
    private BigDecimal coverageLimit;

    @NotNull(message = "Coverage status cannot be null")
    @Size(min = 2, max = 50, message = "Coverage status must be between 2 and 50 characters")
    private String coverageStatus;

    @NotNull(message = "Effective date cannot be null")
    @PastOrPresent(message = "Effective date must be in the past or present")
    private LocalDate effectiveDate;

    @NotNull(message = "Expiration date cannot be null")
    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

    // Constructor to map payload to entity
    public Coverage(CoveragePayload dto, Policy policy) {
        this.vehicleId = dto.getVehicleId();
        this.coverageCode = dto.getCoverageCode();
        this.coverageDesc = dto.getCoverageDesc();
        this.coveragePremium = dto.getCoveragePremium();
        this.coverageLimit = dto.getCoverageLimit();
        this.coverageStatus = dto.getCoverageStatus();
        this.effectiveDate = dto.getEffectiveDate();
        this.expirationDate = dto.getExpirationDate();
        this.createdBy = dto.getCreatedBy();
        this.createdDate = LocalDateTime.now();
        this.modifiedBy = dto.getModifiedBy();
        this.modifiedDate = LocalDateTime.now();
        this.policy = policy;
    }
}
