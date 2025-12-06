package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class checkoutPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public checkoutPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // 🔹 Checkout Form Fields
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/fullNameET\"]")
    WebElement fullNameField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/address1ET\"]")
    WebElement address1Field;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/address2ET\"]")
    WebElement address2Field;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/cityET\"]")
    WebElement cityField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/stateET\"]")
    WebElement stateField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/zipET\"]")
    WebElement zipField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/countryET\"]")
    WebElement countryField;

    // 🔹 Button
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Saves user info for checkout\"]")
    WebElement toPaymentBtn;


    // Methods for interactions
    private void waitAndType(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    private void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Actions

    public void enterShippingDetails(String fullName, String addr1, String addr2,
                                     String city, String state, String zip, String country) {

        waitAndType(fullNameField, fullName);
        waitAndType(address1Field, addr1);
        waitAndType(address2Field, addr2);
        waitAndType(cityField, city);
        waitAndType(stateField, state);
        waitAndType(zipField, zip);
        waitAndType(countryField, country);
    }

    public void clickToPayment() {
        waitAndClick(toPaymentBtn);
    }
}
