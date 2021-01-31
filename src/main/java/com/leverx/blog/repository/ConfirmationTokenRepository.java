package com.leverx.blog.repository;

import com.leverx.blog.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);
}
