package com.dao;

import java.util.List;
import com.model.Medicine;

public interface MedicineDAO {

    void addMedicine(Medicine medicine);

    void updateMedicine(Medicine medicine);

    void deleteMedicine(int id);

    Medicine findMedicineById(int id);

    List<Medicine> findAllMedicines();

    List<Medicine> findNearExpiryMedicines(int days);
}
