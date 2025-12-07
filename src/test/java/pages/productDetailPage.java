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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class productDetailPage {


    AndroidDriver driver;
    public final WebDriverWait wait;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
    List<WebElement> productsCard;

    @FindBy(xpath =  "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]")
    public WebElement products;

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

    public void clickProductByName(String targetName) {
        // Build an XPath that finds the title TextView containing the target text (tolerant to surrounding whitespace)
        String escapedName = targetName.replace("'", "\\'");
        String containerXpath = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and contains(normalize-space(@text), '" + escapedName + "')]/ancestor::android.view.ViewGroup[1]";

        int maxAttempts = 6; // try a few times with scrolling
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                // Wait briefly for the container to be present and clickable
                WebElement container = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(containerXpath)));
                System.out.println("Found and clicking product container for '" + targetName + "'");
                container.click();
                return;
            } catch (Exception e) {
                // Not found/clickable yet → try to bring it into view using UiScrollable
                System.out.println("Attempt " + (attempt + 1) + ": product not clickable/found yet ('" + targetName + "'), trying to scroll into view");
                try {
                    // Use UiScrollable to scroll to the text; this is more reliable for lists
                    String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().textContains(\"" + targetName + "\"))";
                    driver.findElement(AppiumBy.androidUIAutomator(uiScroll));
                    // small pause to allow UI to settle
                    Thread.sleep(400);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } catch (Exception scrollEx) {
                    // scrolling failed - maybe end of list
                    System.out.println("Scroll attempt failed or reached end: " + scrollEx.getMessage());
                }
            }
        }

        // Final attempt: try a broader search for the title element and click its parent if found
        try {
            WebElement title = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV"));
            if (title != null && title.getText() != null && title.getText().equalsIgnoreCase(targetName)) {
                WebElement container = title.findElement(AppiumBy.xpath("ancestor::android.view.ViewGroup"));
                wait.until(ExpectedConditions.elementToBeClickable(container)).click();
                return;
            }
        } catch (Exception ignored) {}

        throw new RuntimeException("Product not found or not clickable after scrolling attempts: " + targetName);
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
