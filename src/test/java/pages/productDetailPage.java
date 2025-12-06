package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class productDetailPage {


    AndroidDriver driver;
    public final WebDriverWait wait;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup[1]")
    public WebElement backpackItem;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Blue color\"]")
    public WebElement colorBlue;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    public WebElement plusBtn;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    public WebElement addToCartBtn;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    public WebElement quantityField;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/productTV\"]")
    public WebElement productNameText;

    public productDetailPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickProductDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackItem)).click();
    }

    public void chooseBlueColor() {
        wait.until(ExpectedConditions.elementToBeClickable(colorBlue)).click();
    }

    public void setQuantity(int qty) {
//        if (qty <= 1) return;
        for (int i = 1; i < qty; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(plusBtn)).click();
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(quantityField));
        } catch (Exception ignored) {}
    }

    public String getProductName() {
        String productName = wait.until(ExpectedConditions.visibilityOf(productNameText)).getText();
        System.out.println("Product Name = " + productName);
        return productName;
    }

    public String getQuantity() {
        String actualQty = wait.until(ExpectedConditions.visibilityOf(quantityField)).getText();
        System.out.println("Qty = " + actualQty);
        return actualQty;
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(quantityField));
        } catch (Exception ignored) {}
    }

}
