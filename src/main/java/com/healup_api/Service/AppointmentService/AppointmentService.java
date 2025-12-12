package com.healup_api.Service.AppointmentService;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.AppoinmentDTO.AppointmentRequest;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRespository doctorRespository;



    public ResponseEntity<ApiResponse> bookAppointment(AppointmentRequest appt){

        // 1. Validate & Fetch Patient
        Patient patient = patientRepository.findByPatientId(appt.getPatientId());
        if (patient == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid patient ID! Please register first.", null));
        }

        // 2. Validate & Fetch Doctor
        Doctor doctor = doctorRespository.findByDoctorId(appt.getDoctorId());
        if (doctor == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid doctor ID! Please select a registered doctor.", null));
        }
        Appointment appointment=new Appointment ();
        appointment.setAppointmentId ("API"+ UUID.randomUUID ().toString ().substring (0,5).toUpperCase (  ));
        appointment.setStatus ("Pending");
        appointment.setPatientId (appt.getPatientId ( ));
        appointment.setDoctorId (appt.getDoctorId ());
        appointment.setDoctorName (doctor.getName ( ));
        appointment.setPatientName (patient.getFirstName ( ));
        appointment.setReason (appt.getReason ( ));
        try {
            appointment.setAppointmentDateTime(LocalDateTime.parse(appt.getAppointmentDateTime()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid Date Format! Use ISO format (e.g., 2025-12-12T11:30:00)", null));
        }
        Appointment appSave=appointmentRepository.save (appointment);

        return ResponseEntity.ok (new ApiResponse ( true,"appointment book succesfully",appSave ));
    }

    //doctor upadate ke liye

}


