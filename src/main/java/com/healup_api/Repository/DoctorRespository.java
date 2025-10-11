package com.healup_api.Repository;

import com.healup_api.Entity.Doctor;
import com.healup_api.Entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DoctorRespository extends MongoRepository<Doctor,String> {
    Doctor findByEmail(String email);
    Doctor findByDoctorId(String doctorId);
    List<Doctor> findByName(String name);
    List<Doctor> findBySpeciality(String speciality);

    //   (e.g. "MBBS")
    List<Doctor> findByDegrees(String degree);

    // 4 Search by Tag (disease keyword e.g. "fever", "diabetes")
    List<Doctor> findByTags(String tag);
    @Query("{'$or':[ "
            + "{'name':{$regex:?0,$options:'i'}},"
            + "{'specialty':{$regex:?0,$options:'i'}},"
            + "{'degrees':{$regex:?0,$options:'i'}},"
            + "{'tags':{$regex:?0,$options:'i'}} ]}")
    List<Doctor> searchDoctor(String keyword);
}
