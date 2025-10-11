package com.healup_api.Repository;

import com.healup_api.Entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment,String> {
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByPatientId(String patientId);
    Appointment findByAppointmentId(String appointmentId);
}
