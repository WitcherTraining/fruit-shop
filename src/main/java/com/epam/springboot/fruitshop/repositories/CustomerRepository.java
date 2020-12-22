package com.epam.springboot.fruitshop.repositories;

import com.epam.springboot.fruitshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByFirstName(String name);
}
