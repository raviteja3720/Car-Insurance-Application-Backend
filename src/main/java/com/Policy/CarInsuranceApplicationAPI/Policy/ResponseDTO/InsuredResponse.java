package com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Insured;
import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.InsuredPayload;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuredResponse {

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String fullName;
    private String emailID;
    private String insuredType;
    private String address;

    public InsuredResponse(Insured dto, Policy policy) {
        this.insuredType = dto.getInsuredType();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.fullName = dto.getFullName();
        this.phoneNo = dto.getPhoneNo();
        this.emailID = dto.getEmailID();
        this.address = dto.getAddress();
//        this.policy = policy;
    }

}
