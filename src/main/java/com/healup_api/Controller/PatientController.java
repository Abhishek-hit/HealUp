package com.healup_api.Controller;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Api")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addpatient(@RequestBody Patient patient){
        return patientService.AddPatient (patient);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> GetPatient(){
        return patientService.getPatient ();
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Patient patient) {
        return patientService.LoginDcot (patient);
    }
    //het by if
    @GetMapping("/{patientId}")
    public ResponseEntity<ApiResponse> getPatient(@PathVariable String patientId) {
        return patientService.FindByID (patientId);
    }
    // Get Appointments by Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse> getPatientAppointments(@PathVariable String patientId) {
        return patientService.getAppointmentsByPatient(patientId);
    }
}
