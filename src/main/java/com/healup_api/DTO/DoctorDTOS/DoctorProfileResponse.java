package com.healup_api.DTO.DoctorDTOS;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class DoctorProfileResponse {
    @Id
    private String id;
    @Indexed(unique = true)
    private String doctorId;

    private String name;

    private String email;

    private String phone;

    private List<String> speciality;
    private List<String> degrees;
    private Map<String, Object> clinicAddress; // line1, city, lat, lng
    private boolean teleAvailable;
    private double rating;
    private List<String> tags;
    private List<Map<String, String>> availability;
    private String gender;
    private String passwordHash;
    private String roles;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
