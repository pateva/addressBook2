package com.example.address_book.repository;

import com.example.address_book.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    boolean existsByUserIdAndPersonal(Long userId, boolean isPersonal);
    List<Record> getByUserIdAndPersonal(Long userId, boolean isPersonal);
    Page<Record> getByUserIdAndPersonal(Long userId, boolean isPersonal, Pageable pageable);
    @EntityGraph(attributePaths = {"contactDetails", "notes"})
    Optional<Record> getByIdAndUserId(Long id, Long userId);
    Optional<Record> getByAddressId(Long addressId);
}
