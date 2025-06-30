A simple Java desktop application to perform CRUD operations (Create, Read, Update, Delete) on users stored in a MySQL database. 

(Used tabbed pane in this one)

Built using Java Swing for GUI, JDBC for database access, and MySQL (via XAMPP) as the backend.

-Features:

  -Create a new user (name, email, password)

  -View user details by ID

  -Update user credentials

  -Delete a user by ID

-Project Structure:

pgsql
Copy
Edit
├── src/
│   ├── DAO.java               # Database access methods
│   ├── User.java              # POJO model class for user entity
│   ├── DataBaseConnection.java # Utility to establish DB connection
│   └── Main.java              # GUI logic using Java Swing
🛠️ Technologies Used
Java (JDK 8 or later)

Swing (Java GUI toolkit)

MySQL (MariaDB via XAMPP)

JDBC

-Setup Instructions:

1.Clone the repo

git clone https://github.com/yourusername/crud_operation_java.git
cd crud_operation_java
2.Setup the MySQL database

-Make sure MySQL (from XAMPP) is running, then:

code:

CREATE DATABASE crud;

USE crud;

CREATE TABLE info (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(100)
);

3.Update DB credentials

In DataBaseConnection.java, set your database name, username, and password:

String url = "jdbc:mysql://localhost:3306/userdb";
String user = "root";
String password = ""; // or your MySQL password

4.Run the application

Open in IntelliJ IDEA

Right-click Main.java → Run

Use the buttons to Add, Read, Update, or Delete users

