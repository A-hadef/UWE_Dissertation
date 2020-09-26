package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import org.beryx.textio.TextIO;

import java.time.LocalDate;
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
        String drivingConvictionCode = TextIOUtil.readString("Enter driving conviction code");

//        String first = textIO.newStringInputReader().read("Enter Customer First Name");
//        String surname = textIO.newStringInputReader().read("Enter Customer  Surname");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        Boolean drivingConviction = Boolean.valueOf(textIO.newStringInputReader().read("Have you had any driving convictions?"));
//        LocalDate dateOfBirth = LocalDate.parse(textIO.newStringInputReader().read("Enter DOB in the format 'DD/MM/YYYY'"), formatter);
//        String drivingConvictionCode = textIO.newStringInputReader().read("Enter driving conviction code");
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
            TextIOUtil.println("ID:%d FirstName:%s Surname:%s", customer.getCustomerID(), customer.getFirst(), customer.getSurname());
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
