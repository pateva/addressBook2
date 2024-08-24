package com.example.address_book.service.impl;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.exception.EntityAlreadyExistsException;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.RecordMapper;
import com.example.address_book.model.Address;
import com.example.address_book.model.Record;
import com.example.address_book.repository.RecordRepository;
import com.example.address_book.service.contract.RecordService;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.address_book.util.Constants.RECORD_DOES_NOT_EXIST_EXCEPTION_MSG;

@RestController
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordMapper recordMapper;
    private final RecordRepository recordRepository;
    private final UserService userService;

    @Override
    public RecordDto createRecord(RecordCreateDto recordCreateDto) {
        userService.validateUser(recordCreateDto.getUserId());

        if (recordCreateDto.isPersonal() && personalRecordExists(recordCreateDto.getUserId())) {
            throw new EntityAlreadyExistsException("The user already has a personal record! You can delete or update it!");
        }

        var record = recordMapper.mapCreateDtoToEntity(recordCreateDto);
        record.getContactDetails().forEach(e -> e.setRecord(record));

        return recordMapper.mapEntityToDto(recordRepository.save(record));
    }

    @Override
    public List<RecordPartialDto> getRecordsByUserId(Long userId) {
        userService.validateUser(userId);
        var records = recordRepository.getByUserIdAndPersonal(userId, false);

        return recordMapper.mapEntityListToPartialDtoList(records);
    }

    @Override
    public RecordDto getRecordById(Long recordId) {
        var record = recordRepository.findById(recordId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, recordId)));

        return recordMapper.mapEntityToDto(record);
    }

    @Override
    @Transactional
    public RecordDto updateRecord(RecordUpdateDto recordUpdateDto) {
        return recordRepository.getByIdAndUserId(recordUpdateDto.getId(), recordUpdateDto.getUserId())
                .map(record -> {
                    var recordNew = recordMapper.mapRecordUpdateDtoToEntity(recordUpdateDto);

                    return recordMapper.mapEntityToDto(recordRepository.save(mapValues(record, recordNew)));
                })
                .orElseThrow(() -> new EntityNotFoundException(String.format("Record with this id %d or or for this user %d does not exist", recordUpdateDto.getId(), recordUpdateDto.getUserId())));
    }

    @Override
    public boolean existsById(Long id) {
        return recordRepository.existsById(id);
    }

    @Override
    public void deleteRecord(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, id));
        }

        recordRepository.deleteById(id);
    }

    @Override
    public void updateAddress(Long id, Address address) {
        var record = recordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, id)));

        record.setAddress(address);
        recordRepository.save(record);
    }

    @Override
    public void removeAddress(Long addressId) {
       var record = recordRepository.getByAddressId(addressId)
               .orElseThrow(() -> new EntityNotFoundException(String.format("No record for address id %d", addressId)));

       record.setAddress(null);
       recordRepository.save(record);
    }

    private boolean personalRecordExists(Long userId) {
        return recordRepository.existsByUserIdAndPersonal(userId, true);
    }

    private Record mapValues(Record target, Record source) {
        BeanUtils.copyProperties(source, target, "notes", "contactDetails", "createdAt", "updatedAt", "address", "user", "personal", "userId");
        target.getContactDetails().clear();
        source.getContactDetails().forEach(contactDetail -> contactDetail.setRecord(target));
        target.getContactDetails().addAll(source.getContactDetails());
        BeanUtils.copyProperties(source.getAddress(), target.getAddress(), "id", "record");

        return target;
    }
}
