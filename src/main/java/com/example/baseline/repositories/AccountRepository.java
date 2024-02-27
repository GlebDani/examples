package com.example.baseline.repositories;

import com.example.baseline.Model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByEmailIgnoreCase(String email);


    Optional<Account> findByToken(String token);
}
