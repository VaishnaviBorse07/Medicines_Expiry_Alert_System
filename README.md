# Medicines_Expiry_Alert_System

A Java-based application designed to manage medicines efficiently and prevent wastage by providing alerts for near-expiry medicines. The project is implemented using **Core Java, JDBC, MySQL, and Java Swing**, following proper **OOP principles and DAO design pattern**.

---

## 📌 Project Objective

The main objective of the **Medicine Expiry Alert System** is to:

* Manage medicine records in a structured manner
* Perform complete **CRUD operations** (Create, Read, Update, Delete)
* Track medicine expiry dates
* Provide alerts for medicines that are close to expiry
* Reduce medicine wastage and improve inventory control

This system can be used in **medical stores, hospitals, and pharmacies**.

---

## 📝 Project Description

The Medicine Expiry Alert System allows users to add, update, delete, and view medicine details such as name, supplier, quantity, and expiry date. The system stores all records in a MySQL database and retrieves them using JDBC.

The application provides:

* A **console-based version** for learning core Java concepts
* A **GUI-based version (Java Swing)** for better usability
* A **near-expiry alert feature** that lists medicines expiring within a specified number of days

The project follows a clean architecture using **Model–DAO–GUI/Main structure**, making it easy to understand, maintain, and extend.

---

## 🎯 Features

* Add new medicine details
* Update existing medicine records
* Delete medicine records
* Search medicine by ID
* View all medicines
* View near-expiry medicines
* Custom exception handling for invalid operations
* User-friendly GUI using Java Swing

---

## 🧩 Tasks Performed in the Project

1. Designed database schema using MySQL
2. Created model (POJO) class for Medicine
3. Implemented DAO interface and DAO implementation
4. Established database connectivity using JDBC
5. Implemented CRUD operations
6. Added expiry alert logic using `LocalDate`
7. Implemented custom exception handling
8. Developed GUI using Java Swing
9. Integrated GUI with DAO layer

---

## 🛠️ Concepts Used

* Object-Oriented Programming (OOP)

  * Encapsulation
  * Constructors
  * Abstraction
  * Inheritance
* DAO (Data Access Object) Design Pattern
* JDBC (Java Database Connectivity)
* Exception Handling

  * Custom Exceptions
  * Try-with-resources
* Collections (`List`, `ArrayList`)
* Date and Time API (`LocalDate`)
* Event Handling in Swing

---

## 💻 Technologies Used

| Technology | Description           |
| ---------- | --------------------- |
| Java       | Core Java & Swing     |
| JDBC       | Database connectivity |
| MySQL      | Backend database      |
| Swing      | GUI development       |
| SQL        | Database queries      |
| GitHub     | Version control       |

---

## 🗂️ Project Structure

```
com.main        → Main application (console version)
com.gui         → GUI application (Swing)
com.dao         → DAO interface & implementation
com.model       → Model (POJO) classes
com.util        → Database connection utility
com.exception   → Custom exceptions
```

---

## 🚀 How to Run the Project

1. Create MySQL database and table
2. Update database credentials in `DBConnection.java`
3. Compile the project
4. Run:

   * `MedicineApp.java` for console version
   * `MedicineGUI.java` for GUI version

---

## 📈 Future Enhancements

* Login authentication (Admin/User)
* Email/SMS alerts for expiry
* JTable-based medicine display
* Stock-level alerts
* Export reports to PDF/Excel
