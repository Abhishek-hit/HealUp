package com.healup_api.Mapper;

import com.healup_api.DTO.PatientDTO.PatientRegister;
import com.healup_api.DTO.PatientDTO.PatientResponse;
import com.healup_api.Entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    // MapStruct ko batao ki jab Entity banao, toh 'roles' field ko chhod do
    @Mapping(target = "roles", ignore = true)
    Patient toEntity(PatientRegister patientRegister);

    // Doctor toEntity(DoctorDTO doctorDTO);
    // 2. Entity -> Output DTO (Profile dikhaane ke liye)
    PatientResponse toResponseDTO(Patient patient);
}
