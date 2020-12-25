package com.epam.springboot.fruitshop.services;

import com.epam.springboot.fruitshop.api.v1.mapper.VendorMapper;
import com.epam.springboot.fruitshop.api.v1.model.VendorDTO;
import com.epam.springboot.fruitshop.api.v1.model.VendorListDTO;
import com.epam.springboot.fruitshop.domain.Vendor;
import com.epam.springboot.fruitshop.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class VendorServiceImplTest {

    public static final Long VENDOR1_ID = 1L;
    public static final String VENDOR1_NAME = "vendor1";
    public static final Long VENDOR2_ID = 2L;
    public static final String VENDOR2_NAME = "vendor2";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getVendorById() {
        Vendor vendor = getVendor1();

        //mockito BDD syntax
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        then(vendorRepository).should(times(1)).findById(anyLong());

        //Hamcrest Assert that with matchers
        assertThat(vendorDTO.getName(), is(equalTo(VENDOR1_NAME)));
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendorDTOS().size(), is(equalTo(2)));
    }

    @Test
    void createNewVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR1_NAME);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void saveVendorByDTO() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR1_NAME);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(VENDOR1_ID, vendorDTO);

        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void patchVendor() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR1_NAME);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.patchVendor(VENDOR1_ID, vendorDTO);

        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void deleteVendorById() {
        vendorService.deleteVendorById(1L);
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR1_NAME);
        vendor.setId(VENDOR1_ID);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR2_NAME);
        vendor.setId(VENDOR2_ID);
        return vendor;
    }
}