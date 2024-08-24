package com.example.address_book.service.contract;

import com.example.address_book.dto.LabelCreateDto;
import com.example.address_book.dto.LabelDto;

import java.util.List;
import java.util.Set;

public interface LabelService {
    LabelDto createLabel(LabelCreateDto labelCreateDto);
    LabelDto getLabel(Long id);
    Set<LabelDto> getByUserId(Long userId);
    LabelDto updateLabel(Long id, LabelDto labelDto);
}
