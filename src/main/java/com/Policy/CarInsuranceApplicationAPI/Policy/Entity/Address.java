//package com.Policy.CarInsuranceApplicationAPI.Policy.Entity;
//
//import com.Policy.CarInsuranceApplicationAPI.Policy.RequestPayload.AddressPayload;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "addresses")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "insured_id", nullable = false)
//    private Insured insured;
//
//    private Boolean isPrimary;
//    private String street;
//    private String city;
//    private String state;
//    private String pinCode;
//
//    private String createdBy;
//    private LocalDateTime createdDate;
//    private String modifiedBy;
//    private LocalDateTime modifiedDate;
//
//    public Address(AddressPayload dto, Policy policy) {
//        this.isPrimary = dto.getIsPrimary();
////
//        this.street = dto.getStreet();
//        this.city = dto.getCity();
//        this.state = dto.getState();
//        this.pinCode = dto.getPinCode();
//
//        this.createdBy = dto.getCreatedBy();
//        this.createdDate = LocalDateTime.now();
//        this.modifiedBy = dto.getModifiedBy();
//        this.modifiedDate = LocalDateTime.now();
////        this.insured=policy.getInsured();
//
//    }
//}
