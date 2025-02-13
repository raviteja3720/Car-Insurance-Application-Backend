package com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicySearchRequest {
    private String policyNumber;
    private String insuredName;
//    private String driverName;
    private String vehicleNumber;
    private String policyType;

    @PastOrPresent(message = "Effective date must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;
}
