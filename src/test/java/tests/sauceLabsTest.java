package tests;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.loginPage;

public class sauceLabsTest extends BaseTest {

    @Test()
    public void login(){
        loginPage loginPage = new loginPage(driver);

        //Step
        loginPage.openLoginForm();
        loginPage.login("bod@example.com", "10203040");
        System.out.println("Sukses Login");
    }
}
