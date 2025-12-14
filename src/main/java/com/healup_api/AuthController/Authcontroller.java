package com.healup_api.AuthController;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.ForgetPassword.ForgetPassword;
import com.healup_api.ForgetPassword.PasswordResetService;
import com.healup_api.ForgetPassword.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class Authcontroller {
@Autowired private PasswordResetService passwordResetService;
    //forget password
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestBody ForgetPassword request
    ) {
        return passwordResetService.forgotpassword(request.getEmail());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestBody ResetPasswordRequest request
    ) {
        return passwordResetService.resetPassword (
                request.getToken(),
                request.getNewPassword()
        );
    }
}
