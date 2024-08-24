package com.example.address_book.service.contract;

import com.example.address_book.dto.LabelCreateDto;
import com.example.address_book.dto.LabelDto;

public interface LabelService {
    LabelDto createLabel(LabelCreateDto labelCreateDto);
}
