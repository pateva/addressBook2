package com.example.address_book.repository;

import com.example.address_book.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    boolean existsByUserIdAndPersonal(Long userId, boolean isPersonal);
    List<Record> getByUserIdAndPersonal(Long userId, boolean isPersonal);
}
