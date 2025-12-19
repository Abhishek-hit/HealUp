package com.healup_api.Security;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.DoctorDTOS.DoctorProfileResponse;
import com.healup_api.DTO.PatientDTO.PatientResponse;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.LoginRequests.LoginRequest;
import com.healup_api.Mapper.DoctorMapper;
import com.healup_api.Mapper.PatientMapper;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DoctorRespository doctorRespository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private PatientMapper patientMapper;

    // === DOCTOR LOGIN LOGIC ===
    public ResponseEntity<ApiResponse> login(LoginRequest request) {
        try {
            // 1. Sabse pehle Password Check karo (Common for both)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // 2. Authentication Pass ho gaya. Ab check karo banda kaun hai.

            // Case A: Check Doctor Repository
            Doctor doctor = doctorRespository.findByEmail(request.getEmail());
            if (doctor != null) {
                String token = jwtService.generateToken(doctor.getEmail());
//                DoctorProfileResponse responseDto = doctorMapper.toResponseDTO(doctor);

                // Response me Role bhi bhej dete hain taaki Frontend ko pata chale redirect kahan karna hai
                // (Optional: Agar tu chahe to DTO ke andar bhi role daal sakta hai)
                return ResponseEntity.ok(new ApiResponse(true, "Token created successfully " , token));
            }

            // Case B: Check Patient Repository
            Patient patient = patientRepository.findByEmail(request.getEmail());
            if (patient != null) {
                String token = jwtService.generateToken(patient.getEmail());
//                PatientResponse responseDto = patientMapper.toResponseDTO(patient);

                return ResponseEntity.ok(new ApiResponse(true, "Token created successfully " , token));
            }

            // Case C: Authentication pass hua par User DB me nahi mila (Rare case)
            return ResponseEntity.status(404).body(new ApiResponse(false, "User details not found", null));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid Email or Password", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "Login Failed: " + e.getMessage(), null));
        }
    }
}