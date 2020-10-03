package com.uwe.dissertation.ins.policybook.policy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Policy {
    private final int id;
    private static int POLICY_ID_COUNTER = 0;
    private String registrationNumber;
    private Integer mileage;
    private Boolean claimYesNo;
    private LocalDate claimDate;
    private BigDecimal claimAmount;

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

    public Boolean getClaimYesNo() {
        return claimYesNo;
    }

    public void setClaimYesNo(Boolean claimYesNo) {
        this.claimYesNo = claimYesNo;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }
    @Override
    public String toString() {
        return String.format("ID:%d RegistrationNumber:%s Mileage:%s ", id, registrationNumber, mileage);
    }
}
