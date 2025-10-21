package com.healup_api.Controller;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Service.AppointmentService.AppointmentService;
import com.healup_api.Service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/Reg")
    public ResponseEntity<ApiResponse> RegisDoctor(  @RequestBody Doctor doctor){
        return doctorService.RegisterDoctor (doctor);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> GetAllDoctor(){
        return doctorService.getDoctor ();

    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Doctor doctor) {
        return doctorService.LoginDcot (doctor);
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

}
