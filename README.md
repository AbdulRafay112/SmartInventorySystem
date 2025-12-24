# Smart Inventory Management System 🛒

This is a Full Stack project built with **Spring Boot (Backend)** and **React + Vite (Frontend)** using **MySQL Database**.

## 🛠️ Prerequisites
Before running, make sure you have:
1. Java Development Kit (JDK 17 or 21)
2. Node.js installed
3. MySQL Server installed

---

## 🚀 How to Run this Project

### Step 1: Database Setup
1. Open MySQL Workbench.
2. Create a new schema named `inventorydb`.
3. Import the `SLS.sql` file located in the `database` folder.
4. **Important:** Go to `backend/src/main/resources/application.properties` and update the `spring.datasource.password` with YOUR MySQL password.

### Step 2: Backend Setup (Spring Boot)
1. Open the `backend` folder in VS Code or IntelliJ.
2. Let Maven download dependencies.
3. Run `SmartinventoryApplication.java`.
4. Backend will start at: `http://localhost:8080`

### Step 3: Frontend Setup (React)
1. Open terminal inside the `frontend` folder.
2. Run command: `npm install` (This will download node_modules).
3. Run command: `npm run dev`.
4. Frontend will start at: `http://localhost:5173`

---

## ✅ Features
- Add Food Items
- View Inventory List
- Delete Items
- Download PDF Report