package com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO;

import com.Policy.CarInsuranceApplicationAPI.Driver.DriverResponse;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Vehicle.VehicleResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponse {
    private String policyNumber;
    private String policyType;
    private String policyTerm;
    private LocalDate policyEffectiveDate;
    private LocalDate policyExpirationDate;
    private String coverageType;
    private Boolean isActive;
    private String policyStatus;
    private String createdBy;

    private InsuredResponse insured;
    private List<DriverResponse> drivers;
    private List<VehicleResponse> vehicles;
    private List<CoverageResponse> coverages;

    public PolicyResponse(Policy dto){
        this.policyNumber = dto.getPolicyNumber();
        this.policyType = dto.getPolicyType();
        this.policyTerm = dto.getPolicyTerm();
        this.policyEffectiveDate = dto.getPolicyEffectiveDate();
        this.policyExpirationDate = dto.getPolicyExpirationDate();
        this.coverageType = dto.getCoverageType();
        this.isActive = dto.getIsActive();
        this.policyStatus = dto.getPolicyStatus();
        this.createdBy = dto.getCreatedBy();
    }
}
