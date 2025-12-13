# 📱 Appium Demo Automation

**Java · TestNG · Selenium · Page Object Model**

This project is a **mobile automation testing framework** built using **Appium**, **Selenium**, and **TestNG**, written in **Java** and structured with the **Page Object Model (POM)** design pattern.

All test scenarios are executed on the **Sauce Labs Demo App** to validate core **e-commerce user flows** such as login, ordering, and product sorting.

---

## ✨ Features

### 1️⃣ Login Test

Validates successful user authentication:

* Uses predefined valid credentials
* Verifies user redirection to the product page

---

### 2️⃣ Buyer Order Flow

Automates a complete purchase journey:

* Login
* Select product
* View product details
* Add to cart
* Checkout
* Payment
* Review order
* Finish order
* Validate **Thank You** page

---

### 3️⃣ Product Filter Test

Validates sorting functionality on the product list page:

* Sort by **price (ascending)**
* Sort by **name (descending)**

---

## 📂 Project Structure

```
src/test/java
│
├── base
│   └── BaseTest.java
│
├── pages
│   ├── CartPage.java
│   ├── CheckoutPage.java
│   ├── LoginPage.java
│   ├── PaymentPage.java
│   ├── ProductDetailPage.java
│   ├── ProductPage.java
│   ├── ReviewOrderPage.java
│   └── ThankYouPage.java
│
└── tests
    └── orderTest.java
    └── productTest.java
    
```

---

## 📄 Key Components

### 🔹 BaseTest.java

* Handles Appium driver setup (UiAutomator2)
* Starts and manages test sessions
* Contains reusable setup logic (including login helper)

---

### 🔹 Pages Package

* Implements **Page Object Model (POM)**
* One class per screen
* Contains element locators and reusable actions

---

### 🔹 SauceLabsTest.java

Includes the following test cases:

* `userCanBuyProduct`
* `testSorting`

---

## 🛠️ Tech Stack

| Component         | Description                        |
| ----------------- | ---------------------------------- |
| Java              | Main programming language          |
| Appium            | Mobile automation framework        |
| Selenium          | WebDriver API for element handling |
| TestNG            | Test runner & reporting            |
| Maven             | Dependency management              |
| Page Object Model | Test design pattern                |

---

## 📦 Dependencies (pom.xml)

Core libraries used:

* `io.appium:java-client`
* `org.seleniumhq.selenium:selenium-java`
* `org.testng:testng`
* `com.google.guava:guava`

---

## ▶️ How to Run the Tests

### 1️⃣ Install Requirements

* Java 8+
* Appium Server
* Appium Inspector
* Android SDK (emulator or real device)
* Maven

---

### 2️⃣ Start Appium Server

```bash
appium
```

---

### 3️⃣ Run Tests with Maven

```bash
mvn clean test
```

---

## 📱 Demo App

This project automates the **Sauce Labs Sample Android App**:

👉 [Download APK](https://github.com/saucelabs/my-demo-app-android/releases/download/2.2.0/mda-2.2.0-25.apk)

---

## 🎯 Purpose of This Project

This repository is created as a **learning and demonstration project** to showcase:

* Clean **Page Object Model** implementation
* Appium driver setup
* TestNG-based test execution
* Realistic e-commerce automation scenarios
* Maintainable and scalable test architecture

---

Siap 👍 Aku tambahin **Notes**-nya dengan wording yang rapi & profesional (aman buat README):

---

## 📝 Notes

> **Known Issue on Demo App Version**

For the application version provided in the link above, there is a **known issue**:

* When scrolling on the **Product List page** and then clicking a product item,
  the application may **crash unexpectedly**.
* This behavior is caused by a **bug in the demo application**, not by the automation framework.
* Test scenarios are designed with this limitation in mind to avoid false negatives.

---
