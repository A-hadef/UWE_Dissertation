package com.uwe.dissertation.ins;

import com.uwe.dissertation.ins.policybook.PolicyBook;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import com.uwe.dissertation.ins.controller.CustomerController;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.util.ArrayList;
import java.util.List;

public class InsuranceApp {

    private final TextIO textIO;
    private final CustomerController customerController;
    private final PolicyBook policyBook;

    public InsuranceApp() {
        textIO = TextIoFactory.getTextIO();
        policyBook = new PolicyBook();
        customerController = new CustomerController(textIO,policyBook);
    }

    public static void main(String[] args) {
        new InsuranceApp().run();
//        String firstName = textIO.newStringInputReader().read("Type First Name");
//        textIO.getTextTerminal().print(firstName);
    }

    private void run() {

        typeLogin();
        customerController.addNewCustomer();
        customerController.displayCustomers();
    }



    private void typeLogin() {
        String user = textIO.newStringInputReader()
                .withDefaultValue("admin")
                .read("Username");
        String password = textIO.newStringInputReader()
                .withMinLength(6)
                .withInputMasking(true)
                .read("Password");
    }
}
