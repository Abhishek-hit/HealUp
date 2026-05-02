package com.healup_api.Controller.MedicineSearch;

import com.healup_api.Entity.Medicine_Search.Medicine;
import com.healup_api.Service.Medicine.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*") // React se connect karne ke liye
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // POST: Data add karega
    // URL: POST http://localhost:8080/api/medicines/bulk
    @PostMapping()
    public ResponseEntity<List<Medicine>> addMultipleMedicines(@RequestBody List<Medicine> medicines) {
        List<Medicine> savedMedicines = medicineService.saveAllMedicines(medicines);
        return new ResponseEntity<>(savedMedicines, HttpStatus.CREATED);
    }
    // GET: Search karega
    // Example: /api/medicines/search?query=aug
    @GetMapping("/search")
    public ResponseEntity<?> searchMedicine(@RequestParam("query") String query) {
        List<Medicine> results = medicineService.searchMedicines(query);

        if (!results.isEmpty()) {
            return ResponseEntity.ok(results.get(0)); // Pehla match dega
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Medicine not found\"}");
        }
    }

    // PUT: Data update karega
    // Example: /api/medicines/64f1b2c3e4d5
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable String id, @RequestBody Medicine medicine) {
        Medicine updated = medicineService.updateMedicine(id, medicine);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Medicine ID not found\"}");
        }
    }

    // GET: Saari medicines dekhega
    @GetMapping
    public ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }
}
