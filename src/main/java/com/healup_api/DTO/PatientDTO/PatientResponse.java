package com.healup_api.DTO.PatientDTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
@Data
public class PatientResponse {

    private String id;
    private String patientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private Map<String, String> address;// line1, city, state, pincode isko bhi custom banana pada kiyu ki
    // JS request/response expect karta hai object, lekin backend String de raha tha.
//    private List<String> medicalRecords;
    private String blood_group;
    private String roles;
    private String allergies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
