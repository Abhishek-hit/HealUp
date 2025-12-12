package com.healup_api.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;
    private String appointmentId;   // Link with appointment
    private String doctorId;
    private String patientId;
    private String doctorname;
    private String patientname;

    private String diagnosis;
    private List<Medicine> medicines;
    private String advice;
    private String followUpDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Medicine {
        private String name;          // e.g. "Paracetamol"
        private String dosage;        // e.g. "500mg"
        private String frequency;     // e.g. "2 times/day"
        private int durationDays;     // e.g. 5
        private String instructions;  // e.g. "After food"
    }
}
