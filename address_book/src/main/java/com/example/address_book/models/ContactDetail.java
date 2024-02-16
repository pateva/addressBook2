package com.example.address_book.models;

import com.example.address_book.utils.ContactType;
import com.example.address_book.utils.converters.ContactTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_details")
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "record_id")
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", insertable = false, updatable = false)
    private Record record;

    @Column(name = "contact_detail_type")
    @Convert(converter = ContactTypeConverter.class)
    private ContactType type;

    @Column(name = "contact_detail_value")
    private String value;

}
