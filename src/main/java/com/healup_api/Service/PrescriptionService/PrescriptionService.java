package com.healup_api.Service.PrescriptionService;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Entity.Appointment;
import com.healup_api.Entity.Prescription;
import com.healup_api.Repository.AppointmentRepository;
import com.healup_api.Repository.DoctorRespository;
import com.healup_api.Repository.PatientRepository;
import com.healup_api.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescriptionService {
    @Autowired private PrescriptionRepository prescriptionRepository;
    @Autowired private DoctorRespository doctorRespository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private AppointmentRepository appointmentRepository;

    public ResponseEntity<ApiResponse>  createPrescription(Prescription appointmentid){
        Appointment appt=appointmentRepository.findByAppointmentId (appointmentid.getAppointmentId ());
       // Check if appointment exists
         if (appt==null){
             return ResponseEntity.badRequest ().body (new ApiResponse ( false, "appointment not fount",null ));
         }

        //  Check if already created
        if (prescriptionRepository.findByAppointmentId (appointmentid.getAppointmentId ())!=null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Prescription already exists for this appointment!", null));
        }
        //autofill doc and patient id
        appointmentid.setDoctorId (appt.getDoctorId ());
        appointmentid.setPatientId (appt.getPatientId ());
        appointmentid.setCreatedAt (LocalDateTime.now ());
        appointmentid.setUpdatedAt (LocalDateTime.now ());
        appointmentid.setDoctorname (appt.getDoctorName ( ));
        appointmentid.setPatientname (appt.getPatientName ());

        Prescription saved=prescriptionRepository.save (appointmentid);

        // update appointment status to “COMPLETED”
        appt.setStatus("COMPLETED");
        appointmentRepository.save (appt);

        return ResponseEntity.ok(new ApiResponse(true, "Prescription created successfully!", saved));
    }

      //doctor see all the prescription
      public  ResponseEntity<ApiResponse>  getByDoctor(String doctorId){
          List<Prescription> list=prescriptionRepository.findByDoctorId (doctorId);
          return ResponseEntity.ok(new ApiResponse(true, "All prescriptions by doctor", list));

      }

    // Patient can see his own prescriptions
    public ResponseEntity<ApiResponse> getByPatient(String patientId) {
        List<Prescription> list = prescriptionRepository.findByPatientId(patientId);
        return ResponseEntity.ok(new ApiResponse(true, "Patient prescription list", list));
    }

    //also get prescription  by appointment id

    public ResponseEntity<ApiResponse>  getPrescriptionByAppointment(String appointmentID){
        Prescription prescription=prescriptionRepository.findByAppointmentId (appointmentID);
        if (prescription == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "No prescription found!", null));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Prescription fetched successfully", prescription));
    }
    }



