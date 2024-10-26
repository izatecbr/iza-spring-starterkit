package com.iza.modules.formalization.customer;

import com.iza.modules.formalization.customer.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
