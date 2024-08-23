package com.example.address_book.service.impl;

import com.example.address_book.dto.AddressCreateDto;
import com.example.address_book.dto.AddressDto;
import com.example.address_book.exception.EntityAlreadyExistsException;
import com.example.address_book.exception.MissingValueException;
import com.example.address_book.mapper.AddressMapper;
import com.example.address_book.repository.AddressRepository;
import com.example.address_book.service.contract.AddressService;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final RecordService recordService;

    @Override
    @Transactional
    public AddressDto createAddress(AddressCreateDto addressDto) {
        if(addressDto.getRecordId() == null) {
            throw new MissingValueException("Record id is required!");
        }

        if(addressRepository.getByRecordId(addressDto.getRecordId()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Record with id %d already has an address. You can either update or delete it", addressDto.getRecordId()));
        }

        var address = addressMapper.mapCreateDtoToEntity(addressDto);
        var entity = addressRepository.save(address);
        recordService.updateAddress(addressDto.getRecordId(), entity);

        return addressMapper.mapEntityToDto(entity);
    }

}
