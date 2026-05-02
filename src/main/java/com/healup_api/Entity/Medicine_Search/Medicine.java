package com.healup_api.Entity.Medicine_Search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medicines")
public class Medicine {

    @Id // Yeh Mongo ka apna annotation hai
    private String id; // ID ab String hogi (e.g., "64f1b2c3e4...")

    private String name;
    private String ingredients;
    private String uses;
    private String howToUse;
    private String sideEffects;
    private String precautions;
    private String price;

    // MongoDB mein yeh array ki tarah automatically embed ho jayega
    private List<Alternative> alternatives;

}