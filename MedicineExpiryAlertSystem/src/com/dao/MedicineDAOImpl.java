package com.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.model.Medicine;
import com.util.DBConnection;

public class MedicineDAOImpl implements MedicineDAO {

    public void addMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicines (name, supplier, quantity, expiry_date) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getSupplier());
            ps.setInt(3, medicine.getQuantity());
            ps.setDate(4, Date.valueOf(medicine.getExpiryDate()));
            ps.executeUpdate();

            System.out.println("Medicine added successfully");

        } catch (SQLException e) {
            System.out.println("Error adding medicine");
        }
    }

    public void updateMedicine(Medicine medicine) {
        String sql = "UPDATE medicines SET name=?, supplier=?, quantity=?, expiry_date=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getSupplier());
            ps.setInt(3, medicine.getQuantity());
            ps.setDate(4, Date.valueOf(medicine.getExpiryDate()));
            ps.setInt(5, medicine.getId());
            ps.executeUpdate();

            System.out.println("Medicine updated successfully");

        } catch (SQLException e) {
            System.out.println("Error updating medicine");
        }
    }

    public void deleteMedicine(int id) {
        String sql = "DELETE FROM medicines WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Medicine deleted successfully");

        } catch (SQLException e) {
            System.out.println("Error deleting medicine");
        }
    }

    public Medicine findMedicineById(int id) {
        String sql = "SELECT * FROM medicines WHERE id=?";
        Medicine medicine = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("supplier"),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching medicine");
        }
        return medicine;
    }

    public List<Medicine> findAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("supplier"),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date").toLocalDate()
                );
                list.add(medicine);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching medicines");
        }
        return list;
    }

    public List<Medicine> findNearExpiryMedicines(int days) {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE expiry_date <= ?";

        LocalDate alertDate = LocalDate.now().plusDays(days);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(alertDate));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("supplier"),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date").toLocalDate()
                );
                list.add(medicine);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching near expiry medicines");
        }
        return list;
    }
}
