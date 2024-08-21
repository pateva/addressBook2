package com.example.address_book.repository;

import com.example.address_book.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<Record> getByUserIdAndPersonal(Long userId, boolean isPersonal);
}
