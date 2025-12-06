package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class reviewOrderPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public reviewOrderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Elements
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/enterShippingAddressTV\"]")
    WebElement textReviewOrderTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]")
    WebElement productTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemNumberTV\"]")
    WebElement textTotalItems;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Completes the process of checkout\"]")
    WebElement placeOrderBtn;

    // Helper
    private void waitVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Actions
    public String getTextTitleReviewOrder() {
        waitVisible(textReviewOrderTitle);
        String pageTitle = textReviewOrderTitle.getText();
        System.out.println("Review Order Page Title: " + pageTitle);
        return pageTitle;
    }

    public String getTextProductTitle() {
        waitVisible(productTitle);
        String productTitleText = productTitle.getText();
        System.out.println("Product Title: " + productTitleText);
        return productTitleText;
    }

    public String getTextTotalItems() {
        waitVisible(textTotalItems);
        String totalItemsText = textTotalItems.getText();
        System.out.println("Product Title: " + totalItemsText);
        return totalItemsText;
    }

    public void placeOrder() {
        waitVisible(placeOrderBtn);
        placeOrderBtn.click();
        System.out.println("Clicked Place Order Button");
    }
}
