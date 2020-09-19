package com.uwe.dissertation.ins.policybook.contact;

public class Customer {
    private final String first;
    private final String surname;
    private final int customerID;
    private static int CUSTOMER_ID_COUNTER = 0;

    public Customer(String first, String surname) {
        this.first = first;
        this.surname = surname;
        customerID = CUSTOMER_ID_COUNTER++;

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
}
