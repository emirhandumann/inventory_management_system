package main.java.inventory;

import java.time.LocalDate;

// Sample data for initializing the inventory system.
public class SampleData {

    public static void initializeSampleData(InventoryManager manager) {
        manager.addProduct("Paracetamol", 100, 5.99, "Painkillers", LocalDate.of(2024, 12, 31));
        manager.addProduct("Ibuprofen", 150, 7.99, "Painkillers", LocalDate.of(2023, 11, 30));
        manager.addProduct("Amoxicillin", 200, 12.99, "Antibiotics", LocalDate.of(2025, 1, 15));
        manager.addProduct("Vitamin C", 300, 9.99, "Vitamins", LocalDate.of(2024, 6, 30));
        manager.addProduct("Vitamin D", 250, 8.99, "Vitamins", LocalDate.of(2024, 9, 30));
        manager.addProduct("Aspirin", 180, 6.99, "Painkillers", LocalDate.of(2023, 12, 15));
        manager.addProduct("Penicillin", 120, 14.99, "Antibiotics", LocalDate.of(2025, 3, 10));
        manager.addProduct("Multivitamin", 400, 11.99, "Vitamins", LocalDate.of(2024, 8, 20));
        manager.addProduct("Lisinopril", 130, 19.99, "Antihypertensives", LocalDate.of(2025, 5, 15));
        manager.addProduct("Metformin", 220, 17.49, "Antidiabetics", LocalDate.of(2023, 10, 31));
        manager.addProduct("Simvastatin", 180, 25.99, "Cholesterol Meds", LocalDate.of(2024, 11, 20));
        manager.addProduct("Omeprazole", 160, 14.99, "Antacids", LocalDate.of(2023, 9, 15));
        manager.addProduct("Amlodipine", 140, 21.99, "Antihypertensives", LocalDate.of(2025, 2, 28));
        manager.addProduct("Albuterol", 90, 29.99, "Bronchodilators", LocalDate.of(2024, 1, 31));
        manager.addProduct("Hydrochlorothiazide", 110, 15.49, "Diuretics", LocalDate.of(2025, 6, 30));
        manager.addProduct("Atenolol", 150, 22.99, "Beta Blockers", LocalDate.of(2024, 3, 31));
        manager.addProduct("Furosemide", 100, 18.99, "Diuretics", LocalDate.of(2025, 7, 31));
        manager.addProduct("Zoloft", 80, 34.99, "Antidepressants", LocalDate.of(2024, 4, 30));
        manager.addProduct("Prozac", 75, 32.99, "Antidepressants", LocalDate.of(2024, 5, 31));
        manager.addProduct("Lipitor", 190, 27.99, "Cholesterol Meds", LocalDate.of(2025, 8, 31));
        manager.addProduct("Zocor", 170, 23.99, "Cholesterol Meds", LocalDate.of(2025, 9, 30));
        manager.addProduct("Paxil", 60, 33.99, "Antidepressants", LocalDate.of(2024, 6, 30));
        manager.addProduct("Claritin", 250, 12.99, "Antihistamines", LocalDate.of(2024, 7, 31));
        manager.addProduct("Zyrtec", 240, 13.99, "Antihistamines", LocalDate.of(2024, 8, 31));
        manager.addProduct("Allegra", 230, 14.99, "Antihistamines", LocalDate.of(2024, 9, 30));
        manager.addProduct("Benadryl", 260, 9.99, "Antihistamines", LocalDate.of(2023, 10, 31));
        manager.addProduct("Glucophage", 200, 16.99, "Antidiabetics", LocalDate.of(2024, 10, 31));
        manager.addProduct("Crestor", 185, 28.99, "Cholesterol Meds", LocalDate.of(2025, 11, 30));
        manager.addProduct("Advair", 95, 45.99, "Bronchodilators", LocalDate.of(2024, 12, 31));
        manager.addProduct("Nexium", 170, 24.99, "Antacids", LocalDate.of(2025, 1, 15));
        manager.addProduct("Singulair", 120, 26.99, "Anti-Inflammatories", LocalDate.of(2025, 2, 28));
        manager.addProduct("Plavix", 130, 31.99, "Anticoagulants", LocalDate.of(2025, 3, 31));
        manager.addProduct("Lasix", 110, 18.99, "Diuretics", LocalDate.of(2025, 4, 30));
        manager.addProduct("Toprol XL", 140, 29.99, "Beta Blockers", LocalDate.of(2025, 5, 31));
        manager.addProduct("Seroquel", 85, 35.99, "Antipsychotics", LocalDate.of(2024, 11, 30));
        manager.addProduct("Effexor XR", 70, 36.99, "Antidepressants", LocalDate.of(2025, 7, 31));
        manager.addProduct("Lexapro", 90, 34.99, "Antidepressants", LocalDate.of(2024, 12, 31));
        manager.addProduct("Zantac", 155, 22.99, "Antacids", LocalDate.of(2025, 8, 31));
        manager.addProduct("Diovan", 125, 27.99, "Antihypertensives", LocalDate.of(2025, 9, 30));
        manager.addProduct("Keflex", 170, 19.99, "Antibiotics", LocalDate.of(2025, 11, 30));
        manager.addProduct("Celebrex", 100, 29.99, "Painkillers", LocalDate.of(2025, 12, 31));
        manager.addProduct("Flomax", 80, 25.99, "Alpha Blockers", LocalDate.of(2025, 1, 31));
        manager.addProduct("Actos", 110, 32.99, "Antidiabetics", LocalDate.of(2025, 2, 28));
        manager.addProduct("Lantus", 70, 45.99, "Insulin", LocalDate.of(2025, 3, 31));
        manager.addProduct("Levaquin", 95, 28.99, "Antibiotics", LocalDate.of(2025, 4, 30));
        manager.addProduct("Xanax", 60, 39.99, "Anxiolytics", LocalDate.of(2024, 10, 31));
        manager.addProduct("Ambien", 75, 38.99, "Sedatives", LocalDate.of(2024, 11, 30));
        manager.addProduct("Valium", 65, 37.99, "Anxiolytics", LocalDate.of(2024, 12, 31));
        manager.addProduct("Cialis", 55, 49.99, "Erectile Dysfunction", LocalDate.of(2025, 1, 31));
        manager.addProduct("Viagra", 50, 51.99, "Erectile Dysfunction", LocalDate.of(2025, 2, 28));
        manager.addProduct("Zithromax", 130, 29.99, "Antibiotics", LocalDate.of(2025, 3, 31));
        manager.addProduct("OxyContin", 45, 59.99, "Painkillers", LocalDate.of(2025, 4, 30));
        manager.addProduct("Synthroid", 150, 24.99, "Hormones", LocalDate.of(2025, 5, 31));
        manager.addProduct("Norvasc", 140, 22.99, "Antihypertensives", LocalDate.of(2025, 6, 30));
        manager.addProduct("Percocet", 50, 41.99, "Painkillers", LocalDate.of(2025, 7, 31));
        manager.addProduct("Ultram", 85, 27.99, "Painkillers", LocalDate.of(2025, 8, 31));
        manager.addProduct("Prilosec", 160, 23.99, "Antacids", LocalDate.of(2025, 9, 30));
        manager.addProduct("Coumadin", 90, 19.99, "Anticoagulants", LocalDate.of(2025, 10, 31));
    }
}
