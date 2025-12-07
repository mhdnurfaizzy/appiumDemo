package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class productPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public productPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Sorting menu button
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Shows current sorting order and displays available sorting options\"]")
    WebElement sortButton;

    // Sorting options inside popup
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Descending order by name\"]")
    WebElement sortNameDescending;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Ascending order by price\"]")
    WebElement sortPriceAscending;


    // Product name list
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]")
    List<WebElement> productNames;

    // Product price list
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/priceTV\"]")
    List<WebElement> productPrices;

    private void waitClick(WebElement el) {
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    // Methods for sorting and verification
    // ----------------------------------------------------------
    // SORT BY NAME - DESCENDING
    // ----------------------------------------------------------
    public void sortByNameDescending() {
        waitClick(sortButton);
        waitClick(sortNameDescending);
    }

    // ----------------------------------------------------------
    // SORT BY PRICE - ASCENDING
    // ----------------------------------------------------------
    public void sortByPriceAscending() {
        waitClick(sortButton);
        waitClick(sortPriceAscending);
    }

    public boolean isSortedByNameDescending() {
        List<String> names = new ArrayList<>();
        for (WebElement el : productNames) {
            names.add(el.getText());
        }

        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());  // Descending

        return names.equals(sorted);
    }

    // ----------------------------------------------------------
    // VERIFY PRICE SORT ASCENDING
    // ----------------------------------------------------------
    public boolean isSortedByPriceAscending() {
        List<Double> prices = new ArrayList<>();
        for (WebElement el : productPrices) {
            prices.add(
                    Double.parseDouble(el.getText().replace("$", "").trim())
            );
        }

        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted); // Ascending

        return prices.equals(sorted);
    }
}
