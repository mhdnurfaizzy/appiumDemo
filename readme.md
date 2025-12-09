📱 Appium Demo Automation (Java + TestNG + Selenium)

This project is a mobile automation framework built using Appium, Selenium, and TestNG, written in Java and designed using the Page Object Model (POM).
All tests are executed on the Sauce Labs Demo App to validate core e-commerce flows such as login, ordering, and product filtering.

---

Features

1. Login Test

Validates successful login into the app:
	•	Uses predefined valid credentials
	•	Verifies user is redirected to the product page

2. Buyer Order Flow

Automates complete purchase scenario:
	•	Login
	•	Select product
	•	View Product Detail
	•	Add to Cart
	•	Checkout
	•	Payment
	•	Review Order
	•	Finish order
	•	Validate Thank You page

3. Product Filter Test

Validates sorting functionality on product list page:
	•	Ascending price filter
	•	Descending name filter

⸻

Project Structure

src/test/java
│
├── base
│   └── BaseTest.java
│
├── pages
│   ├── cartPage.java
│   ├── checkoutPage.java
│   ├── loginPage.java
│   ├── paymentPage.java
│   ├── productDetailPage.java
│   ├── productPage.java
│   ├── reviewOrderPage.java
│   └── thankYouPage.java
│
└── tests
    └── sauceLabsTest.java

📄 BaseTest.java
	•	Handles driver setup (Appium UiAutomator2)
	•	Starts session
	•	Contains loginSuccess test

📄 Pages Folder

Implements POM structure:
	•	Each screen has its own class
	•	Contains elements + reusable actions

📄 sauceLabsTest.java

Contains:
	•	buyerCanOrder
	•	verify product filter ascending
	•	verify product filter descending

⸻

🛠️ Tech Stack

Component	Description
Java	Main programming language
Appium	Mobile automation framework
Selenium	WebDriver API for element handling
TestNG	Test runner & reporting
Maven	Dependency manager
Page Object Model	Test structure design pattern


⸻

📦 Dependencies (pom.xml)

Core libraries used:
	•	io.appium:java-client
	•	org.seleniumhq.selenium:selenium-java
	•	org.testng:testng
	•	com.google.guava:guava

⸻

How to Run the Tests

1. Install Requirements
	•	Java 8+
	•	Appium Server
	• Appium Inspector
	•	Android SDK (emulator or real device)
	•	Maven

3. Start Appium Server

appium

3. Run tests with Maven

mvn clean test


⸻

📱 Demo App

This project automates the Sauce Labs Sample App:
  [apk
a-2.2.0-25.apk.](https://github.com/saucelabs/my-demo-app-android/releases/download/2.2.0/mda-2.2.0-25.apk)

⸻

🎯 Purpose of This Project

This project is created as a learning and demonstration repo for building a clean mobile automation framework covering:
	•	Page Object Model (POM)
	•	Appium driver setup
	•	TestNG tests
	•	Realistic e-commerce test cases
	•	Clean, maintainable code structure

⸻



