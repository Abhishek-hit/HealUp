package com.healup_api.Repository;

import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient,String> {
    Patient findByEmail(String email);
    Patient findByPatientId(String patientId);
}
