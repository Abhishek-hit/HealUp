package com.healup_api.Service.AppointmentService;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    public ResponseEntity<ApiResponse> bookAppointment(Appointment appt){
        appt.setAppointmentId ("API"+ UUID.randomUUID ().toString ().substring (0,5).toUpperCase (  ));
        Appointment appSave=appointmentRepository.save (appt);
        return ResponseEntity.ok (new ApiResponse ( true,"appointment book succesfully",appSave ));
   }

   //doctor upadate ke liye

}


