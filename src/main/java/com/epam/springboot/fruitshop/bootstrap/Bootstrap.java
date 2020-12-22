package com.epam.springboot.fruitshop.bootstrap;

import com.epam.springboot.fruitshop.domain.Category;
import com.epam.springboot.fruitshop.domain.Customer;
import com.epam.springboot.fruitshop.repositories.CategoryRepository;
import com.epam.springboot.fruitshop.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();
    }

    private void loadCustomers() {
        Customer roma = new Customer();
        roma.setId(1L);
        roma.setFirstName("Roman");
        roma.setLastName("Pushkin");

        Customer dima = new Customer();
        dima.setId(2L);
        dima.setFirstName("Dmitriy");
        dima.setLastName("Krotov");

        Customer lena = new Customer();
        lena.setId(3L);
        lena.setFirstName("Elena");
        lena.setLastName("Koneva");

        customerRepository.save(roma);
        customerRepository.save(dima);
        customerRepository.save(lena);

        System.out.println("Data loaded customers: " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data loaded Categories: " + categoryRepository.count());
    }
}
