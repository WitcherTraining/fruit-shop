package com.epam.springboot.fruitshop.api.v1.mapper;

import com.epam.springboot.fruitshop.api.v1.model.VendorDTO;
import com.epam.springboot.fruitshop.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
