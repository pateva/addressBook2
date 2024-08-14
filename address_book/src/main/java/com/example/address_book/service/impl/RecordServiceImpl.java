package com.example.address_book.service.impl;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.mapper.RecordMapper;
import com.example.address_book.model.Record;
import com.example.address_book.repository.RecordRepository;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordMapper recordMapper;
    private final RecordRepository recordRepository;

    @Override
    public RecordDto createRecord(RecordCreateDto recordCreateDto) {
        var record = recordMapper.mapCreateDtoToEntity(recordCreateDto);

        return recordMapper.mapEntityToDto(recordRepository.save(record));
    }
}
