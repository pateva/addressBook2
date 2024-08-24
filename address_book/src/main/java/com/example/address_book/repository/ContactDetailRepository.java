package com.example.address_book.repository;

import com.example.address_book.model.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {
    @Query("SELECT c FROM ContactDetail c WHERE c.record.id = :recordId")
    Set<ContactDetail> findByRecordId(Long recordId);
}
