package com.uwe.dissertation.ins.controller;

import com.uwe.dissertation.ins.io.TextIOUtil;
import com.uwe.dissertation.ins.policybook.contact.Claim;
import com.uwe.dissertation.ins.policybook.contact.Conviction;
import com.uwe.dissertation.ins.policybook.contact.Customer;
import com.uwe.dissertation.ins.policybook.contact.DrivingHistory;
import com.uwe.dissertation.ins.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerController {
    private final PolicyController policyController;
    private Customer selectedCustomer;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerController(PolicyController policyController) {
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
//        policyBook.getCustomers().add(newCustomer);
        customerRepository.save(newCustomer);
        selectedCustomer = newCustomer;
    }

    public void displayAndSelectCustomer() {
        selectedCustomer = null;
        TextIOUtil.println("Customer Lists");

        List<Customer> customers = customerRepository.findAll(Sort.sort(Customer.class).by(Customer::getSurname).ascending());
        for (int i = 0; i < customers.size(); i++) {
            TextIOUtil.println("index:%d %s", i, customers.get(i).toString());
        }

        getCustomerByIndex(customers);
    }

    private void getCustomerByIndex(List<Customer> customers) {
        do {
            int index = TextIOUtil.readInt("Enter the index of the customer to select or -1 to exit this menu");
            if (index < 0) {
                break;
            }
            if (index > customers.size() - 1) {
                TextIOUtil.println(String.format("index '%d' is not within the list, try harder", index));
            } else {
                selectedCustomer = getCustomerFromDataBase(customers.get(index));
            }
        } while (selectedCustomer == null);
    }

    private Customer getCustomerFromDataBase(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findOne(Example.of(customer));
        if (!customerOptional.isPresent()) {
            TextIOUtil.println("unable to select customer with index '%d', please try another");
            return null;
        } else {
            return customerOptional.get();
        }
    }

//    public void selectCustomerByID() {
//        Integer id = TextIOUtil.readInt("Enter customer id to select");
////        Optional<Customer> customerOptional = policyBook.getCustomers().stream().filter(c -> id.equals(c.getId())).findFirst();
//        Optional<Customer> customerOptional = customerRepository.findById(id);
//        if (customerOptional.isPresent()) {
//            selectedCustomer = customerOptional.get();
//            TextIOUtil.println("Selected customer %s %s %s\n", selectedCustomer.getId(), selectedCustomer.getFirst(), selectedCustomer.getSurname());
//        } else {
//            TextIOUtil.println("Customer with ID '%d' not found\n", id);
//        }
//    }

    public void createNewPolicyForSelectedCustomer() {
        Optional<Customer> customerOptional;
        if (selectedCustomer != null && (customerOptional = customerRepository.findOne(Example.of(selectedCustomer))).isPresent()) {
            Customer customer = customerOptional.get();
            customer.getPolicies().add(policyController.createNewPolicy());
            customerRepository.save(customer);
//            selectedCustomer.getPolicies().add(policyController.createNewPolicy());
        } else {
            TextIOUtil.println("No customer selected, please create a new customer or select one by id");
        }
    }
}
