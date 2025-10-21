package com.healup_api.Mapper;

import com.healup_api.DTO.PatientDTO;
import com.healup_api.Entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDto(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
}
