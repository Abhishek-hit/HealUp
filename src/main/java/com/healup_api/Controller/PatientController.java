package com.healup_api.Controller;


import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.PatientDTO.PatientRegister;
import com.healup_api.LoginRequests.LoginRequest;
import com.healup_api.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/Reg")
   // @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse> addpatient(@RequestBody PatientRegister patient){
        return patientService.AddPatient (patient);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse> GetPatient(){
        return patientService.getPatient ();
    }
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest patient) {
//        return patientService.LoginDcot (patient);
//    }
    //get by id
    @GetMapping("/{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse> getPatient(@PathVariable String patientId) {
        return patientService.FindByID (patientId);
    }
    // Get Appointments by Patient
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse> getPatientAppointments(@PathVariable String patientId) {
        return patientService.getAppointmentsByPatient(patientId);
    }
    @GetMapping("/profile")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse> getMyProfile() {
        return patientService.MyProfile ();
    }
}
