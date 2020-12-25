package com.epam.springboot.fruitshop.services;

import com.epam.springboot.fruitshop.api.v1.mapper.VendorMapper;
import com.epam.springboot.fruitshop.api.v1.model.VendorDTO;
import com.epam.springboot.fruitshop.api.v1.model.VendorListDTO;
import com.epam.springboot.fruitshop.domain.Vendor;
import com.epam.springboot.fruitshop.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.springboot.fruitshop.controllers.v1.VendorController.BASE_URL;

@Service
public class VendorServiceImpl implements VendorService {


    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private String getVendorUrl(Long id) {
        return BASE_URL + id;
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendorToSave = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        return vendorRepository.findById(id).map(vendor -> {
            //todo if more properties, add more if statements
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            return saveAndReturnDTO(vendor);
        })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        vendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return vendorDTO;
    }
}
