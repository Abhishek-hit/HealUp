package com.healup_api.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    private String appointmentId;
    private String patientId;     // linked to Patient
    private String doctorId;      // linked to Doctor

    private String doctorName;
    private String patientName;

    private LocalDateTime appointmentDateTime;
    private String status;        // PENDING / CONFIRMED / COMPLETED / CANCELLED
    private String reason;        // reason for visit / symptoms

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}