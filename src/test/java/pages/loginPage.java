package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {

    AndroidDriver driver;
    private final WebDriverWait wait;

    // Menu
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"View menu\"]")
    public WebElement humbergerBtn;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Login Menu Item\"]")
    public WebElement loginBtn;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Logout Menu Item\"]")
    public WebElement logoutBtn;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    public WebElement logoutBtnConfirm;

    // Login Page
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]")
    public WebElement usernameField;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/passwordET\"]")
    public WebElement passwordField;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to login with given credentials\"]")
    public WebElement loginButton;

    public loginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openLoginForm() {
        wait.until(ExpectedConditions.elementToBeClickable(humbergerBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(humbergerBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtnConfirm)).click();
    }

}
