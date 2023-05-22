package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportRealityCategoryPage extends BaseHelper {
    public SportRealityCategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (id = "nb_f-kategorije")
    WebElement listOfProducts;



    private void clickOnProduct(String selectProduct){
      //  wdWait.until(ExpectedConditions.visibilityOf(listOfProducts));
        List<WebElement> products = listOfProducts.findElements(By.tagName("a"));
        for(WebElement product : products){
            String title = product.getAttribute("title");
            System.out.println(title);
            if(title.contains(selectProduct)){
                product.click();
                break;
            }
        }
    }


    public void sendFilter(String selectProduct){
        clickOnProduct(selectProduct);
    }

}
