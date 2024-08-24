package com.example.address_book.service.impl;

import com.example.address_book.dto.LabelCreateDto;
import com.example.address_book.dto.LabelDto;
import com.example.address_book.mapper.LabelMapper;
import com.example.address_book.repository.LabelRepository;
import com.example.address_book.service.contract.LabelService;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;
    private final UserService userService;

    @Override
    public LabelDto createLabel(LabelCreateDto labelCreateDto) {
        userService.validateUser(labelCreateDto.getUserId());
        var label = labelMapper.mapCreateDtoToEntity(labelCreateDto);

        return labelMapper.mapEntityToDto(labelRepository.save(label));
    }
}
