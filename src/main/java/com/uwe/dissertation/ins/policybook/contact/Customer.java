package com.uwe.dissertation.ins.policybook.contact;

import com.uwe.dissertation.ins.policybook.policy.Policy;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String first;
    private final String surname;
    private final int customerID;
    private static int CUSTOMER_ID_COUNTER = 0;
    private final List<Policy> policies;

    public Customer(String first, String surname) {
        this.first = first;
        this.surname = surname;
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
}
