package com.example.address_book.model;

import com.example.address_book.util.ContactType;
import com.example.address_book.util.converter.ContactTypeConverter;
import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    @Column(name = "contact_detail_type")
    @Convert(converter = ContactTypeConverter.class)
    private ContactType type;

    @Column(name = "contact_detail_value")
    private String value;

}
