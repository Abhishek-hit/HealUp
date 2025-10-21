package com.healup_api.Service.AppointmentService;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRespository doctorRespository;



    public ResponseEntity<ApiResponse> bookAppointment(Appointment appt){

        //validate patient
       boolean patientExist=patientRepository.findByPatientId (appt.getPatientId ())!=null;
       if (!patientExist){
           return ResponseEntity.badRequest ()
                   .body (new ApiResponse ( false,"Invalid patient ID! Please register first.",patientExist ));
       }


        //  Validate Doctor
        boolean doctorExists = doctorRespository.findByDoctorId(appt.getDoctorId()) != null;
        if (!doctorExists) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid doctor ID! Please select a registered doctor.", doctorExists));
        }
        appt.setAppointmentId ("API"+ UUID.randomUUID ().toString ().substring (0,5).toUpperCase (  ));
        appt.setStatus ("Pending");
        Appointment appSave=appointmentRepository.save (appt);

        return ResponseEntity.ok (new ApiResponse ( true,"appointment book succesfully",appSave ));
   }

   //doctor upadate ke liye

}


