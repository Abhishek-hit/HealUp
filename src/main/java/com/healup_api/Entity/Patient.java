package com.healup_api.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection= "Patients")
public class Patient {

    @Id
    private String id;

    @Indexed(unique = true)
   private String patientId;


    private String firstName;
    private String lastName;


    private String email;



    private String phone;

    private LocalDate dob;

    private String gender;

 private Map<String, String> address;// line1, city, state, pincode
   // private List<String> medicalRecords;

    @Field("blood_group")
    private String blood_group;


    private String allergies;
    private String roles;




    @Field("password_hash") //field ka nam hogaa
    private String passwordHash;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
