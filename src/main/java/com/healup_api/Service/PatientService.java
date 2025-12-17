package com.healup_api.Service;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.PatientDTO.PatientRegister;
import com.healup_api.DTO.PatientDTO.PatientResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.LoginRequests.LoginRequest;
import com.healup_api.Mapper.PatientMapper;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired private PasswordEncoder passwordEncoder;
    public ResponseEntity<ApiResponse> AddPatient(PatientRegister dto){
        Patient patient=patientMapper.toEntity (dto);
        patient.setPatientId("P"+ UUID.randomUUID ().toString ().substring (0,6).toUpperCase (  ));
        patient.setRoles ("PATIENT");
        patient.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        Patient patientdata=patientRepository.save (patient);//use mapper

        PatientResponse patientDto=patientMapper.toResponseDTO (patientdata);
        return ResponseEntity.ok (new ApiResponse ( true,"Patient created",patientDto ));

    }

    public ResponseEntity<ApiResponse> getPatient(){
        List<Patient> patientsData=patientRepository.findAll ();
        List<PatientResponse> patientDTOS=patientsData
                .stream ()
                .map (patientMapper::toResponseDTO)
                .toList (); //USE DTOS
        return ResponseEntity.ok (new ApiResponse ( true,"Patient information",patientDTOS ));

    }

    //login
//    public ResponseEntity<ApiResponse> LoginDcot(LoginRequest loginRequest){
//        Patient patientData=patientRepository.findByEmail (loginRequest.getEmail ( ));
//        PatientResponse loginDto=patientMapper.toResponseDTO (patientData);//use dto mapper
//        if (patientData!=null&&loginRequest.getPassword ().equals (patientData.getPasswordHash ())){
//            return ResponseEntity.ok(new ApiResponse ( true,"login succesfullly",loginDto));
//
//        }
//        else {
//            return ResponseEntity
//                    .status(401) // Unauthorized
//                    .body(new ApiResponse(false, "Invalid credentials or user not found", null));
//
//        }
//
//    }
    //search by patient id
    public ResponseEntity<ApiResponse> FindByID(String patientId){
        Patient PatientId=patientRepository.findByPatientId (patientId);
        PatientResponse patientIdDTO=patientMapper.toResponseDTO (PatientId);

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
