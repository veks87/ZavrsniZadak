package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

import java.util.List;

public class SportRealitySearchFunctionalityTest extends BaseTest {

    @Test
    public void searchFunctionalityTest() throws InterruptedException {
        String url = "https://www.sportreality.ba/";
        String searchTerm = "Kander";

        SportRealityHomePage homePage = new SportRealityHomePage(driver);
        homePage.searchFunctionality(url, searchTerm);

        WebElement listOfProducts = driver.findElement(By.className("product-listing-items"));
        List<WebElement> allProducts = listOfProducts.findElements(By.className("list-class"));

        for (WebElement product : allProducts) {
            String titleOfProduct = product.findElement(By.className("title")).getText().toUpperCase();
            System.out.println(titleOfProduct);


            Assert.assertTrue("Article: \n" + titleOfProduct + "\n does not contain search term in the title", titleOfProduct.contains(searchTerm.toUpperCase()));
        }

        Thread.sleep(6000);
    }


}
