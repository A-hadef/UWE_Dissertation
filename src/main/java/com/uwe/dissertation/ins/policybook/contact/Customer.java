package com.uwe.dissertation.ins.policybook.contact;

import com.uwe.dissertation.ins.policybook.policy.Policy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String first;
    private final String surname;
    private final int customerID;
    private static int CUSTOMER_ID_COUNTER = 0;
    private final List<Policy> policies;
    private final LocalDate dateOfBirth;
    private Boolean drivingConviction;
    private List<String> drivingConvictionCode;
    private Integer numberOfClaims;
    private LocalDate claimDate;
    private BigDecimal claimAmount;
    private DrivingHistory drivingHistory;

//    public Customer(String first, String surname, LocalDate dateOfBirth, Boolean drivingConviction, List<String> drivingConvictionCode, Integer numberOfClaims, LocalDate claimDate, BigDecimal claimAmount) {
//        this.first = first;
//        this.surname = surname;
//        this.dateOfBirth = dateOfBirth;
//        this.drivingConviction = drivingConviction;
//        this.drivingConvictionCode = drivingConvictionCode;
//        this.numberOfClaims = numberOfClaims;
//        this.claimDate = claimDate;
//        this.claimAmount = claimAmount;
//        customerID = CUSTOMER_ID_COUNTER++;
//        policies = new ArrayList<>();
//    }

    public Customer(String first, String surname, LocalDate dateOfBirth) {
        customerID = CUSTOMER_ID_COUNTER++;
        this.first = first;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        policies = new ArrayList<>();
    }
    
    public void setDrivingHistory(DrivingHistory drivingHistory) {
        this.drivingHistory = drivingHistory;
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

    public List<Policy> getPolicies() {
        return policies;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getDrivingConviction() {
        return drivingConviction;
    }

    public List<String> getDrivingConvictionCode() {
        return drivingConvictionCode;
    }

    @Override
    public String toString() {
        return String.format("ID:%d FirstName:%s Surname:%s DateOfBirth:%s Driving history:%s", customerID, first, surname, dateOfBirth, drivingHistory.toString());
    }

    public Integer getNumberOfClaims() {
        return numberOfClaims;
    }

    public void setNumberOfClaims(Integer numberOfClaims) {
        this.numberOfClaims = numberOfClaims;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }
}
