package tests;


import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class sauceLabsTest extends BaseTest {

    @Test()
    public void clickProduct(){
        WebElement productNameEle = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Product Title\" and @text=\"Sauce Labs Backpack\"]"));
        String productName = productNameEle.getText();
        System.out.println(productName);
    }
}
