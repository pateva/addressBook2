package com.example.address_book.service.impl;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.ContactDetailMapper;
import com.example.address_book.repository.ContactDetailRepository;
import com.example.address_book.service.contract.ContactDetailService;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.example.address_book.util.Constants.CONTACT_DOES_NOT_EXIST_EXCEPTION_MSG;
import static com.example.address_book.util.Constants.RECORD_DOES_NOT_EXIST_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class ContactDetailServiceImpl implements ContactDetailService {
    private final ContactDetailRepository contactDetailRepository;
    private final ContactDetailMapper contactDetailMapper;
    private final RecordService recordService;

    @Override
    public ContactDetailDto getContactDetail(Long id) {

        return contactDetailMapper.mapEntityToDto(contactDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CONTACT_DOES_NOT_EXIST_EXCEPTION_MSG, id))));
    }

    @Override
    public Set<ContactDetailDto> getContactDetailsByRecordId(Long recordId) {

        return contactDetailMapper.mapEntitySetToDtoSet(contactDetailRepository.findByRecordId(recordId));
    }

    @Override
    public ContactDetailDto updateContactDetails(Long id, ContactDetailDto contactDetailDto) {

        return contactDetailRepository.findById(id)
                .map(contactDetail -> {
                    var contactDetailNew = contactDetailMapper.mapDtoToEntity(contactDetailDto);
                    BeanUtils.copyProperties(contactDetailNew, contactDetail, "id", "record");

                    return contactDetailMapper.mapEntityToDto(contactDetailRepository.save(contactDetail));
                })
                .orElseThrow(() -> new EntityNotFoundException(String.format(CONTACT_DOES_NOT_EXIST_EXCEPTION_MSG, id)));
    }

    @Override
    @Transactional
    public void deleteContactDetail(Long id) {
        var contactDetail = contactDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CONTACT_DOES_NOT_EXIST_EXCEPTION_MSG, id)));

        recordService.removeContactDetail(contactDetail.getRecord().getId(), id);
    }

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
