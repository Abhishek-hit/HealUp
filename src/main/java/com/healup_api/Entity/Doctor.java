package com.healup_api.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Doctors")
public class Doctor {
    @Id
    private String id;
    @Indexed(unique = true)
    private String doctorId;

//    @NotBlank(message = "Doctor's name is required")
//    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

//    @NotBlank(message = "Email is required")
//    @Email(message = "Please provide a valid email address")
//    @Indexed(unique = true)
    private String email;

//    @NotBlank(message = "Phone number is required")
//    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;
//
//    @NotBlank(message = "Specialization is required")
    private List<String> speciality;
    private List<String> degrees;
    private Map<String, Object> clinicAddress; // line1, city, lat, lng
    private boolean teleAvailable;
    private double rating;
    private List<String> tags;
    private List<Map<String, String>> availability;
    private String roles;


//    @NotBlank(message = "Password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
   @Field("password_hash") //field ka nam hogaa
    private String passwordHash;

    private String gender;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
