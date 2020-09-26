package com.uwe.dissertation.ins.policybook.contact;

import com.uwe.dissertation.ins.policybook.policy.Policy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String first;
    private final String surname;
    private final int customerID;
    private static int CUSTOMER_ID_COUNTER = 0;
    private final List<Policy> policies;
    private final LocalDate dateOfBirth;
    private final Boolean drivingConviction;
    private final String drivingConvictionCode;

    public Customer(String first, String surname, LocalDate dateOfBirth, Boolean drivingConviction, String drivingConvictionCode) {
        this.first = first;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.drivingConviction = drivingConviction;
        this.drivingConvictionCode = drivingConvictionCode;
        customerID = CUSTOMER_ID_COUNTER++;
        policies = new ArrayList<>();
    }

    public String getFirst() {
        return first;
    }

    public String getSurname() {
        return surname;
    }

    public int getCustomerID() {
        return customerID;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getDrivingConviction() {
        return drivingConviction;
    }

    public String getDrivingConvictionCode() {
        return drivingConvictionCode;
    }
}
