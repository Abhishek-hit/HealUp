package com.healup_api.Controller;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.DoctorDTOS.DoctorRegister;
import com.healup_api.ForgetPassword.ForgetPassword;
import com.healup_api.ForgetPassword.PasswordResetService;
import com.healup_api.ForgetPassword.ResetPasswordRequest;
import com.healup_api.LoginRequests.LoginRequest;
import com.healup_api.Service.AppointmentService.AppointmentService;
import com.healup_api.Service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api")
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private  PasswordResetService passwordResetService;
    @PostMapping("/Reg")
  //  @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> RegisDoctor(  @RequestBody DoctorRegister register){
        return doctorService.RegisterDoctor (register);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> GetAllDoctor(){
        return doctorService.getDoctor ();

    }
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
//        return doctorService.LoginDoct(loginRequest);
//    }

    @GetMapping("/{doctorId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> getDoctor(@PathVariable String doctorId) {
        return doctorService.FindByIDdoctor (doctorId);
    }
    //doctor profile
    @GetMapping("/profile")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> getMyProfile() {
        return doctorService.getMyProfile();
    }

    //  Get appointments by doctor
    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor")
    public ResponseEntity<ApiResponse> getDoctorAppointments() {
        return doctorService.getAppointmentsByDoctor ();
    }

    //set status
    @PutMapping("/{appointmentId}/status/{status}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable String appointmentId, @PathVariable String status) {
        return doctorService.updateStatus(appointmentId, status);
    }
    //history of patient
    @GetMapping("/patient/history/{patientId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> getPatientHistory(@PathVariable String patientId) {
        return doctorService.PatientHistory (patientId);
    }




}
