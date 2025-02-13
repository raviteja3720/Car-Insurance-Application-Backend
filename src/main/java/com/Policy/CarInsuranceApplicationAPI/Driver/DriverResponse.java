package com.Policy.CarInsuranceApplicationAPI.Driver;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponse {
    private String driverId;
    private String firstName;
    private String lastName;
    private String driverLicenseId;
    private LocalDate licenseEffectiveDate;
    private LocalDate licenseExpirationDate;
    private LocalDate driverEffectiveDate;
    private LocalDate driverExpirationDate;
    private String licensedCurrentState;
    private String gender;
    private LocalDate driverDOB;
    private Boolean isInsured;
    private Boolean isActive;

    public DriverResponse(Driver dto) {

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
//        this.policy = policy;
    }
}
