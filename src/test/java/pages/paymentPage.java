package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class paymentPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public paymentPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Locators

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/paymentTV")
    WebElement paymentHeaderText;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cardNumberTIL") // wrapper
    WebElement cardSection;

    @FindBy(id = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]")
    WebElement fullName;

    @FindBy(id = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/cardNumberET\"]")
    WebElement cardNumber;

    @FindBy(id = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/expirationDateET\"]")
    WebElement expDate;

    @FindBy(id = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/securityCodeET\"]")
    WebElement securityCode;

    @FindBy(id = "//android.widget.CheckBox[@content-desc=\"Select if User billing address and shipping address are same\"]")
    WebElement billingAddressCheckbox;

    @FindBy(id = "//android.widget.Button[@content-desc=\"Saves payment info and launches screen to review checkout data\"]")
    WebElement reviewOrderBtn;

    @FindBy(id = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/paymentDetailsTV\"]")
    WebElement descriptionMessage; // “You will not be charged…”


    // Helpers

    private void waitVisible(WebElement el) {
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    private void waitClick(WebElement el) {
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    private void inputText(WebElement el, String text) {
        waitVisible(el);
        el.clear();
        el.sendKeys(text);
    }


    // Actions

    public void verifyDescriptionMessage() {
        waitVisible(descriptionMessage);
        Assert.assertTrue(
                descriptionMessage.getText().contains("You will not be charged"),
                "Payment description message is not displayed correctly!"
        );
    }

    public void enterFullName(String name) {
        inputText(fullName, name);
    }

    public void enterCardNumber(String number) {
        inputText(cardNumber, number);
    }

    public void enterExpDate(String date) {
        inputText(expDate, date);
    }

    public void enterSecurityCode(String code) {
        inputText(securityCode, code);
    }

    public void tickBillingAddress() {
        // Verify if the checkbox is selected
        boolean isChecked = billingAddressCheckbox.isSelected();

        if (isChecked) {
            System.out.println("Checkbox is ticked.");
        } else {
            System.out.println("Checkbox is not ticked.");
        }

        // Optional: Perform an action if the checkbox is not ticked
        if (!isChecked) {
            billingAddressCheckbox.click(); // Click to tick the checkbox
            System.out.println("Checkbox was not ticked, now it is clicked.");
        }
    }

    public void clickReviewOrder() {
        waitClick(reviewOrderBtn);
    }


    // Full Payment Flow

    public void fillPayment(
            String name,
            String num,
            String exp,
            String cvv
    ) {
        verifyDescriptionMessage();
        enterFullName(name);
        enterCardNumber(num);
        enterExpDate(exp);
        enterSecurityCode(cvv);
        tickBillingAddress();
        clickReviewOrder();
    }
}
