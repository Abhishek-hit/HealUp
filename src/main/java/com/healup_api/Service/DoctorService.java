package com.healup_api.Service;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.DoctorDTO;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import com.healup_api.Mapper.DoctorMapper;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.DoctorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    private DoctorRespository doctorRespository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorMapper doctorMapper;


    public ResponseEntity<ApiResponse> RegisterDoctor(Doctor doctor){
        doctor.setDoctorId ("DOC"+ UUID.randomUUID ().toString ().substring (0,6).toUpperCase (  ));
        Doctor doctorSave=doctorRespository.save (doctor);
        //use doctor dto
        DoctorDTO doctorDTOdata=doctorMapper.toDto (doctorSave);

        return ResponseEntity.ok (new ApiResponse (true,"Doctor created successfully",doctorDTOdata ));

    }

    public ResponseEntity<ApiResponse> getDoctor(){
        List<Doctor> getall= doctorRespository.findAll ();

        List<DoctorDTO> dtoList=getall //this is list
                .stream( ) //and convert in stream
                .map (doctorMapper::toDto) //convert every docot obj into doctordto
                .toList ();//phir stream ko wapas list me convert kia gya h

        return ResponseEntity.ok (new ApiResponse (true,"All doctor information get successfully",dtoList ));
    }

    //login
    public ResponseEntity<ApiResponse> LoginDcot(Doctor doctor){
        Doctor doctors=doctorRespository.findByEmail (doctor.getEmail ( ));
        DoctorDTO loginDto=doctorMapper.toDto (doctors);
        if (doctors!=null&&doctor.getPasswordHash ().equals (doctors.getPasswordHash ())){
            return ResponseEntity.ok(new ApiResponse ( true,"login succesfullly",loginDto ));

        }
        else {
            return ResponseEntity
                    .status(401) // Unauthorized
                    .body(new ApiResponse(false, "Invalid credentials or user not found", null));

        }

    }

    //find doctor by id
    public ResponseEntity<ApiResponse> FindByIDdoctor(String doctorId){
        Doctor DoctorId=doctorRespository.findByDoctorId (doctorId);
        DoctorDTO DoctorIdDto=doctorMapper.toDto (DoctorId);//USE doctor Dto
        if (DoctorId!=null){
            return ResponseEntity.ok ( new ApiResponse ( true,"find by id succesfully",DoctorIdDto) );
        }
        return ResponseEntity.status(404).body(new ApiResponse(false, "Doctor not found", null));
    }

    // doctor will get patient appointment
    public ResponseEntity<ApiResponse> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> list =appointmentRepository.findByDoctorId(doctorId);
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



}
