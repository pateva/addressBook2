package com.example.address_book.service.impl;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.RecordMapper;
import com.example.address_book.repository.RecordRepository;
import com.example.address_book.service.contract.RecordService;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordMapper recordMapper;
    private final RecordRepository recordRepository;
    private final UserService userService;

    @Override
    public RecordDto createRecord(RecordCreateDto recordCreateDto) {
        if(!userService.existsById(recordCreateDto.getUserId())) {
            throw new EntityNotFoundException(String.format("User with id %d does not exist!", recordCreateDto.getUserId()));
        }

        var record = recordMapper.mapCreateDtoToEntity(recordCreateDto);
        record.getContactDetails().forEach(e -> e.setRecord(record));

        return recordMapper.mapEntityToDto(recordRepository.save(record));
    }
}
