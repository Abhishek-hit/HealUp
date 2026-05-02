package com.healup_api.Service.Medicine;

import com.healup_api.Entity.Medicine_Search.Medicine;
import com.healup_api.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    // 1. POST: Save data
    // Ek sath poori list save karne ke liye
    public List<Medicine> saveAllMedicines(List<Medicine> medicines) {
        return medicineRepository.saveAll(medicines);
    }

    // 2. GET: Smart Search
    public List<Medicine> searchMedicines(String query) {
        return medicineRepository.findByNameContainingIgnoreCase(query);
    }

    // 3. GET All
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // 4. PUT: Update data
    public Medicine updateMedicine(String id, Medicine updatedData) {
        Optional<Medicine> existingMedicineOpt = medicineRepository.findById(id);

        if (existingMedicineOpt.isPresent()) {
            Medicine existingMedicine = existingMedicineOpt.get();

            // MongoDB mein purane data ko update karna
            existingMedicine.setName(updatedData.getName());
            existingMedicine.setIngredients(updatedData.getIngredients());
            existingMedicine.setUses(updatedData.getUses());
            existingMedicine.setHowToUse(updatedData.getHowToUse());
            existingMedicine.setSideEffects(updatedData.getSideEffects());
            existingMedicine.setPrecautions(updatedData.getPrecautions());
            existingMedicine.setPrice(updatedData.getPrice());
            existingMedicine.setAlternatives(updatedData.getAlternatives());

            return medicineRepository.save(existingMedicine);
        }
        return null;
    }
}