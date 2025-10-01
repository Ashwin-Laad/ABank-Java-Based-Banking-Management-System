# Java-Based Secure Financial Transaction Management System

Here is a Java-based Financial Transaction Management system designed to simulate core banking operations with a secure, user-friendly interface. It allows users to perform banking transactions, manage accounts, and verify identity via email OTP, providing a comprehensive understanding of banking workflows and transaction management.

# Features

User Registration & Multi-Step Sign-Up: Collects personal and account details, including PAN, Aadhaar, income, and account type.

Email OTP Verification: Ensures secure access to the system via OTP sent to the user’s registered email.

Account Management:

Deposit funds

Cash withdrawals

Balance inquiry

Mini statement generation

PIN change functionality

Transaction Logging: Stores all individual transactions in a database, allowing accurate balance calculations.

GUI-Based Interface: Built with Java Swing for a visually intuitive experience.

Secure Database Integration: MySQL database handles user data, account details, and transaction history.

# Technologies Used

Java – Core language for logic and GUI (Swing).

MySQL – Database management for storing user and transaction data.

Java Swing – Desktop GUI framework.

HTTP/Java Mail API – OTP email verification (via SendGrid).

OOP Principles – Modular and maintainable code structure.

# Project Structure
Bank-System/
│
├── BankFeatures/
│   ├── Deposit.java
│   ├── Withdrawl.java
│   ├── BalanceEnquiry.java
│   ├── Statement.java
│   └── PinChange.java
│
├── Con.java
├── EmailLogin.java
├── MainScreen.java
├── SignUp2.java
├── SignUp3.java
└── resources/
    ├── Icon/
    │   ├── icon1.jpeg
    │   └── signupback.jpeg
    └── modif.jpeg
