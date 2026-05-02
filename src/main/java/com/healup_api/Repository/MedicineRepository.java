package com.healup_api.Repository;


import com.healup_api.Entity.Medicine_Search.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {

    // 🔥 SMART SEARCH: Contains + IgnoreCase (Partial and Case Insensitive Match)
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
