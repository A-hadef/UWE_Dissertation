package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.policy.Policy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class PolicyController {
    private final PolicyBook policyBook;

    public PolicyController(PolicyBook policyBook) {
        this.policyBook = policyBook;
    }

    public Policy createNewPolicy() {
        Policy policy = new Policy();
        policyBook.getPolicies().add(policy);
        captureRiskData(policy);
        return policy;
    }

    private void captureRiskData(Policy policy) {
        String registrationNumber = TextIOUtil.readString("Please Enter your registration number");
        Integer mileage = TextIOUtil.readInt("Please Enter the annual mileage of the vehicle");
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
        TextIOUtil.println("Polices Lists");
        for (Policy policy : policyBook.getPolicies()) {
            TextIOUtil.println(policy.toString());
        }
    }
}
