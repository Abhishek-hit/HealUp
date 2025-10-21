package com.healup_api.Service;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.PatientDTO;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Mapper.PatientMapper;
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
    @Autowired
    private PatientMapper patientMapper;
    public ResponseEntity<ApiResponse> AddPatient(Patient patient){
        patient.setPatientId("P"+ UUID.randomUUID ().toString ().substring (0,6).toUpperCase (  ));

        Patient patientdata=patientRepository.save (patient);//use mapper

        PatientDTO patientDto=patientMapper.toDto (patientdata);
        return ResponseEntity.ok (new ApiResponse ( true,"Patient created",patientDto ));

    }

    public ResponseEntity<ApiResponse> getPatient(){
        List<Patient> patientsData=patientRepository.findAll ();
        List<PatientDTO> patientDTOS=patientsData
                .stream ()
                .map (patientMapper::toDto)
                .toList (); //USE DTOS
        return ResponseEntity.ok (new ApiResponse ( true,"Patient information",patientDTOS ));

    }

    //login
    public ResponseEntity<ApiResponse> LoginDcot(Patient patient){
        Patient patientData=patientRepository.findByEmail (patient.getEmail ( ));
        PatientDTO loginDto=patientMapper.toDto (patientData);//use dto mapper
        if (patientData!=null&&patient.getPasswordHash ().equals (patientData.getPasswordHash ())){
            return ResponseEntity.ok(new ApiResponse ( true,"login succesfullly",loginDto));

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
        PatientDTO patientIdDTO=patientMapper.toDto (PatientId);

       if (PatientId!=null){
           return ResponseEntity.ok ( new ApiResponse ( true,"find by id succesfully",patientIdDTO) );
       }
        return ResponseEntity.status(404).body(new ApiResponse(false, "Patient not found", null));
    }

    // Get appointments by patient
    public ResponseEntity<ApiResponse> getAppointmentsByPatient(String patientId) {
        List<Appointment> list = appointmentRepository.findByPatientId(patientId);
        return ResponseEntity.ok(new ApiResponse(true, "Patient appointments fetched", list));
    }
}
