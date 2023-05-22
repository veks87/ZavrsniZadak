package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportRealityFilterTPage extends BaseHelper {

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

    public SportRealityFilterTPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private void clickOnFilter(String selectFilter) {
        List<WebElement> filters = listOfFilters.findElements(By.tagName("a"));
        for (WebElement filter : filters) {
            String title = filter.getAttribute("title");
            if (title.contains(selectFilter)) {
                filter.click();
                break;
            }

          /*  wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("listing-products")));
           List<WebElement> listOfProducts = driver.findElements(By.className("listing-products"));
            for (WebElement list : listOfProducts){
                WebElement list1 = list.findElement(By.className("col-sm-9"));
               // WebElement list2 = list.findElement(By.className("product-listing-items"));
                WebElement list2 = list.findElement(By.className("row"));

                System.out.println("List  test "+list2.getText());
            }*/

        }

        // finding title of products
       wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("listing-products")));
        List<WebElement> listOfProducts = driver.findElements(By.className("listing-products"));
        for (WebElement list : listOfProducts){
            WebElement list1 = list.findElement(By.className("col-sm-9"));
             WebElement list2 = list.findElement(By.className("product-listing-items"));
       /*     WebElement list3 = list2.findElement(By.className("row"));
            WebElement list4 = list3.findElement(By.className("title"));
            System.out.println("Test " + list4.getText());*/


         /*   List<WebElement> list3 = list2.findElements(By.className("row"));
             int brojac = 0;
             for (WebElement lists : list3) {
                 WebElement list4 = lists.findElement(By.className("title"));
                 brojac++;
                // String title = list4.getAttribute("data-productname");
                 //System.out.println("List  test " + title);
                 System.out.println(brojac);
                 System.out.println("Title" + list4.getText());
             }*/
        }
    }

    private void selectGander(String selectGander) throws InterruptedException {
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
                wdWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("#nb_f-attr-3>ul>li>div>input[checked=\"checked\"]")));
                break;
            }
        }


        //drugi primjer
/*        for (WebElement ganderSelect : ganders) {

            //String listGandersTitle = ganderSelect.getText();
            //System.out.println(listGandersTitle);
            // WebElement che = ganderSelect.findElement(By.xpath("//*[@id=\"nb_f-attr-3\"]/ul/li[2]/label"));

                WebElement div = ganderSelect.findElement(By.tagName("div"));
                WebElement input = div.findElement(By.tagName("input"));
                String titleBox = input.getAttribute("value").replace("-", " ");
                System.out.println("Title box " +titleBox);
                if (titleBox.contains(selectGander)) {
                    ganderSelect.click();
                    break;
                }

        }*/

    }

/*    private void priceRange() throws InterruptedException {
        String cURL = (String) js.executeScript("return document.URL");
        System.out.println("Current URL is : " + cURL);

        List<WebElement> listPrice = priceWrapper.findElements(By.className("items-wrapper"));

        for (WebElement list : listPrice) {
            //    System.out.println("Size "+listPrice.size());
            String text = list.getText();
            System.out.println("Cijene " + text);
        }

    }*/

    private void selectArticle(int articleNumber) throws InterruptedException {
        WebElement productsList = articleWrapper.findElement(By.className("products-listing"));
        List<WebElement>listOfProducts = productsList.findElements((By.className("row")));
        listOfProducts.get(articleNumber).click();
      /*  for (WebElement productList : listOfProducts{
            WebElement row = productList.findElement(By.className("row"));
           // WebElement listOfProduct = row.findElement(By.className("product-item"));
            //System.out.println(row.getText());
            productsList.getSize(articleNumber-1).click();
        }*/
    }


    public void sendFilters(String selectFilter, String selectGander,int articleNumber) throws InterruptedException {
        clickOnFilter(selectFilter);
        selectGander(selectGander);
        //priceRange();
        selectArticle(articleNumber);
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("code")));
        WebElement code = driver.findElement(By.className("code"));
        System.out.println("Assert1 " + code.getText());


    }

}

