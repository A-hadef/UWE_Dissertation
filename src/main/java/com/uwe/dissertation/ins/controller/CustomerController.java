package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import org.beryx.textio.TextIO;

public class CustomerController {
    private final TextIO textIO;
    private final PolicyBook policyBook;


    public CustomerController(TextIO textIO, PolicyBook policyBook) {
        this.textIO = textIO;
        this.policyBook = policyBook;
    }

    private Customer createNewCustomer() {
        String first = textIO.newStringInputReader().read("Enter Customer First Name");
        String surname = textIO.newStringInputReader().read("Enter Customer  Surname");
        return new Customer(first, surname);
    }

    public void addNewCustomer() {
        policyBook.getCustomers().add(createNewCustomer());
    }

    public void displayCustomers() {

        textIO.getTextTerminal().println("Customer Lists");
        for (Customer customer : policyBook.getCustomers()) {
            textIO.getTextTerminal().printf("ID:%d FirstName:%s Surname:%s\n",customer.getCustomerID(),customer.getFirst(), customer.getSurname());
        }
    }
}
