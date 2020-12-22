package com.epam.springboot.fruitshop.services;

import com.epam.springboot.fruitshop.api.v1.mapper.CustomerMapper;
import com.epam.springboot.fruitshop.api.v1.model.CategoryDTO;
import com.epam.springboot.fruitshop.api.v1.model.CustomerDTO;
import com.epam.springboot.fruitshop.domain.Customer;
import com.epam.springboot.fruitshop.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Roman";
    public static final String LAST_NAME = "Lunev";
    public static final String CUSTOMER_URL = "api/customers/1";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {

        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        assertEquals(2, customerDTOList.size());
    }

    @Test
    void getCustomerByFirstName() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findByFirstName(NAME)).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByFirstName(NAME);

        assertEquals("Roman", customerDTO.getFirstName());
    }
}