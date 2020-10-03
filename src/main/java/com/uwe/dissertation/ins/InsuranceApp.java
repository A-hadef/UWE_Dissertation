package com.uwe.dissertation.ins;

import com.uwe.dissertation.ins.controller.CustomerController;
import com.uwe.dissertation.ins.controller.PolicyController;
import com.uwe.dissertation.ins.controller.options.MenuOption;
import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.PolicyBook;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class InsuranceApp {

    private final TextIO textIO;
    private final CustomerController customerController;
    private final PolicyBook policyBook;
    private final PolicyController policyController;

    public InsuranceApp() {
        textIO = TextIoFactory.getTextIO();
        policyBook = new PolicyBook();
        policyController = new PolicyController(textIO, policyBook);
        customerController = new CustomerController(textIO, policyBook,policyController);
    }

    public static void main(String[] args) {
        new InsuranceApp().run();
    }

    private void run() {
        typeLogin();
        MenuOption option;

        do {
            TextIOUtil.println();
            TextIOUtil.println("******************************************");
            option = TextIOUtil.readOption(MenuOption.class, "Choose option");
            TextIOUtil.println();
            handleOption(option);
        } while (option != MenuOption.EXIT);

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
            case DISPLAY_POLICIES:
                policyController.displayPolicies();
        }
    }


    private void typeLogin() {
        TextIOUtil.readString("Username");
        TextIOUtil.readPassword("Password");

//        String user = textIO.newStringInputReader()
//                .withDefaultValue("admin")
//                .read("Username");
//        String password = textIO.newStringInputReader()
//                .withMinLength(6)
//                .withInputMasking(true)
//                .read("Password");
    }
}
