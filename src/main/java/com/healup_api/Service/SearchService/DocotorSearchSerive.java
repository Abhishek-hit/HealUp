package com.healup_api.Service.SearchService;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.DTO.DoctorDTO;
import com.healup_api.Entity.Doctor;
import com.healup_api.Mapper.DoctorMapper;
import com.healup_api.Repository.DoctorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocotorSearchSerive {
    @Autowired
    private DoctorRespository doctorRespository;
    @Autowired private DoctorMapper doctorMapper;
    public ResponseEntity<ApiResponse> SearchDoctor(String keyword){
        List<Doctor> doctors=doctorRespository.searchDoctor (keyword);

      List<DoctorDTO> doctorDTO=doctors.stream ().map (doctorMapper::toDto).toList ();
        if (doctors.isEmpty ()){
            return ResponseEntity.ok (new ApiResponse (false,"Doctor not found"+keyword,null));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Doctors found successfully", doctorDTO));


    }
}
