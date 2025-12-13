package tests;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;
import org.testng.Assert; // added import


public class productTest extends BaseTest {

    @Test
    public void testSorting() {
        productPage productsPage = new productPage(driver);

        // Sort by Product Name Descending
        productsPage.sortByNameDescending();
        Assert.assertTrue(productsPage.isSortedByNameDescending(),
                "Product list NOT sorted by name descending");

        // Sort by Price Ascending
        productsPage.sortByPriceAscending();
        Assert.assertTrue(productsPage.isSortedByPriceAscending(),
                "Product list NOT sorted by price ascending");
    }




}
