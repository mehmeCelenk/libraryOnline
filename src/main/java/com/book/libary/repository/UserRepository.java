package com.book.libary.repository;

import com.book.libary.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByEmailAndPassword(String email, String password);
  //  Optional<User> findByUuid(final String userId);


}