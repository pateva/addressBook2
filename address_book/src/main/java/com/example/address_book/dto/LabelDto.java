package com.example.address_book.dto;

import com.example.address_book.util.Color;
import lombok.Data;

@Data
public class LabelDto {
    private Long id;
    private String name;
    private Color color;
}
