package com.healup_api.DTO.AppoinmentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private String patientId;
    private String doctorId;
    private String appointmentDateTime; // ISO string, e.g. "2025-12-12T11:30:00"
    private String reason;
}
