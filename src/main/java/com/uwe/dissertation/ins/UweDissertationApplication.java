package com.uwe.dissertation.ins;

import com.uwe.dissertation.ins.controller.CustomerController;
import com.uwe.dissertation.ins.controller.PolicyController;
import com.uwe.dissertation.ins.controller.options.CustomerSearchCriteria;
import com.uwe.dissertation.ins.controller.options.MenuOption;
import com.uwe.dissertation.ins.io.TextIOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.uwe.dissertation.ins.controller.options.MenuOption.FIND_AND_SELECT_CUSTOMER_BY_NAME;

@SpringBootApplication
public class UweDissertationApplication implements CommandLineRunner {

    private final CustomerController customerController;
    private final PolicyController policyController;

    public UweDissertationApplication(CustomerController customerController, PolicyController policyController) {
        this.customerController = customerController;
        this.policyController = policyController;
    }

    public static void main(String[] args) {
        SpringApplication.run(UweDissertationApplication.class, args);
    }

    @Override
    public void run(String... args) {
        typeLogin();
        MenuOption option;

        do {
            TextIOUtil.println();
            TextIOUtil.println("******************************************");
            option = TextIOUtil.readOption(MenuOption.class, "Choose option");
            TextIOUtil.println();
            handleOption(option);
        } while (option != MenuOption.EXIT);

        TextIOUtil.dispose();
    }

    private void handleOption(MenuOption option) {
        switch (option) {
            case CREATE_NEW_CONTACT:
                customerController.addNewCustomer();
                break;
            case DISPLAY_AND_SELECT_CUSTOMER:
                customerController.displayAndSelectCustomer(CustomerSearchCriteria.ALL);
                break;
            case FIND_AND_SELECT_CUSTOMER_BY_NAME:
                customerController.displayAndSelectCustomer(CustomerSearchCriteria.BY_NAME);
                break;
            case CREATE_NEW_POLICY_FOR_SELECTED_CUSTOMER:
                customerController.createNewPolicyForSelectedCustomer();
                break;
            case DISPLAY_POLICIES:
                policyController.displayPolicies();
                break;
            case EXIT:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    private void typeLogin() {
        TextIOUtil.readString("Username");
        TextIOUtil.readPassword("Password");
    }
}
