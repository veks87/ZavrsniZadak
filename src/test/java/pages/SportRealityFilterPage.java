package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportRealityFilterPage extends BaseHelper {
    @FindBy(id = "nb_f-kategorije")
    WebElement listOfFilters;
    @FindBy(id = "nb_f-attr-3")
    WebElement listGander;
    @FindBy(className = "filter-price-wrapper")
    WebElement priceWrapper;
    @FindBy(className = "products-found")
    WebElement numberOfProducts;
    @FindBy(className = "col-sm-9")
    WebElement articleWrapper;

    public SportRealityFilterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private void clickOnFilter(String selectFilter) {
        wdWait.until(ExpectedConditions.visibilityOf(listOfFilters));
        String numberOfResults = numberOfProducts.getText();
        List<WebElement> filters = listOfFilters.findElements(By.tagName("a"));
        for (WebElement filter : filters) {
            String title = filter.getAttribute("title");
            if (title.contains(selectFilter)) {
                filter.click();
                break;
            }

        }
            wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("products-found"),numberOfResults));
    }

    private void selectGander(String selectGander) throws InterruptedException {
        wdWait.until(ExpectedConditions.visibilityOf(listGander));
        String numberOfResults = numberOfProducts.getText();
        WebElement itemsWrapper = listGander.findElement(By.className("items-wrapper"));
        List<WebElement> ganders = itemsWrapper.findElements(By.tagName("li"));
        //Primjer koji radi
        for (WebElement gander: ganders){

            WebElement label = gander.findElement(By.tagName("label"));
            String orginalTitle = label.getText();
            String title = orginalTitle.replaceAll("[čć]", "c")
                    .replaceAll("[đ]", "d")
                    .replaceAll("[š]", "s")
                    .replaceAll("[ž]", "z").replaceAll("\\(\\d+\\)", "").toLowerCase().trim();
            System.out.println("Orginal title " + orginalTitle);
            System.out.println("Title " + title);


            if (title.contains(selectGander.replaceAll("[čć]", "c")
                    .replaceAll("[đ]", "d")
                    .replaceAll("[š]", "s")
                    .replaceAll("[ž]", "z").toLowerCase().trim())){
                gander.click();
                break;
            }
        }
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("products-found"),numberOfResults));

    }

    private void priceRange() throws InterruptedException {
        String cURL = (String) js.executeScript("return document.URL");
        System.out.println("Current URL is : " + cURL);

        List<WebElement> listPrice = priceWrapper.findElements(By.className("items-wrapper"));

        for (WebElement list : listPrice) {
            //    System.out.println("Size "+listPrice.size());
            String text = list.getText();
            System.out.println("Cijene " + text);
        }

    }

    private void selectArticle(int articleNumber) throws InterruptedException {
        WebElement productsList = articleWrapper.findElement(By.className("products-listing"));
        List<WebElement>listOfProducts = productsList.findElements((By.className("row")));
        listOfProducts.get(articleNumber-1).click();

    }


    public void sendFilters(String selectFilter, String selectGander,int articleNumber) throws InterruptedException {
        clickOnFilter(selectFilter);
        selectGander(selectGander);
       // priceRange();
        selectArticle(articleNumber);
    }

}
