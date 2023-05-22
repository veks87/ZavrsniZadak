package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportRealityArticlePage extends BaseHelper {

    WebDriver driver;

    public SportRealityArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "code")
    WebElement NumberOfArticle;

    @FindBy(className = "product-attributes-wrapper")
    WebElement sizeContainer;

    @FindBy(id = "nb_addToCartButton")
    WebElement buttonContainer;

    @FindBy(className = "header-cart-toggle")
    WebElement cartContainer;
    @FindBy(className = "btn-success")
    WebElement cartButton;

    private void selectSize(String selectSize){
        wdWait.until(ExpectedConditions.visibilityOf(NumberOfArticle));
        WebElement sizes = sizeContainer.findElement(By.className("product-attributes"));
        List<WebElement> listOfSizes = sizes.findElements(By.tagName("li"));
        for (WebElement size : listOfSizes){
            String sizeString = size.findElement(By.className("eur-size")).getText();
            if(sizeString.contains(selectSize)){
                System.out.println("Size:" + sizeString);
                size.click();
                break;
            }
            //System.out.println("Size:" + sizeString);

        }
    }

    private void clickOnAddButton(){
        wdWait.until(ExpectedConditions.visibilityOf(buttonContainer));
        buttonContainer.click();
    }

    private void clickOnBagButton(){
        wdWait.until(ExpectedConditions.visibilityOf(cartContainer));
        wdWait.until(ExpectedConditions.visibilityOf(cartButton));
        cartButton.click();
    }

    public void selectSizeAndAddToChart(String size){
        selectSize(size);
        clickOnAddButton();
        clickOnBagButton();
    }
}
