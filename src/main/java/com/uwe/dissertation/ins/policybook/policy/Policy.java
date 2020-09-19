package com.uwe.dissertation.ins.policybook.policy;

public class Policy {
    private final int id;
    private static int POLICY_ID_COUNTER = 0;

    public Policy() {
        id = POLICY_ID_COUNTER++;
    }
}
