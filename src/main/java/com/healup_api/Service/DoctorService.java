package com.healup_api.Service;

import com.healup_api.API_Response.ApiResponse;

import com.healup_api.DTO.DoctorDTOS.DoctorProfileResponse;
import com.healup_api.DTO.DoctorDTOS.DoctorRegister;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Entity.Prescription;
import com.healup_api.LoginRequests.LoginRequest;
import com.healup_api.Mapper.DoctorMapper;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PatientRepository;
import com.healup_api.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    private DoctorRespository doctorRespository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private  PatientRepository patientRepository;
    @Autowired private PrescriptionRepository prescriptionRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public ResponseEntity<ApiResponse> RegisterDoctor(DoctorRegister dto){
        Doctor doctor=doctorMapper.toEntity(dto);
        doctor.setDoctorId ("DOC"+ UUID.randomUUID ().toString ().substring (0,6).toUpperCase (  ));
        doctor.setRoles ("DOCTOR");
        doctor.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        Doctor  saveDoctor= doctorRespository.save (doctor);
        //use doctor Response

        DoctorProfileResponse doctorProfileResponse=doctorMapper.toResponseDTO (saveDoctor);
        return ResponseEntity.ok (new ApiResponse (true,"Doctor created successfully",doctorProfileResponse ));

    }
//get all doctor
    public ResponseEntity<ApiResponse> getDoctor(){
        List<Doctor> getall= doctorRespository.findAll ();

        List<DoctorProfileResponse> dtoList=getall //this is list
                .stream( ) //and convert in stream
                .map (doctorMapper::toResponseDTO) //convert every docot obj into doctordto
                .toList ();//phir stream ko wapas list me convert kia gya h

        return ResponseEntity.ok (new ApiResponse (true,"All doctor information get successfully",dtoList ));
    }

    //login
//    public ResponseEntity<ApiResponse> LoginDoct(LoginRequest loginRequest){
//        Doctor doctors=doctorRespository.findByEmail (loginRequest.getEmail ( ));
//        DoctorProfileResponse loginDto=doctorMapper.toResponseDTO (doctors);
//        if (doctors!=null&&loginRequest.getPassword().equals(doctors.getPasswordHash())){
//            return ResponseEntity.ok(new ApiResponse ( true,"login succesfullly",loginDto ));
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

    //doctor find own profile only
    public ResponseEntity<ApiResponse> getMyProfile(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();//own id can search only
        Doctor Doctor=doctorRespository.findByEmail (email);
        // DEBUG LINE: Console me check kar ki email kya aa raha hai
        System.out.println("Checking Profile for Email: " + email);
        DoctorProfileResponse DoctorDto=doctorMapper.toResponseDTO (Doctor);//USE doctor Dto
        if (Doctor!=null){
            return ResponseEntity.ok ( new ApiResponse ( true,"find by id succesfully",DoctorDto) );
        }
        return ResponseEntity.status(404).body(new ApiResponse(false, "Doctor not found", null));
    }
    //find by id only
    public ResponseEntity<ApiResponse> FindByIDdoctor(String doctorId){
        Doctor doctor = doctorRespository.findByDoctorId(doctorId);
        if (doctor != null){
            return ResponseEntity.ok(new ApiResponse(true, "Doctor found", doctorMapper.toResponseDTO(doctor)));
        }
        return ResponseEntity.status(404).body(new ApiResponse(false, "Doctor not found", null));
    }

    // doctor will get patient appointment
    public ResponseEntity<ApiResponse> getAppointmentsByDoctor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // 2. Doctor find karo
        Doctor doctor = doctorRespository.findByEmail(email);

        if (doctor == null) {
            return ResponseEntity.status(404).body(new ApiResponse(false, "Doctor not found", null));
        }
        List<Appointment> list =appointmentRepository.findByDoctorId(doctor.getDoctorId ( ));
        return ResponseEntity.ok(new ApiResponse(true, "Doctor appointments fetched", list));
    }

    //update status patient appointment
    public ResponseEntity<ApiResponse> updateStatus(String appointmentId, String newStatus){
      Appointment appointment=appointmentRepository.findByAppointmentId (appointmentId);
        if (appointment == null) {
            return ResponseEntity.status (404).body (new ApiResponse (false, "Appointment not found", null));
        }
        appointment.setStatus(newStatus.toUpperCase());
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return ResponseEntity.ok(new ApiResponse(true, "Status updated to " + newStatus, updatedAppointment));
    }

//Patient history check by doctor
    public ResponseEntity<ApiResponse> PatientHistory(String PatientId){
        List<Appointment> appointments=appointmentRepository.findByPatientId (PatientId);
        if (appointments == null || appointments.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "No appointments found for this patient!", null));
        }

       for (Appointment appt:appointments){
           Prescription prescription=prescriptionRepository.findByAppointmentId (appt.getAppointmentId ());
           appt.setPrescription(prescription);
       }
           return ResponseEntity.ok(new ApiResponse(true, "Patient history fetched successfully", appointments));

    }


}
