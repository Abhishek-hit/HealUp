package com.healup_api.Controller.PrescriptionController;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Prescription;
import com.healup_api.Service.PrescriptionService.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    @Autowired
   private   PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPrescription(@RequestBody Prescription prescription){
        return prescriptionService.createPrescription (prescription);
    }

    //get by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse> getDoctorPrescriptions(@PathVariable String doctorId) {
        return prescriptionService.getByDoctor(doctorId);
    }
  //get by patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse> getPatientPrescriptions(@PathVariable String patientId) {
        return prescriptionService.getByPatient(patientId);
    }

    //also fetch by appointment id
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<ApiResponse>  getByAppointment(@PathVariable String appointmentId){
        return prescriptionService.getPrescriptionByAppointment (appointmentId);
    }

}
