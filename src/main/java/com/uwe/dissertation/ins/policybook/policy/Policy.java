package com.uwe.dissertation.ins.policybook.policy;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Policy {
    @Id
    private String id;
    private String registrationNumber;
    private Integer mileage;
    private Boolean claimYesNo;
    private LocalDate claimDate;
    private BigDecimal claimAmount;

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
        return String.format("ID:%s RegistrationNumber:%s Mileage:%s ", id, registrationNumber, mileage);
    }
}
