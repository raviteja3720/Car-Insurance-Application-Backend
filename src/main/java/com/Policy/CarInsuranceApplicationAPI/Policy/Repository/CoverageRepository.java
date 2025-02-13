package com.Policy.CarInsuranceApplicationAPI.Policy.Repository;

import com.Policy.CarInsuranceApplicationAPI.Policy.Entity.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverageRepository extends JpaRepository<Coverage, Long> {
}
