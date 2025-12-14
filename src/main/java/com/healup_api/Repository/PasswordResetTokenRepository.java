package com.healup_api.Repository;

import com.healup_api.ForgetPassword.ResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends MongoRepository<ResetToken,String> {
    Optional<ResetToken>  findByResetToken(String token);
}
