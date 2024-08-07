package com.mlk.criteria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;

    private String streetName;

    private String houseNumber;

    private String zipCode;

    // @OneToOne
    // @JoinColumn(name = "employee_id")
    // private Employee employee;

}