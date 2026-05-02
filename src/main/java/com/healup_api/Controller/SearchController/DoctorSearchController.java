package com.healup_api.Controller.SearchController;

import com.healup_api.API_Response.ApiResponse;
import com.healup_api.Service.SearchService.DocotorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorSearchController {  //http://localhost:8080/api/doctors/search?keyword=md
    @Autowired
    private DocotorSearchService docotorSearchSerive;
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchdoctor(@RequestParam String keyword){
        return docotorSearchSerive.SearchDoctor (keyword);
    }
}
