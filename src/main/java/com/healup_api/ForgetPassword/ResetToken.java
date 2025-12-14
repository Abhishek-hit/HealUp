package com.healup_api.ForgetPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "token")
public class ResetToken {
    private String email;
    private String resetToken;
    // Naya column 2: Token kab expire hoga
    private LocalDateTime tokenExpiryDate;

}
