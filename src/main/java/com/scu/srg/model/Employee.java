package com.scu.srg.model;

import lombok.Getter;

@Getter
public class Employee {

    String name;
    String email;
    EmployeeType status;

    public Employee(String name, String email, EmployeeType status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }
}
