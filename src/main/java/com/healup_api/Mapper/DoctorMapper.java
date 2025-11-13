package com.healup_api.Mapper;

import com.healup_api.DTO.DoctorDTOS.DoctorProfileResponse;
import com.healup_api.DTO.DoctorDTOS.DoctorRegister;
import com.healup_api.Entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    // 1. Input DTO -> Entity (Register ke liye)
    // (DTO ke 'password' ko Entity ke 'passwordHash' se map karna)

    // MapStruct ko batao ki jab Entity banao, toh 'roles' field ko chhod do
    @Mapping(target = "roles", ignore = true)
  Doctor toEntity(DoctorRegister registerRequest);

   // Doctor toEntity(DoctorDTO doctorDTO);
   // 2. Entity -> Output DTO (Profile dikhaane ke liye)
  DoctorProfileResponse toResponseDTO(Doctor doctor);

}
