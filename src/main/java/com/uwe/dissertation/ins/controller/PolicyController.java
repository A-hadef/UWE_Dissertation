package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import com.uwe.dissertation.ins.policybook.policy.Policy;
import org.beryx.textio.TextIO;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        captureRiskData(policy);
        return policy;
    }

    private void captureRiskData(Policy policy) {
        String registrationNumber = textIO.newStringInputReader().read("Please Enter your registration number");
        Integer mileage = textIO.newIntInputReader().read("Please Enter the annual mileage of the vehicle");
        Boolean claimYesNo = TextIOUtil.readBoolean("Have you had any claims in the last 5 years regardless of fault");

        if (claimYesNo) {
            LocalDate claimDate = TextIOUtil.readDate("Can you please enter the date of the accident");
            BigDecimal claimAmount = TextIOUtil.readBigDecimal("Enter Claim Amount");
            policy.setClaimDate(claimDate);
            policy.setClaimAmount(claimAmount);
        }

        policy.setRegistrationNumber(registrationNumber);
        policy.setMileage(mileage);
        policy.setClaimYesNo(claimYesNo);

    }

    public void displayPolicies() {
        TextIOUtil.println("Polcies Lists");
        for (Policy policy : policyBook.getPolicies()) {
            TextIOUtil.println(policy.toString());
        }
    }
}
