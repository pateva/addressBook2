package com.example.address_book.service.impl;

import com.example.address_book.dto.PagedRecordDto;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordImageDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.exception.EntityAlreadyExistsException;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.RecordMapper;
import com.example.address_book.model.Address;
import com.example.address_book.model.Record;
import com.example.address_book.model.RecordLabel;
import com.example.address_book.model.id.RecordLabelId;
import com.example.address_book.repository.RecordLabelRepository;
import com.example.address_book.repository.RecordRepository;
import com.example.address_book.service.contract.RecordService;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import static com.example.address_book.util.Constants.RECORD_DOES_NOT_EXIST_EXCEPTION_MSG;

@RestController
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordMapper recordMapper;
    private final RecordRepository recordRepository;
    private final RecordLabelRepository recordLabelRepository;
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
    public PagedRecordDto getPagedRecordsByUserId(Long userId, Pageable pageable) {
        userService.validateUser(userId);
        var pagedRecords = recordRepository.getByUserIdAndPersonal(userId, false, pageable);

        return recordMapper.toPagedRecordDto(pagedRecords);
    }

    @Override
    public PagedRecordDto getPagedRecordsByUserIdAndLabel(Long userId, Long labelId, Pageable pageable) {
        userService.validateUser(userId);
        var pagedRecords = recordRepository.getByUserIdAndLabelId(userId, labelId, pageable);

        return recordMapper.toPagedRecordDto(pagedRecords);
    }

    @Override
    public RecordDto getRecordById(Long recordId) {
        var record = recordRepository.findById(recordId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, recordId)));

        return recordMapper.mapEntityToDto(record);
    }

    @Override
    @Transactional
    public RecordDto updateRecord(Long id, RecordUpdateDto recordUpdateDto) {
        return recordRepository.getByIdAndUserId(id, recordUpdateDto.getUserId())
                .map(record -> {
                    var recordNew = recordMapper.mapRecordUpdateDtoToEntity(recordUpdateDto);

                    return recordMapper.mapEntityToDto(recordRepository.save(mapValues(record, recordNew)));
                })
                .orElseThrow(() -> new EntityNotFoundException(String.format("Record with this id %d or or for this user %d does not exist", id, recordUpdateDto.getUserId())));
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

        if(target.getAddress() != null && source.getAddress() != null ) {
            BeanUtils.copyProperties(source.getAddress(), target.getAddress(), "id", "record");
        }

        return target;
    }

    @Override
    public void removeContactDetail(Long id, Long contactDetailId) {
        var record = recordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, id)));

        record.getContactDetails().removeIf(contact -> contact.getId().equals(contactDetailId));
        recordRepository.save(record);
    }

    @Override
    public void addLabelToRecord(Long id, Long labelId) {
        var recordLabelId = RecordLabelId.builder().labelId(labelId).recordId(id).build();

        if(recordLabelRepository.existsById(recordLabelId)) {
            //do nothing
            return;
        }

        recordLabelRepository.save(RecordLabel.builder().id(recordLabelId).build());
    }

    @Override
    public void removeLabelFromRecord(Long id, Long labelId) {
        var recordLabelId = RecordLabelId.builder().labelId(labelId).recordId(id).build();

        if(!recordLabelRepository.existsById(recordLabelId)) {
            //do nothing
            return;
        }

        recordLabelRepository.deleteById(recordLabelId);
    }

    @Override
    public RecordDto getPersonalRecord(Long userId) {

        return recordMapper.mapEntityToDto(recordRepository.getByUserIdAndPersonal(userId, true));
    }

    @Override
    public RecordDto addImageToRecord(Long id, RecordImageDto imageDto) {
        var record = recordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, id)));
        //TODO validations would be good
        record.setImageUrl(imageDto.getImageUrl());

        return recordMapper.mapEntityToDto(recordRepository.save(record));
    }
}
