package com.example.address_book.repository;

import com.example.address_book.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Set<Label> getByUserId(Long userId);
}
