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
    private DrivingHistory drivingHistory;

    public Customer(String first, String surname, LocalDate dateOfBirth) {
        customerID = CUSTOMER_ID_COUNTER++;
        this.first = first;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
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

    public int getCustomerID() {
        return customerID;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format("ID:%d FirstName:%s Surname:%s DateOfBirth:%s Driving history:%s", customerID, first, surname, dateOfBirth, drivingHistory.toString());
    }

}
