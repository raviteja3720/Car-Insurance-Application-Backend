package com.Policy.CarInsuranceApplicationAPI.Policy.Entity;

import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.InsuredPayload;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "insured_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insured {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "policyNumber", nullable = false)
    @NotNull(message = "Policy cannot be null")
    private Policy policy;

    @NotNull(message = "Insured type cannot be null")
    private String insuredType;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Full name cannot be null")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNo;

    @NotNull(message = "Email ID cannot be null")
    @Email(message = "Email must be a valid email address")
    private String emailID;

    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
    private String address;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;


    public Insured(InsuredPayload dto, Policy policy) {
        this.insuredType = dto.getInsuredType();
        this.fullName = dto.getFullName();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.phoneNo = dto.getPhoneNo();
        this.emailID = dto.getEmailID();
        this.createdBy = dto.getCreatedBy();
        this.createdDate = LocalDateTime.now();
        this.modifiedBy = dto.getModifiedBy();
        this.modifiedDate = LocalDateTime.now();
        this.policy = policy;
    }
}
