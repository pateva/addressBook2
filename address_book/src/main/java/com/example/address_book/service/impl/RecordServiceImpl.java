package com.example.address_book.service.impl;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.exception.EntityAlreadyExistsException;
import com.example.address_book.mapper.RecordMapper;
import com.example.address_book.model.Record;
import com.example.address_book.repository.RecordRepository;
import com.example.address_book.service.contract.RecordService;
import com.example.address_book.service.contract.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordMapper recordMapper;
    private final RecordRepository recordRepository;
    private final UserService userService;

    @Override
    public RecordDto createRecord(RecordCreateDto recordCreateDto) {
        userService.validateUser(recordCreateDto.getUserId());

        if(recordCreateDto.isPersonal() && personalRecordExists(recordCreateDto.getUserId())) {
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
                .orElseThrow(() -> new EntityNotFoundException(String.format("Record with id %d does not exist", recordId)));

        return recordMapper.mapEntityToDto(record);
    }

    @Override
    public RecordDto updateRecord(RecordUpdateDto recordUpdateDto) {
        var existingRecord = recordRepository.getByIdAndUserId(recordUpdateDto.getId(), recordUpdateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Record with this id %d or or for this user %d does not exist", recordUpdateDto.getId(), recordUpdateDto.getUserId())));

        var record = recordMapper.mapRecordUpdateDtoToEntity(recordUpdateDto);


        return recordMapper.mapEntityToDto(recordRepository.save(mapValues(existingRecord, record)));
    }

    private boolean personalRecordExists(Long userId) {
        return recordRepository.existsByUserIdAndPersonal(userId, true);
    }

    //TODO finish + can you do this better, this looks durveno
    private Record mapValues(Record target, Record source) {
        target.setFirmName(source.getFirstName());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setAddress(source.getAddress());
        target.getContactDetails().clear();
        source.getContactDetails().forEach(contactDetail -> contactDetail.setRecord(target));
        target.getContactDetails().addAll(source.getContactDetails());
        BeanUtils.copyProperties(source.getAddress(), target.getAddress(), "id", "record");
        return target;
    }

}
