package com.Policy.CarInsuranceApplicationAPI.Policy.Entity;

import com.Policy.CarInsuranceApplicationAPI.Driver.Driver;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CreatePolicyPayload;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.Vehicle;

import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;
import java.util.List;

@Entity
@Table(name = "policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    @Column(unique = true, nullable = false)
    @NotNull(message = "Policy number cannot be null")
    @Size(min = 7, max = 7, message = "Policy number must have 7 characters")
    @Pattern(regexp = "^P\\d{6}$", message = "Policy number must follow the format P000000")
    private String policyNumber;

    @NotNull(message = "PolicyType cannot be null")
    private String policyType;

    @NotNull(message = "PolicyTerm cannot be null")
    private String policyTerm;

    @NotNull(message = "Policy effective date cannot be null")
    @PastOrPresent(message = "Policy effective date must be in the past or present")
    private LocalDate policyEffectiveDate;

    @NotNull(message = "Policy expiration date cannot be null")
    @Future(message = "Policy expiration date must be in the future")
    private LocalDate policyExpirationDate;

    @NotNull(message = "Coverage type cannot be null")
    private String coverageType;

    private Boolean isActive;

    @NotNull(message = "Policy status cannot be null")
    private String policyStatus;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

    @OneToOne(mappedBy = "policy", cascade = CascadeType.ALL)
    @Valid
    private Insured insured;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @Valid
    private List<Driver> drivers;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @Valid
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @Valid
    private List<Coverage> coverages;

    public Policy(CreatePolicyPayload dto) {
        this.policyNumber = dto.getPolicyNumber();
        this.policyType = dto.getPolicyType();
        this.policyTerm = dto.getPolicyTerm();
        this.policyEffectiveDate = dto.getPolicyEffectiveDate();
        this.policyExpirationDate = dto.getPolicyExpirationDate();
        this.coverageType = dto.getCoverageType();
        this.isActive = dto.getIsActive();
        this.policyStatus = dto.getPolicyStatus();
        this.createdBy = dto.getCreatedBy();
        this.createdDate = LocalDateTime.now();
        this.modifiedBy = dto.getModifiedBy();
        this.modifiedDate = LocalDateTime.now();

    }


}
