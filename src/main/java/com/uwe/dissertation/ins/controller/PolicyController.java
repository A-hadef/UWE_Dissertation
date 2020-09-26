package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.policy.Policy;
import org.beryx.textio.TextIO;

public class PolicyController {
    private final TextIO textIO;
    private final PolicyBook policyBook;

    public PolicyController(TextIO textIO, PolicyBook policyBook) {
        this.textIO = textIO;
        this.policyBook = policyBook;
    }

    public Policy createNewPolicy() {
        Policy policy = new Policy();
        policyBook.getPolicies().add(policy);
        captureRiskData (policy);
        return policy;
    }

    private void captureRiskData(Policy policy) {
        String registrationNumber = textIO.newStringInputReader().read("Please Enter your registration number");
        Integer mileage = textIO.newIntInputReader().read("Please Enter the annual mileage of the vehicle");
        policy.setRegistrationNumber(registrationNumber);
        policy.setMileage(mileage);
    }
}
