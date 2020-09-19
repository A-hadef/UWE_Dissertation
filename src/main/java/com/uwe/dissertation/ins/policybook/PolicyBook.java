package com.uwe.dissertation.ins.policybook;

import com.uwe.dissertation.ins.policybook.contact.Customer;

import java.util.ArrayList;
import java.util.List;

public class PolicyBook {
    private final List <Customer> customers;

    public PolicyBook() {
        customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
