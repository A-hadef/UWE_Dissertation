package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Claim;
import com.uwe.dissertation.ins.policybook.contact.Conviction;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import com.uwe.dissertation.ins.policybook.contact.DrivingHistory;
import org.beryx.textio.TextIO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerController {
    private final TextIO textIO;
    private final PolicyBook policyBook;
    private final PolicyController policyController;
    private Customer selectedCustomer;

    public CustomerController(TextIO textIO, PolicyBook policyBook, PolicyController policyController) {
        this.textIO = textIO;
        this.policyBook = policyBook;
        this.policyController = policyController;
    }

    private Customer createNewCustomer() {
        String first = TextIOUtil.readString("Enter Customer First Name");
        String surname = TextIOUtil.readString("Enter Customer Surname");
        LocalDate dateOfBirth = TextIOUtil.readDate("Enter DOB");

        Customer customer = new Customer(first, surname, dateOfBirth);
        customer.setDrivingHistory(captureDrivingHistory());
        return customer;
    }

    private DrivingHistory captureDrivingHistory() {
        DrivingHistory drivingHistory = new DrivingHistory();
        drivingHistory.setConvictions(captureConviction());
        drivingHistory.setClaims(captureClaims());
        return drivingHistory;
    }

    private List<Claim> captureClaims() {
        List<Claim> claims = new ArrayList<>();
        int numberOfClaims = TextIOUtil.readInt("How many claims have you had in the last 5 years regardless of liability?");
        for (int i = 0; i < numberOfClaims; i++) {
            Claim claim = new Claim();
            claim.setDate(TextIOUtil.readDate(String.format("Please enter claim %d date", i + 1)));
            claim.setType(TextIOUtil.readString(String.format("Please enter claim %d type", i + 1)));
            claim.setAmount(TextIOUtil.readBigDecimal(String.format("Please enter claim %d amount", i + 1)));
            claims.add(claim);
        }
        return claims;
    }

    private List<Conviction> captureConviction() {
        List<Conviction> convictions = new ArrayList<>();
        int numberOfConvictions = TextIOUtil.readInt("How many motoring convictions have you had in the last 5 years?");
        for (int i = 0; i < numberOfConvictions; i++) {
            Conviction conviction = new Conviction();
            conviction.setDate(TextIOUtil.readDate(String.format("Please enter conviction %d date", i + 1)));
            conviction.setCode(TextIOUtil.readString(String.format("Please enter conviction %d code", i + 1)));
            convictions.add(conviction);
        }
        return convictions;
    }

    public void addNewCustomer() {
        Customer newCustomer = createNewCustomer();
        policyBook.getCustomers().add(newCustomer);
        selectedCustomer = newCustomer;
    }

    public void displayCustomers() {
        TextIOUtil.println("Customer Lists");
        for (Customer customer : policyBook.getCustomers()) {
            TextIOUtil.println(customer.toString());
        }
    }

    public void selectCustomerByID() {
        Integer id = textIO.newIntInputReader().read("Enter customer id to select");
        Optional<Customer> customerOptional = policyBook.getCustomers().stream().filter(c -> id.equals(c.getCustomerID())).findFirst();
        if (customerOptional.isPresent()) {
            selectedCustomer = customerOptional.get();
            textIO.getTextTerminal().printf("Selected customer %d %s %s\n", selectedCustomer.getCustomerID(), selectedCustomer.getFirst(), selectedCustomer.getSurname());
        } else {
            textIO.getTextTerminal().printf("Customer with ID '%d' not found\n", id);
        }
    }

    public void createNewPolicyForSelectedCustomer() {
        if (selectedCustomer != null) {
            selectedCustomer.getPolicies().add(policyController.createNewPolicy());
        } else {
            textIO.getTextTerminal().println("No customer selected, please create a new customer or select one by id");
        }
    }
}
