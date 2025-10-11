package com.healup_api.Service;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    public ResponseEntity<ApiResponse> AddPatient(Patient patient){
        patient.setPatientId("P"+ UUID.randomUUID ().toString ().substring (0,6).toUpperCase (  ));
        Patient patientdata=patientRepository.save (patient);
        return ResponseEntity.ok (new ApiResponse ( true,"Patient created",patientdata ));

    }

    public ResponseEntity<ApiResponse> getPatient(){
        List<Patient> patientsData=patientRepository.findAll ();
        return ResponseEntity.ok (new ApiResponse ( true,"Patient information",patientsData ));

    }

    //login
    public ResponseEntity<ApiResponse> LoginDcot(Patient patient){
        Patient patientData=patientRepository.findByEmail (patient.getEmail ( ));
        if (patientData!=null&&patient.getPasswordHash ().equals (patientData.getPasswordHash ())){
            return ResponseEntity.ok(new ApiResponse ( true,"login succesfullly",patientData));

        }
        else {
            return ResponseEntity
                    .status(401) // Unauthorized
                    .body(new ApiResponse(false, "Invalid credentials or user not found", null));

        }

    }
    //search by patient id
    public ResponseEntity<ApiResponse> FindByID(String patientId){
        Patient PatientId=patientRepository.findByPatientId (patientId);
       if (PatientId!=null){
           return ResponseEntity.ok ( new ApiResponse ( true,"find by id succesfully",PatientId ) );
       }
        return ResponseEntity.status(404).body(new ApiResponse(false, "Patient not found", null));
    }

    // Get appointments by patient
    public ResponseEntity<ApiResponse> getAppointmentsByPatient(String patientId) {
        List<Appointment> list = appointmentRepository.findByPatientId(patientId);
        return ResponseEntity.ok(new ApiResponse(true, "Patient appointments fetched", list));
    }
}
