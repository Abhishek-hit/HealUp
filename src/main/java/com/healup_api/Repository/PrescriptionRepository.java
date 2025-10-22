package com.healup_api.Repository;


import com.healup_api.Entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrescriptionRepository extends MongoRepository<Prescription,String> {
    Prescription findByAppointmentId(String appointmentId);
    List<Prescription> findByDoctorId(String doctorId);
    List<Prescription> findByPatientId(String patientId);


}
