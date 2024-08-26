package com.example.address_book.model;

import com.example.address_book.model.id.RecordLabelId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "records_labels")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordLabel {
    @EmbeddedId
    private RecordLabelId id;
}
