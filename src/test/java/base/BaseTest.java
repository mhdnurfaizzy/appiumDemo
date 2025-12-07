package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.loginPage;

import java.net.MalformedURLException;
import java.net.URI;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("16")
                .setDeviceName("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.saucelabs.mydemoapp.android")
                .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity");

        driver = new AndroidDriver(
                URI.create("http://127.0.0.1:4723/wd/hub/").toURL(),
                options
        );
    }

    @BeforeMethod
    public void loginSuccess(){
        loginPage loginPage = new loginPage(driver);

        //Step
        loginPage.openLoginForm();
        loginPage.login("bod@example.com", "10203040");
        System.out.println("Sukses Login");
    }

    @AfterMethod
    public void logout(){
        loginPage loginPage = new loginPage(driver);
        loginPage.logout();
        System.out.println("Sukses Logout");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
