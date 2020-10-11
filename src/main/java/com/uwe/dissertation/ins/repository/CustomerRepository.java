package com.uwe.dissertation.ins.repository;

import com.uwe.dissertation.ins.policybook.contact.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

    Customer findByFirst(String first);
    List<Customer> findBySurname(String surname);

}
