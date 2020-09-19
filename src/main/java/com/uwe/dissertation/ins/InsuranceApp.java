package com.uwe.dissertation.ins;

import com.uwe.dissertation.ins.controller.CustomerController;
import com.uwe.dissertation.ins.controller.options.MenuOption;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class InsuranceApp {

    private final TextIO textIO;
    private final CustomerController customerController;
    private final PolicyBook policyBook;

    public InsuranceApp() {
        textIO = TextIoFactory.getTextIO();
        policyBook = new PolicyBook();
        customerController = new CustomerController(textIO, policyBook);
    }

    public static void main(String[] args) {
        new InsuranceApp().run();
    }

    private void run() {
        typeLogin();
        MenuOption option;
        
        do {
            textIO.getTextTerminal().println();
            textIO.getTextTerminal().println("******************************************");
            option = textIO.newEnumInputReader(MenuOption.class).read("Choose option");
            textIO.getTextTerminal().println();
            handleOption(option);
        } while(option != MenuOption.EXIT);
        
        textIO.dispose();
    }

    private void handleOption(MenuOption option) {
        switch (option) {
            case CREATE_NEW_CONTACT:
                customerController.addNewCustomer();
                break;
            case DISPLAY_CONTACTS:
                customerController.displayCustomers();
                break;
            case SELECT_CUSTOMER_BY_ID:
                customerController.selectCustomerByID();
                break;
            case CREATE_NEW_POLICY_FOR_SELECTED_CUSTOMER:
                customerController.createNewPolicyForSelectedCustomer();
                break;
        }
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
