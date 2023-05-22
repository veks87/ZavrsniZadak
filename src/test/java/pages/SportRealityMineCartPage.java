package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SportRealityMineCartPage extends BaseHelper {

    WebDriver driver;

    public SportRealityMineCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "order_cart_content")
    WebElement chartTitle;
  @FindBy(id = "quantity_1")
  WebElement quantityContainer;

    @FindBy(css = "span[class='product-item-prices']")
    WebElement numberOfProducts;

    private void selectQuantity(String selectQuantityP){
        String numberOfResults = numberOfProducts.getText();
       // wdWait.until(ExpectedConditions.visibilityOf(chartTitle));
    //    WebElement selectComponent = driver.findElement(By.id("quantity_1"));
      //  Select selectQuantity = new Select(selectComponent);
        Select selectQuantity = new Select(quantityContainer);
        selectQuantity.selectByValue(selectQuantityP);
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("span[class='product-item-prices']"),numberOfResults));

    }


    public void sendSelectQuantity(String selectQuantityP){
        selectQuantity(selectQuantityP);
    }


}
