package com.Policy.CarInsuranceApplicationAPI.Policy.Repository;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>, JpaSpecificationExecutor<Policy> {
    Boolean existsByPolicyNumber(String policyNumber);

    Policy findByPolicyNumber(String policyNumber);

//    @Query("SELECT p FROM Policy p "
//            + "WHERE (:policyNumber IS NULL OR p.policyNumber = :policyNumber)  "
//            + "AND (:insuredName IS NULL OR i.firstName LIKE %:insuredName% OR i.lastName LIKE %:insuredName% OR i.fullName LIKE %:insuredName%) "
//            + "AND (:vehicleNumber IS NULL OR v.vehicleNumber = :vehicleNumber)  "
//            + "AND (:policyType IS NULL OR p.policyType = :policyType) "
//            + "AND (cast(:effectiveDate as date) IS NULL OR p.effectiveDate >= :effectiveDate)"
//    )
//
//    List<Policy> searchPolicyDetails(@Param("policyNumber") String policyNumber,
//                                         @Param("insuredName") String insuredName,
//                                         @Param("vehicleNumber") String vehicleNumber,
//                                         @Param("policyType") String policyType,
//                                         @Param("effectiveDate") LocalDate effectiveDate
//    );

//    @Query("SELECT new com.Policy.CarInsuranceApplicationAPI.Policy.ResponseDTO.PolicyResponse(" +
//            "p, " +
//            "(SELECT v FROM Vehicle v WHERE v.policy.policyNumber = :policyNumber), " +
//            "(SELECT d FROM Driver d WHERE d.policy.policyNumber = :policyNumber), " +
//            "(SELECT c FROM Coverage c WHERE c.policy.policyNumber = :policyNumber)) " +
//            "FROM Policy p " +
//            "LEFT JOIN FETCH p.insured i " +
//            "WHERE (:policyNumber IS NULL OR p.policyNumber = :policyNumber) " +
//            "AND (:insuredName IS NULL OR i.firstName LIKE %:insuredName% OR i.lastName LIKE %:insuredName% OR i.fullName LIKE %:insuredName%) " +
//            "AND (:policyType IS NULL OR p.policyType = :policyType) " +
//            "AND (cast(:effectiveDate as date) is null OR p.policyEffectiveDate >= cast(:effectiveDate as date))")
//    List<PolicyResponse> searchPolicyDetails(@Param("policyNumber") String policyNumber,
//                                                   @Param("insuredName") String insuredName,
//                                                   @Param("policyType") String policyType,
//                                                   @Param("effectiveDate") LocalDate effectiveDate);


//    @Query("SELECT DISTINCT p FROM Policy p " +
//            "INNER JOIN  p.insured i " +
//            "INNER JOIN  p.vehicles v " +
//            "INNER JOIN  p.drivers d " +
//            "INNER JOIN  p.coverages c " +
//            "WHERE (:policyNumber IS NULL OR p.policyNumber = :policyNumber) " +
//            "AND (:insuredName IS NULL OR i.firstName LIKE %:insuredName% OR i.lastName LIKE %:insuredName% OR i.fullName LIKE %:insuredName%) " +
//            "AND (:vehicleNumber IS NULL OR v.vehicleNumber = :vehicleNumber) " +
//            "AND (:policyType IS NULL OR p.policyType = :policyType) " +
//            "AND (cast(:effectiveDate as date) is null  OR p.policyEffectiveDate >= cast(:effectiveDate as date))"
////            "AND (:effectiveDate IS NULL  OR p.policyEffectiveDate = :effectiveDate)"
//    )
//    List<Policy> searchPolicyDetails(@Param("policyNumber") String policyNumber,
//                                         @Param("insuredName") String insuredName,
//                                         @Param("vehicleNumber") String vehicleNumber,
//                                         @Param("policyType") String policyType,
//                                         @Param("effectiveDate") LocalDate effectiveDate
//    );


//    @Query(value = "SELECT * FROM policies p WHERE (:policyNumber IS NULL OR p.policy_number = :policyNumber) " +
//            "AND (:insuredName IS NULL OR p.insured_name LIKE %:insuredName%) " +
//            "AND (:vehicleNumber IS NULL OR EXISTS (SELECT 1 FROM vehicles v WHERE v.policy_number = p.policy_number AND v.vehicle_number = :vehicleNumber)) " +
//            "AND (:policyType IS NULL OR p.policy_type = :policyType) " +
//            "AND (:effectiveDate IS NULL OR p.policy_effective_date >= :effectiveDate)", nativeQuery = true)
//    List<Policy> searchPolicies(@Param("policyNumber") String policyNumber,
//                                @Param("insuredName") String insuredName,
//                                @Param("vehicleNumber") String vehicleNumber,
//                                @Param("policyType") String policyType,
//                                @Param("effectiveDate") LocalDate effectiveDate);
}
