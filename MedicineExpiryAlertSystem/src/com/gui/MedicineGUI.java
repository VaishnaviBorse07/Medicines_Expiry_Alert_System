package com.gui;

import com.dao.MedicineDAO;
import com.dao.MedicineDAOImpl;
import com.exception.MedicineNotFoundException;
import com.model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MedicineGUI extends JFrame {

    JTextField txtId, txtName, txtSupplier, txtQuantity, txtExpiry;
    JTextArea textArea;

    MedicineDAO dao = new MedicineDAOImpl();

    public MedicineGUI() {

        setTitle("Medicine Expiry Alert System");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Medicine ID"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Name"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Supplier"));
        txtSupplier = new JTextField();
        panel.add(txtSupplier);

        panel.add(new JLabel("Quantity"));
        txtQuantity = new JTextField();
        panel.add(txtQuantity);

        panel.add(new JLabel("Expiry Date (yyyy-mm-dd)"));
        txtExpiry = new JTextField();
        panel.add(txtExpiry);

        add(panel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnFind = new JButton("Find");
        JButton btnFindAll = new JButton("Find All");
        JButton btnAlert = new JButton("Near Expiry");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnFind);
        btnPanel.add(btnFindAll);
        btnPanel.add(btnAlert);

        add(btnPanel, BorderLayout.CENTER);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // BUTTON ACTIONS
        btnAdd.addActionListener(e -> addMedicine());
        btnUpdate.addActionListener(e -> updateMedicine());
        btnDelete.addActionListener(e -> deleteMedicine());
        btnFind.addActionListener(e -> findMedicine());
        btnFindAll.addActionListener(e -> findAllMedicines());
        btnAlert.addActionListener(e -> nearExpiry());

        setVisible(true);
    }
    void addMedicine() {
        Medicine m = new Medicine(
                txtName.getText(),
                txtSupplier.getText(),
                Integer.parseInt(txtQuantity.getText()),
                LocalDate.parse(txtExpiry.getText())
        );
        dao.addMedicine(m);
        textArea.setText("Medicine added successfully");
    }

    void updateMedicine() {
        Medicine m = new Medicine(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                txtSupplier.getText(),
                Integer.parseInt(txtQuantity.getText()),
                LocalDate.parse(txtExpiry.getText())
        );
        dao.updateMedicine(m);
        textArea.setText("Medicine updated");
    }

    void deleteMedicine() {
        dao.deleteMedicine(Integer.parseInt(txtId.getText()));
        textArea.setText("Medicine deleted");
    }

    void findMedicine() {
        try {
            String idText = txtId.getText().trim();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter Medicine ID",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idText);
            Medicine m = dao.findMedicineById(id);

            if (m == null)
                throw new MedicineNotFoundException("Medicine not found");

            textArea.setText(
                    m.getId() + " | " +
                    m.getName() + " | " +
                    m.getSupplier() + " | " +
                    m.getQuantity() + " | " +
                    m.getExpiryDate()
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Medicine ID must be a number",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (MedicineNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Not Found",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    void findAllMedicines() {
        List<Medicine> list = dao.findAllMedicines();
        textArea.setText("");
        for (Medicine m : list) {
            textArea.append(
                    m.getId() + " | " +
                    m.getName() + " | " +
                    m.getSupplier() + " | " +
                    m.getQuantity() + " | " +
                    m.getExpiryDate() + "\n"
            );
        }
    }

    void nearExpiry() {
        List<Medicine> list = dao.findNearExpiryMedicines(30);
        textArea.setText("");
        for (Medicine m : list) {
            textArea.append(
                    "Alert  " + m.getName() + " expires on " + m.getExpiryDate() + "\n"
            );
        }
    }

    public static void main(String[] args) {
        new MedicineGUI();
    }
}

 
