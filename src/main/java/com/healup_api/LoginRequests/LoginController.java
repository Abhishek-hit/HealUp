package com.healup_api.LoginRequests;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Common URL prefix
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
