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
        
        return policy;
    }
}
