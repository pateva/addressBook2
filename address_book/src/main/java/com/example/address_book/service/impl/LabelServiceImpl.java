package com.example.address_book.service.impl;

import com.example.address_book.dto.LabelCreateDto;
import com.example.address_book.dto.LabelDto;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.LabelMapper;
import com.example.address_book.repository.LabelRepository;
import com.example.address_book.service.contract.LabelService;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.example.address_book.util.Constants.LABEL_DOES_NOT_EXIST_EXCEPTION_MSG;

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

    @Override
    public LabelDto getLabel(Long id) {

        return labelMapper.mapEntityToDto(labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LABEL_DOES_NOT_EXIST_EXCEPTION_MSG , id))));
    }

    @Override
    public Set<LabelDto> getByUserId(Long userId) {

        return labelMapper.mapEntitySetToDtoSet(labelRepository.getByUserId(userId));
    }

    @Override
    public LabelDto updateLabel(Long id, LabelDto labelDto) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LABEL_DOES_NOT_EXIST_EXCEPTION_MSG , id)));
        var labelNew = labelMapper.mapDtoToEntity(labelDto);
        BeanUtils.copyProperties(labelNew, label, "id", "user");

        return labelMapper.mapEntityToDto(labelRepository.save(label));
    }

    @Override
    public void deleteLabel(Long id) {
        if(!labelRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(LABEL_DOES_NOT_EXIST_EXCEPTION_MSG , id));
        }

        labelRepository.deleteById(id);
    }
}
