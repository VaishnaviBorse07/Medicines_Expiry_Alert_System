package com.main;

import com.dao.MedicineDAO;
import com.dao.MedicineDAOImpl;
import com.exception.MedicineNotFoundException;
import com.model.Medicine;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MedicineApp {

    public static void main(String[] args) {

        MedicineDAO dao = new MedicineDAOImpl();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n--- Medicine Expiry Alert System ---");
            System.out.println("1. Add Medicine");
            System.out.println("2. Update Medicine");
            System.out.println("3. Delete Medicine");
            System.out.println("4. Find Medicine by ID");
            System.out.println("5. View All Medicines");
            System.out.println("6. View Near Expiry Medicines");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Supplier: ");
                        String supplier = sc.nextLine();

                        System.out.print("Quantity: ");
                        int quantity = sc.nextInt();

                        System.out.print("Expiry Date (yyyy-mm-dd): ");
                        LocalDate expiry = LocalDate.parse(sc.next());

                        dao.addMedicine(new Medicine(name, supplier, quantity, expiry));
                        break;

                    case 2:
                        System.out.print("Enter Medicine ID: ");
                        int uid = sc.nextInt();

                        Medicine existing = dao.findMedicineById(uid);
                        if (existing == null)
                            throw new MedicineNotFoundException("Medicine not found");

                        sc.nextLine();
                        System.out.print("New Name: ");
                        name = sc.nextLine();

                        System.out.print("New Supplier: ");
                        supplier = sc.nextLine();

                        System.out.print("New Quantity: ");
                        quantity = sc.nextInt();

                        System.out.print("New Expiry Date (yyyy-mm-dd): ");
                        expiry = LocalDate.parse(sc.next());

                        dao.updateMedicine(new Medicine(uid, name, supplier, quantity, expiry));
                        break;

                    case 3:
                        System.out.print("Enter Medicine ID: ");
                        int did = sc.nextInt();

                        if (dao.findMedicineById(did) == null)
                            throw new MedicineNotFoundException("Medicine not found");

                        dao.deleteMedicine(did);
                        break;

                    case 4:
                        System.out.print("Enter Medicine ID: ");
                        int fid = sc.nextInt();

                        Medicine med = dao.findMedicineById(fid);
                        if (med == null)
                            throw new MedicineNotFoundException("Medicine not found");

                        System.out.println(med.getId() + " | " + med.getName() + " | " +
                                med.getSupplier() + " | " + med.getQuantity() + " | " +
                                med.getExpiryDate());
                        break;

                    case 5:
                        List<Medicine> list = dao.findAllMedicines();
                        for (Medicine m : list) {
                            System.out.println(m.getId() + " | " + m.getName() + " | " +
                                    m.getSupplier() + " | " + m.getQuantity() + " | " +
                                    m.getExpiryDate());
                        }
                        break;

                    case 6:
                        System.out.print("Enter days for expiry alert: ");
                        int days = sc.nextInt();

                        List<Medicine> alertList = dao.findNearExpiryMedicines(days);
                        for (Medicine m : alertList) {
                            System.out.println("⚠ ALERT: " + m.getName() +
                                    " expires on " + m.getExpiryDate());
                        }
                        break;

                    case 0:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type");
                sc.nextLine();
                choice = -1;
            } catch (MedicineNotFoundException e) {
                System.out.println(e.getMessage());
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }
}
