package com.uwe.dissertation.ins.policybook.policy;

public class Policy {
    private final int id;
    private static int POLICY_ID_COUNTER = 0;
    private String registrationNumber;
    private Integer mileage;

    public Policy() {
        id = POLICY_ID_COUNTER++;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getMileage() {
        return mileage;
    }
}
