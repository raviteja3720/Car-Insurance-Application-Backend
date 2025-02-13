package com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Coverage;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.CoveragePayload;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoverageResponse {
    private String vehicleId;
    private String vehicleNumber;
    private Integer coverageCode;
    private String coverageDesc;
    private String coveragePremium;
    private BigDecimal coverageLimit;
    private String coverageStatus;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;

    public CoverageResponse(Coverage dto) {
        this.vehicleId = dto.getVehicleId();
        this.vehicleNumber = dto.getVehicleNumber();
        this.coverageCode = dto.getCoverageCode();
        this.coverageDesc = dto.getCoverageDesc();
        this.coveragePremium = dto.getCoveragePremium();
        this.coverageLimit = dto.getCoverageLimit();
        this.coverageStatus = dto.getCoverageStatus();
        this.effectiveDate = dto.getEffectiveDate();
        this.expirationDate = dto.getExpirationDate();

    }
}
