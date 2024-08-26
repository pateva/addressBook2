package com.example.address_book.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class RecordLabelId implements Serializable {
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "label_id")
    private Long labelId;
}
