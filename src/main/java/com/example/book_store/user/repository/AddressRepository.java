package com.example.book_store.user.repository;

import com.example.book_store.user.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
