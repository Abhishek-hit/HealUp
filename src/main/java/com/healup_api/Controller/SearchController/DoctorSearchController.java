package com.healup_api.Controller.SearchController;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Service.SearchService.DocotorSearchSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorSearchController {
    @Autowired
    private DocotorSearchSerive docotorSearchSerive;
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchdoctor(@RequestParam String keyword){
        return docotorSearchSerive.SearchDoctor (keyword);
    }
}
