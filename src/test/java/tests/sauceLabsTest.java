package tests;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.cartPage;
import pages.checkoutPage;
import pages.loginPage;
import org.testng.Assert; // added import
import pages.paymentPage;

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
        productDetailPage.toCartBtn();
        System.out.println("Berhasil menambahkan ke keranjang");
    }

    @Test()
    public void cart() {

    }

    @Test()
    public void E2E() {
        loginPage loginPage = new loginPage(driver);
        pages.productDetailPage productDetailPage = new pages.productDetailPage(driver);
        checkoutPage checkout = new checkoutPage(driver);
        cartPage cart = new cartPage(driver);
        paymentPage payment = new paymentPage(driver);

        //Step
        loginPage.openLoginForm();
        loginPage.login("bod@example.com", "10203040");
        System.out.println("Sukses Login");

        //Add to Cart
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

        // go to cart
        productDetailPage.toCartBtn();
        cart.verifyProductName("Sauce Labs Backpack");
        // Assert “Qty is 2 in cart page”
        String actualQtyCart = cart.verifyTotalItems();
        String expectedQtyCart = "2";
        Assert.assertEquals(actualQtyCart, expectedQtyCart, "Total items in cart is expected");
        cart.proceedToCheckout();

        // checkout
        checkout.enterShippingDetails(
                "Rebecca Winter",
                "Manderley 112",
                "Entrance 1",
                "Truro",
                "Cornwall",
                "89750",
                "United Kingdom"
        );
        checkout.clickToPayment();
        System.out.println("Berhasil ke halaman payment");
        payment.fillPayment(
                "Rebecca Winter",
                "3258 1256 7568 7891",
                "03/25",
                "123"
        );
    }



}
