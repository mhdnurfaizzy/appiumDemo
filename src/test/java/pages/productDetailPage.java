package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.AppiumBy;


import java.time.Duration;

public class productDetailPage {


    AndroidDriver driver;
    public final WebDriverWait wait;

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

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    public WebElement toCartBtn;

    public productDetailPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void scrollToProduct(String textFromOutside, String price) {
        WebElement productName = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + textFromOutside + "\"))"));
        WebElement productPrice = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + price + "\"))"));

        productName.isDisplayed();
        productPrice.isDisplayed();

        System.out.println("Sukses find produk by scroll");
    }

    public WebElement getProductContainer(String targetName) {

        String xpath =
                "//android.widget.TextView[@content-desc='Product Title' and @text='" + targetName + "']" +
                        "/ancestor::android.view.ViewGroup[1]";
        return driver.findElement(By.xpath(xpath));
    }

    public void clickProduct() throws InterruptedException {
        WebElement title = getProductContainer("Sauce Labs Backpack");
        title.isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(title)).click();
        System.out.println("Sukses click produk 2");

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

    public void toCartBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(toCartBtn)).click();
    }

}
