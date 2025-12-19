package com.healup_api.Security;

import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private DoctorRespository doctorRespository;

    @Autowired
    private PatientRepository patientRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // 1. Check Doctor Table
        Doctor doctor = doctorRespository.findByEmail(email);
        if (doctor != null) {
            // Yahan hum Spring Security wala User object bana kar return kar rahe hain
            // Yeh database me save nahi hoga, bas login verify karne ke liye temporary banta hai
            return new org.springframework.security.core.userdetails.User(
                    doctor.getEmail(),
                    doctor.getPasswordHash(),
                    Collections.singletonList(new SimpleGrantedAuthority ("ROLE_DOCTOR"))
            );
        }
        // Dynamic role load karein aur "ROLE_" prefix lagayein
//        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole))
// Database se role nikaalein (e.g., "PATIENT" ya "ADMIN")
//        String userRole = patient.getRole();
        // 2. Check Patient Table
        Patient patient = patientRepository.findByEmail(email);
        if (patient != null) {
            return new org.springframework.security.core.userdetails.User(
                    patient.getEmail(),
                    patient.getPasswordHash(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_PATIENT"))
            );
        }

        throw new UsernameNotFoundException ("User not found with email: " + email);
    }
}
