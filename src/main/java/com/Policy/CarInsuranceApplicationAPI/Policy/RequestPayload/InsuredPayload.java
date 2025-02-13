package com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuredPayload {

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
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNo;

    @NotNull(message = "Email ID cannot be null")
    @Email(message = "Email ID must be a valid email address")
    private String emailID;

    private String address;

    @NotNull(message = "Created by cannot be null")
    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

}
