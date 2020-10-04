package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Claim;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import org.beryx.textio.TextIO;

import java.math.BigDecimal;
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
        boolean drivingConviction = TextIOUtil.readBoolean("Have you had any driving convictions?");
        List<String> drivingConvictionCode = null;
        if (drivingConviction) {
            drivingConvictionCode = TextIOUtil.readList("Enter driving conviction code");
        }
        int numberOfClaims = TextIOUtil.readInt("How many claims have you had in the last 5 years regardless of liability");
        List<Claim> claims = new ArrayList<>();
        LocalDate claimDate = null;
        BigDecimal claimAmount = null;
        if (numberOfClaims) {
            claimDate = TextIOUtil.readDate("Can you please enter the date of the accident");
            claimAmount = TextIOUtil.readBigDecimal("Enter Claim Amount");

        }

        return new Customer(first, surname, dateOfBirth, drivingConviction, drivingConvictionCode, numberOfClaims,claimDate,claimAmount);
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
