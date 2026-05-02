package com.healup_api.DTO.DoctorDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDashboardStatus {
    private long totalAppointments;
    private long pending;
    private long completed;
    private long today;
}
