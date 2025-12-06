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

public class cartPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public cartPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Elements

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]")
    WebElement productTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    WebElement quantityText;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Confirms products for checkout\"]")
    WebElement proceedToCheckoutBtn;

    // Helper

    private void waitVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Actions

    public void verifyProductName(String expectedName) {
        waitVisible(productTitle);
        Assert.assertEquals(productTitle.getText(), expectedName,
                "❌ Product name is incorrect!");
    }

    public String verifyTotalItems() {
        waitVisible(quantityText);
        String actualQty = wait.until(ExpectedConditions.visibilityOf(quantityText)).getText();
        System.out.println("Qty = " + actualQty);
        return actualQty;
    }

    public void proceedToCheckout() {
        waitClick(proceedToCheckoutBtn);
    }
}
