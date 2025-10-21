package com.healup_api.Mapper;

import com.healup_api.DTO.DoctorDTO;
import com.healup_api.Entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO toDto(Doctor doctor);

    Doctor toEntity(DoctorDTO doctorDTO);

}
