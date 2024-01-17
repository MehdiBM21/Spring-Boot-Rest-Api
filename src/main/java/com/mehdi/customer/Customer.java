package com.mehdi.customer;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name="customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="customer_sequence"
    )
    private Integer id;
    private String name;
    private String email;
    private LocalDate birthday;
    @Transient
    private Integer age;

    public Integer getAge(){
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }

    public Customer(Integer id, String name, String email,LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday= birthday;
        this.age = age;
    }
    public Customer( String name, String email,LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.birthday=birthday;
        this.age = age;
    }
    public Customer(){

    }

}
