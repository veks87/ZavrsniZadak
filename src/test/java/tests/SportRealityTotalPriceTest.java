package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.text.DecimalFormat;

public class SportRealityTotalPriceTest extends BaseTest{
    final DecimalFormat df = new DecimalFormat("0.00");
    @Test
    public void totalPriceTest() throws InterruptedException {


        String url = "https://www.sportreality.ba/";
        String searchTerm = "Nike";
        String selectProduct = "Tekstil";
        String selectFilter = "Trenerka";
        String selectGander = "za muškarce";
        int articleNumber = 3;
        String size = "L";
        String quantity = "3";

        SportRealityHomePage homePage = new SportRealityHomePage(driver);
        homePage.searchFunctionality(url, searchTerm);

        SportRealityCategoryPage categoryPage = new SportRealityCategoryPage(driver);
        categoryPage.sendFilter(selectProduct);

        SportRealityFilterPage filterPage = new SportRealityFilterPage(driver);
        filterPage.sendFilters(selectFilter,selectGander,articleNumber);

        SportRealityArticlePage articlePage = new SportRealityArticlePage(driver);
        articlePage.selectSizeAndAddToChart(size);

        SportRealityMineCartPage mineCartPage = new SportRealityMineCartPage(driver);
        mineCartPage.sendSelectQuantity(quantity);

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-total-product")));
        WebElement productContainer = driver.findElement(By.tagName("tbody"));
        WebElement priceWithDiscount = productContainer.findElement(By.className("cart-price-discount"));
        WebElement totalPrice = productContainer.findElement(By.className("cart-total-product"));

        String articlePriceDiscount = priceWithDiscount.getText();
        String allPrice = totalPrice.getText();
        System.out.println("Price of article " + articlePriceDiscount);
        System.out.println("Total price " + allPrice);

        float articlePriceFixed = Float.parseFloat(articlePriceDiscount.replace("BAM","").replace(",","."));
        float allPriceFixed = Float.parseFloat(allPrice.replace("BAM","").replace(",","."));
        float quantityFixed = Float.parseFloat(quantity);
        float total = Float.parseFloat(df.format(articlePriceFixed*quantityFixed));
        System.out.println("Float price " + articlePriceFixed);
        System.out.println("Float all price " + allPriceFixed);
        System.out.println("Quantity " + quantityFixed);
        System.out.println("Total " + total);


        Assert.assertTrue("Total price of articles is not correct!",total == allPriceFixed);

        Thread.sleep(6000);
    }
}
