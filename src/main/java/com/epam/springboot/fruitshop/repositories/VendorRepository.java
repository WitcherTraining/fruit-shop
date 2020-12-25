package com.epam.springboot.fruitshop.repositories;

import com.epam.springboot.fruitshop.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
