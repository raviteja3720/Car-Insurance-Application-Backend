package com.Policy.CarInsuranceApplicationAPI.SequenceGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sequence")
@NoArgsConstructor
@AllArgsConstructor
public class Sequence {

    @Id
    @Column(name = "sequence_type", nullable = false, unique = true)
    private String sequenceType;

    @Column(name = "sequence_value", nullable = false)
    private int value;


    public String getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}