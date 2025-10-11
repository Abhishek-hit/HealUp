package com.healup_api.Service;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
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


    public ResponseEntity<ApiResponse> RegisterDoctor(Doctor doctor){
        doctor.setDoctorId ("DOC"+ UUID.randomUUID ().toString ().substring (0,6).toUpperCase (  ));
        Doctor doctorSave=doctorRespository.save (doctor);
        return ResponseEntity.ok (new ApiResponse (true,"Doctor created successfully",doctorSave ));

    }

    public ResponseEntity<ApiResponse> getDoctor(){
        List<Doctor> getall= doctorRespository.findAll ();
        return ResponseEntity.ok (new ApiResponse (true,"All doctor information get successfully",getall ));
    }

    //login
    public ResponseEntity<ApiResponse> LoginDcot(Doctor doctor){
        Doctor doctors=doctorRespository.findByEmail (doctor.getEmail ( ));
        if (doctors!=null&&doctor.getPasswordHash ().equals (doctors.getPasswordHash ())){
            return ResponseEntity.ok(new ApiResponse ( true,"login succesfullly",doctors ));

        }
        else {
            return ResponseEntity
                    .status(401) // Unauthorized
                    .body(new ApiResponse(false, "Invalid credentials or user not found", null));

        }

    }

    //find by id
    public ResponseEntity<ApiResponse> FindByIDdoctor(String doctorId){
        Doctor DoctorId=doctorRespository.findByDoctorId (doctorId);
        if (DoctorId!=null){
            return ResponseEntity.ok ( new ApiResponse ( true,"find by id succesfully",DoctorId ) );
        }
        return ResponseEntity.status(404).body(new ApiResponse(false, "Doctor not found", null));
    }

    //get
    public ResponseEntity<ApiResponse> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> list =appointmentRepository.findByDoctorId(doctorId);
        return ResponseEntity.ok(new ApiResponse(true, "Doctor appointments fetched", list));
    }

    //update status
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
