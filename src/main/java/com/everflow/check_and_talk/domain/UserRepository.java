package com.everflow.check_and_talk.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<USer, Long> {

    Optional<USer> findByEmail(String email);
}
