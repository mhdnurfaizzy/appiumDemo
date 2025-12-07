package tests;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;
import org.testng.Assert; // added import


public class sauceLabsTest extends BaseTest {

    @Test()
    public void loginSuccess(){
        loginPage loginPage = new loginPage(driver);

        //Step
        loginPage.openLoginForm();
        loginPage.login("bod@example.com", "10203040");
        System.out.println("Sukses Login");
    }

    @Test()
    public void userCanBuyProduct() {
        loginPage loginPage = new loginPage(driver);
        pages.productDetailPage productDetailPage = new pages.productDetailPage(driver);
        checkoutPage checkout = new checkoutPage(driver);
        cartPage cart = new cartPage(driver);
        paymentPage payment = new paymentPage(driver);
        thankYouPage thankYou = new thankYouPage(driver);
        reviewOrderPage reviewOrder = new reviewOrderPage(driver);

        /* Product Details Page */
        productDetailPage.clickProductDetails();
        productDetailPage.chooseBlueColor();
        productDetailPage.setQuantity(2);
        productDetailPage.addToCart();
        System.out.println("Berhasil menambahkan ke keranjang");
        productDetailPage.toCartBtn();
        /* End of Product Details Page */

        /* Cart Page */
        cart.verifyProductName("Sauce Labs Backpack");
        cart.proceedToCheckout();
        System.out.println("Berhasil ke halaman checkout");
        /*End of Cart Page*/

        /* Checkout Page */
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
        /* End of Checkout Page*/

        /* Payment Page*/
        payment.getTextPaymentHeader();
        payment.fillPayment(
                "Rebecca Winter",
                "3258 1256 7568 7891",
                "03/25",
                "123"
        );
        /* End of Payment Page*/

        /* Review Order Page */
        reviewOrder.getTextTitleReviewOrder();
        String actualProductTitle = reviewOrder.getTextProductTitle();
        String actualTotalItems = reviewOrder.getTextTotalItems();
        String expectedProductTitle = "Sauce Labs Backpack";
        String expectedTotalItems = "2 Items";
        Assert.assertEquals(actualProductTitle, expectedProductTitle, "Product Title is expected");
        Assert.assertEquals(actualTotalItems, expectedTotalItems, "Total Items is expected");
        reviewOrder.placeOrder();
        System.out.println("Berhasil ke halaman Review Order");
        /* End of Review Order Page*/

        /* Thank You Page */
        String actualTextOrderComplete = thankYou.getTextOrderComplete();
        String actualTextOrderCompleteMsg = thankYou.getTextOrderCompleteMsg();
        String expectedTextOrderComplete = "Checkout Complete";
        String expectedTextOrderCompleteMsg = "Your order has been dispatched and will arrive as fast as the pony gallops!";
        Assert.assertEquals(actualTextOrderComplete, expectedTextOrderComplete, "Order Complete Title is expected");
        Assert.assertEquals(actualTextOrderCompleteMsg, expectedTextOrderCompleteMsg, "Order Complete Message is expected");
        System.out.println("Berhasil ke halaman Thank You");
        /* End of Thank You Page*/

    }


}
