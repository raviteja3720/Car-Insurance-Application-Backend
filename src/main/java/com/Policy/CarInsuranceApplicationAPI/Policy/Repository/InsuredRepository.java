package com.Policy.CarInsuranceApplicationAPI.Policy.Repository;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Insured;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface InsuredRepository extends JpaRepository<Insured, Long> {
    boolean existsByEmailID(String emailID);

    Optional<Insured> findByFullName(@NotNull(message = "Full name cannot be null")
                                     @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
                                     String fullName
    );

    Optional<Insured> findByPolicy_PolicyNumber(@NotNull(message = "Full name cannot be null")
                                        @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
                                        String policyNumber);
}
