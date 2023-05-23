package tests;

import org.junit.Test;
import pages.SportRealityCategoryPage;
import pages.SportRealityFilterPage;
import pages.SportRealityFilterPriceRangePage;
import pages.SportRealityHomePage;

public class SportRealityPriceRangeTest extends BaseTest{


    @Test
    public void priceRangeTest() throws InterruptedException {
        String url = "https://www.sportreality.ba/";
        String searchTerm = "Adidas";
        String selectProduct = "Obuća";
        String selectFilter = "Patike";
        String selectGander = "za muškarce";
        String selectPriceRange = "100";


        SportRealityHomePage homePage = new SportRealityHomePage(driver);
        homePage.searchFunctionality(url, searchTerm);

        SportRealityCategoryPage categoryPage = new SportRealityCategoryPage(driver);
        categoryPage.sendFilter(selectProduct);

        SportRealityFilterPriceRangePage filterPage = new SportRealityFilterPriceRangePage(driver);
        filterPage.sendFilters(selectFilter,selectGander,selectPriceRange);
    }
}
