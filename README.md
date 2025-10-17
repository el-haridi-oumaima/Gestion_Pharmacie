# 💊 Pharmacy Management System

<div align="center">
  
  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
  ![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white)
  ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
  
  <p><i>A desktop application for pharmacy inventory and supplier management</i></p>
</div>

---


## 🎯 Overview

This project is a **Pharmacy Management System** designed to streamline pharmacy operations through efficient inventory and supplier management. The application provides a secure, user-friendly interface for administrators to manage medications, track stock levels, and maintain supplier information.

### Key Highlights:
- 🔐 **Secure Authentication** with password hashing
- 📦 **Real-time Stock Management** with low inventory alerts
- 👥 **Supplier Database** integration
- ⚠️ **Automatic Alerts** for medications below threshold 
- 🖥️ **Modern JavaFX Interface** for intuitive user experience

---


## 🛠️ Technologies

### Core Technologies:
- **Language:** Java
- **GUI Framework:** JavaFX
- **Database:** MySQL
- **Architecture:** MVC (Model-View-Controller)

### Design & Modeling:
- **UML Diagrams:** Use Case, Class Diagram
- **Database Design:** MCD (Conceptual Data Model)
---

## 📸 Screenshots

### Login Interface
<div align="center">
  <img width="1595" height="779" alt="Screenshot 2025-10-15 094624" src="https://github.com/user-attachments/assets/3ac5fffb-5d7c-4296-a499-b19433f0c301" />
  <p><i>Secure administrator authentication</i></p>
</div>

### Dashboard
<div align="center">
  <img width="1600" height="569" alt="image" src="https://github.com/user-attachments/assets/a7ff1f2f-6785-4a73-9558-32f994c5d1fe" />
  <p><i>Main dashboard with quick access to all features</i></p>
</div>

### Medication Management
<div align="center">
  <img width="1604" height="801" alt="Screenshot 2025-10-15 094728" src="https://github.com/user-attachments/assets/2d2f2ac7-5e3d-469b-b9cb-5d6570006cab" />

  <p><i>Complete medication inventory with stock levels</i></p>
</div>


### Supplier Management
<div align="center">
  <img width="1595" height="834" alt="Screenshot 2025-10-15 094703" src="https://github.com/user-attachments/assets/10a94326-90f7-4f90-b564-5b8c14ee3138" />
  <p><i>Supplier database management interface</i></p>
</div>

---

## 🚀 Installation

### Prerequisites:
- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- JavaFX SDK
- IDE (Eclipse, IntelliJ IDEA, or NetBeans)

### Steps:

1. **Clone the repository**
   ```bash
   git clone https://github.com/el-haridi-oumaima/pharmacy-management.git
   cd pharmacy-management
   ```

2. **Set up the database**
   ```sql
   -- Create database
   CREATE DATABASE pharmacy_db;
   
   -- Import the SQL schema
   mysql -u root -p pharmacy_db < database/schema.sql
   ```

3. **Configure database connection**
   ```java
   // Update database credentials in DatabaseConfig.java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy_db";
   private static final String DB_USER = "your_username";
   private static final String DB_PASSWORD = "your_password";
   ```

4. **Build and run**
   ```bash
   # Using Maven
   mvn clean install
   mvn javafx:run
   
   # Or using your IDE
   # Right-click on Main.java → Run
   ```

---

## 🎓 Academic Context

**Course:** Java API avancées 
**Institution:** École des Sciences de l'Information (ESI)  
**Professor:** Mme. HILAL Imane  
**Academic Year:** 2024-2025

---

## 👥 Contributors

<div align="center">

| Name | Role | GitHub |
|------|------|--------|
| **Oumaima EL HARIDI** | Developer | [@el-haridi-oumaima](https://github.com/el-haridi-oumaima) |
| **Salama LAAMIAR** | Developer | [@laami-ar-salama](https://github.com/Salamalaamiar) |

</div>

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---


## 📧 Contact

**Oumaima EL HARIDI**  
📧 Email: elharidioumaima@gmail.com  
💼 LinkedIn: [oumaima-el-haridi](https://www.linkedin.com/in/oumaima-el-haridi-087239301/)  
🐙 GitHub: [@el-haridi-oumaima](https://github.com/el-haridi-oumaima)

---

## 🙏 Acknowledgments

- Thanks to **Mme. HILALI Imane** for guidance and support
- École des Sciences de l'Information (ESI) for the academic framework
- JavaFX community for excellent documentation and resources

---

<div align="center">

### ⭐ If you find this project useful, please consider giving it a star!

**Made with ❤️ by Oumaima EL HARIDI & Salama LAAMIAR**

</div>
