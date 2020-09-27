package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import org.beryx.textio.TextIO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerController {
    private final TextIO textIO;
    private final PolicyBook policyBook;
    private final PolicyController policyController;
    private Customer selectedCustomer;

    public CustomerController(TextIO textIO, PolicyBook policyBook) {
        this.textIO = textIO;
        this.policyBook = policyBook;
        policyController = new PolicyController(textIO, policyBook);
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

        return new Customer(first, surname, dateOfBirth, drivingConviction, drivingConvictionCode);
    }

    public void addNewCustomer() {
        Customer newCustomer = createNewCustomer();
        policyBook.getCustomers().add(newCustomer);
        selectedCustomer = newCustomer;
    }

    public void displayCustomers() {
        TextIOUtil.println("Customer Lists");
//        textIO.getTextTerminal().println("Customer Lists");
        for (Customer customer : policyBook.getCustomers()) {
            TextIOUtil.println(customer.toString());
//            textIO.getTextTerminal().printf("ID:%d FirstName:%s Surname:%s\n", customer.getCustomerID(), customer.getFirst(), customer.getSurname());
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
