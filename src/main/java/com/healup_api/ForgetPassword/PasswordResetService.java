package com.healup_api.ForgetPassword;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PasswordResetTokenRepository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private  DoctorRespository doctorRespository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private JavaMailSender javaMailSender;
//    @Autowired
//    private PasswordEncoder passwordEncoder;


    //forget password

    public ResponseEntity<ApiResponse>  forgotpassword(String email){
        boolean exists =
                doctorRespository.findByEmail(email) != null || patientRepository.findByEmail(email) != null;

        if (!exists) {
            // SECURITY: same response
            return ResponseEntity.ok(
                    new ApiResponse(true, "If email exists, reset link sent", null)
            );
        }
        String token= UUID.randomUUID ().toString ();
        ResetToken resetToken= new ResetToken();
        resetToken.setEmail(email);
        resetToken.setResetToken (token);
        resetToken.setTokenExpiryDate (LocalDateTime.now (  ).plusMinutes (10));
        passwordResetTokenRepository.save (resetToken);
        sendResetMail(email, token);

        return ResponseEntity.ok(
                new ApiResponse(true, "Password reset link sent to email", null));

    }
    private void sendResetMail(String email, String token){
        String resetLink =
                "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage msg = new SimpleMailMessage ();
        msg.setTo(email);
        msg.setSubject("HealUp Doctor Password Reset");
        msg.setText(
                "Click the link to reset your password:\n" +
                        resetLink + "\n\nLink valid for 10 minutes"
        );

        javaMailSender.send(msg);
    }
//      // Step 2: reset password
public ResponseEntity<ApiResponse> resetPassword(String token, String newPassword) {

    ResetToken resetToken = passwordResetTokenRepository
            .findByResetToken(token)
            .orElse(null);

    if (resetToken == null ||
            resetToken.getTokenExpiryDate().isBefore(LocalDateTime.now())) {

        return ResponseEntity.badRequest()
                .body(new ApiResponse(false, "Invalid or expired token", null));
    }

    String email = resetToken.getEmail();

// 1 Pehle Doctor check
    Doctor doctor = doctorRespository.findByEmail(email);
    if (doctor != null) {

        doctor.setPasswordHash(newPassword);
        doctorRespository.save(doctor);

    } else {

        // 2️ Agar Doctor nahi mila to Patient check
        Patient patient = patientRepository.findByEmail(email);

        if (patient == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "User not found", null));
        }

        patient.setPasswordHash(newPassword);
        patientRepository.save(patient);
    }

    // Token invalidate
   // passwordResetTokenRepository.deleteByResetToken(token);

    return ResponseEntity.ok(
            new ApiResponse(true, "Password reset successful", null)
    );
}
}
