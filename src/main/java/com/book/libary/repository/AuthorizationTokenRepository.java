package com.book.libary.repository;

import com.book.libary.model.entity.AuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {
    Optional<AuthorizationToken> findByToken(final String token);
}