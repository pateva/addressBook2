package com.example.address_book.service.impl;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.ContactDetailMapper;
import com.example.address_book.repository.ContactDetailRepository;
import com.example.address_book.service.contract.ContactDetailService;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.address_book.util.Constants.RECORD_DOES_NOT_EXIST_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class ContactDetailServiceImpl implements ContactDetailService {
    private final ContactDetailRepository contactDetailRepository;
    private final ContactDetailMapper contactDetailMapper;
    private final RecordService recordService;

    @Override
    @Transactional
    public ContactDetailDto createContactDetail(ContactDetailCreateDto contactDetailCreateDto) {
        if(!recordService.existsById(contactDetailCreateDto.getRecordId())) {
            throw new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, contactDetailCreateDto.getRecordId()));
        }

        var contactDetail = contactDetailMapper.mapCreateDtoToEntity(contactDetailCreateDto);

        return contactDetailMapper.mapEntityToDto(contactDetailRepository.save(contactDetail));
    }
}
