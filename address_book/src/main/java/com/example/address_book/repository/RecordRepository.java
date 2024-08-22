package com.example.address_book.repository;

import com.example.address_book.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    boolean existsByUserIdAndPersonal(Long userId, boolean isPersonal);
}
