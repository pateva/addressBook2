package com.example.address_book.models;

import com.example.address_book.models.primarykeys.RecordLabelId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_labels")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RecordLabelId.class)
public class RecordLabel {
    @Id
    @Column(name = "record_id")
    private Long recordId;

    @Id
    @Column(name = "label_id")
    private Long labelId;
}
