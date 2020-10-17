package com.uwe.dissertation.ins.policybook.contact;

import com.uwe.dissertation.ins.policybook.policy.Policy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    @Id
    private String id;
    private final String first;
    private final String surname;
    private LocalDate dateOfBirth;
    private DrivingHistory drivingHistory;
    @DBRef
    private List<Policy> policies;

    public Customer(String first, String surname) {
        this.first = first;
        this.surname = surname;
        policies = new ArrayList<>();
    }

    public void setDrivingHistory(DrivingHistory drivingHistory) {
        this.drivingHistory = drivingHistory;
    }

    public String getFirst() {
        return first;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    @Override
    public String toString() {
        return String.format("ID:%s FirstName:%s Surname:%s DateOfBirth:%s Driving history:%s", id, first, surname, dateOfBirth, drivingHistory.toString());
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
