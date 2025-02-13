package com.Policy.CarInsuranceApplicationAPI.SequenceGenerator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceRepository extends JpaRepository<Sequence, String> {
}
