package com.healup_api.Controller.AppointmentController;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Service.AppointmentService.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class Appointmentcontroller {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<ApiResponse> book(@RequestBody Appointment appt) {
        return appointmentService.bookAppointment (appt);
    }

}
