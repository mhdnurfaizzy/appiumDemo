package tests;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.loginPage;
import org.testng.Assert; // added import

public class sauceLabsTest extends BaseTest {

    @Test()
    public void login(){
        loginPage loginPage = new loginPage(driver);

        //Step
        loginPage.openLoginForm();
        loginPage.login("bod@example.com", "10203040");
        System.out.println("Sukses Login");
    }

    @Test()
    public void addToCart() {
        loginPage loginPage = new loginPage(driver);
        //Step
        loginPage.openLoginForm();
        loginPage.login("bod@example.com", "10203040");
        System.out.println("Sukses Login");

        //Add to Cart
        pages.productDetailPage productDetailPage = new pages.productDetailPage(driver);
        productDetailPage.clickProductDetails();
        productDetailPage.chooseBlueColor();
        productDetailPage.setQuantity(2);
        // assert Qty
        String actualQty = productDetailPage.getQuantity();
        String expectedQty = "2";
        Assert.assertEquals(actualQty, expectedQty, "Quantity in product detail is expected");
        // assert Product Name
        String actualProductName = productDetailPage.getProductName();
        String expectedProductName = "Sauce Labs Backpack";
        Assert.assertEquals(actualProductName, expectedProductName, "Product Name in product detail is expected");
        // add to cart
        productDetailPage.addToCart();
        System.out.println("Berhasil menambahkan ke keranjang");
    }



}
