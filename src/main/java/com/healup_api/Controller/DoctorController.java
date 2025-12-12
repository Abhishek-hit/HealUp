package com.healup_api.Controller;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.DoctorDTOS.DoctorRegister;
import com.healup_api.LoginRequests.LoginRequest;
import com.healup_api.Service.AppointmentService.AppointmentService;
import com.healup_api.Service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/Reg")
    public ResponseEntity<ApiResponse> RegisDoctor(  @RequestBody DoctorRegister register){
        return doctorService.RegisterDoctor (register);
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> GetAllDoctor(){
        return doctorService.getDoctor ();

    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        return doctorService.LoginDoct(loginRequest);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<ApiResponse> getDoctor(@PathVariable String doctorId) {
        return doctorService.FindByIDdoctor (doctorId);
    }

    //  Get appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse> getDoctorAppointments(@PathVariable String doctorId) {
        return doctorService.getAppointmentsByDoctor (doctorId);
    }

    //set status
    @PutMapping("/{appointmentId}/status/{status}")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable String appointmentId, @PathVariable String status) {
        return doctorService.updateStatus(appointmentId, status);
    }
    //history of patient
    @GetMapping("/patient/history/{patientId}")
    public ResponseEntity<ApiResponse> getPatientHistory(@PathVariable String patientId) {
        return doctorService.PatientHistory (patientId);
    }
    //forget password
    @PutMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgetPassword(@PathVariable String email,@PathVariable String newPashword){
        return doctorService.forgetPassword (email,newPashword);
    }

}
