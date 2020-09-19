package com.uwe.dissertation.ins.policybook;

import com.uwe.dissertation.ins.policybook.contact.Customer;
import com.uwe.dissertation.ins.policybook.policy.Policy;

import java.util.ArrayList;
import java.util.List;

public class PolicyBook {
    private final List<Customer> customers;
    private final List<Policy> policies;

    public PolicyBook() {
        customers = new ArrayList<>();
        policies = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Policy> getPolicies() {
        return policies;
    }
}
