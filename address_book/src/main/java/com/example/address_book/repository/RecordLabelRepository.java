package com.example.address_book.repository;

import com.example.address_book.model.RecordLabel;
import com.example.address_book.model.id.RecordLabelId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordLabelRepository extends JpaRepository<RecordLabel, RecordLabelId> {
}
