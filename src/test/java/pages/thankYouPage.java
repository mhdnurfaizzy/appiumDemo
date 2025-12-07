package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class thankYouPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public thankYouPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Elements
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/completeTV\"]")
    WebElement checkoutCompleteTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/orderTV\"]")
    WebElement orderSuccessMsg;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to open catalog\"]")
    WebElement backToHomeBtn;
    // Helper
    private void waitVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Actions
    public String getTextOrderComplete() {
        waitVisible(checkoutCompleteTitle);
        String completeTitle = checkoutCompleteTitle.getText();
        System.out.println("Checkout Complete Title: " + completeTitle);
        return completeTitle;
    }

    public String getTextOrderCompleteMsg() {
        waitVisible(orderSuccessMsg);
        String completeOrderMsg = orderSuccessMsg.getText();
        System.out.println("Checkout Complete Title: " + completeOrderMsg);
        return completeOrderMsg;
    }

    public void clickBackToHome() {
        waitVisible(backToHomeBtn);
        backToHomeBtn.click();
    }



}
