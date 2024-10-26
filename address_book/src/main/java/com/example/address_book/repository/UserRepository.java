package com.example.address_book.repository;

import com.example.address_book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.records r " +
            "WHERE u.email = :email AND (r.personal = true OR r IS NULL)")
    Optional<User> findByEmailWithPersonalRecordOnly(@Param("email") String email);
}
