package com.healup_api.DTO.PatientDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class PatientRegister {

    @NotBlank(message = "Patient's name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String firstName;
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Indexed(unique = true)
    private String email;


    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;

    private LocalDate dob;
    @NotBlank(message = "gender is required")
    private String gender;

    private Map<String, String> address;// line1, city, state, pincode
    // private List<String> medicalRecords;

    private String blood_group;

    @NotBlank(message = "Allergies is required")
    private String allergies;




    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")

    private String passwordHash;

}
