package com.example.address_book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import microsoft.sql.DateTimeOffset;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;

@Entity
@Table(name = "records")
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "firm_name")
    private String firmName;

    @Column(name = "address_id")
    private Long addressId;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Address address;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToOne(mappedBy = "personalRecord")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User personalUser;

    @OneToMany(mappedBy = "record")
    private Set<ContactDetail> contactDetails;

    @OneToMany(mappedBy = "record")
    private Set<Note> notes;

    @Column(name = "created_at")
    @CreationTimestamp
    private DateTimeOffset createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private DateTimeOffset updatedAt;

}
