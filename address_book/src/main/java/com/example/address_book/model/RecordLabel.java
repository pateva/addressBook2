package com.example.address_book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "records_labels")
@AllArgsConstructor
@NoArgsConstructor
public class RecordLabel {
    @Id
    @Column(name = "record_id")
    private Long recordId;

    @Id
    @Column(name = "label_id")
    private Long labelId;
}
