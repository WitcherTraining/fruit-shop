package com.epam.springboot.fruitshop.services;

import com.epam.springboot.fruitshop.api.v1.mapper.CustomerMapper;
import com.epam.springboot.fruitshop.api.v1.model.CustomerDTO;
import com.epam.springboot.fruitshop.bootstrap.Bootstrap;
import com.epam.springboot.fruitshop.domain.Customer;
import com.epam.springboot.fruitshop.repositories.CategoryRepository;
import com.epam.springboot.fruitshop.repositories.CustomerRepository;
import com.epam.springboot.fruitshop.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run(); // load data

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    private Long getCustomerIDValue() {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers found: " + customers.size());

        //return id of first customer
        return customers.get(0).getId();
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";
        Long id = getCustomerIDValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void patchCustomerLastName() {
        String updatedLastName = "UpdatedLastName";
        Long id = getCustomerIDValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedLastName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedLastName, updatedCustomer.getLastName());
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
    }




















}
