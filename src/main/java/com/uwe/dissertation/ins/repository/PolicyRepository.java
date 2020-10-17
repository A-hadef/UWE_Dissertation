package com.uwe.dissertation.ins.repository;

import com.uwe.dissertation.ins.policybook.policy.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PolicyRepository extends MongoRepository<Policy, String> {
}
