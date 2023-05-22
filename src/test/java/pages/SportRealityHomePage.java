package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportRealityHomePage extends BaseHelper {

    @FindBy(className = "fa-search")
    WebElement searchButton;

    @FindBy(id = "search-text")
    WebElement searchField;

    public SportRealityHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Navigate to page
    private void navigateToPage(String url){
        driver.get(url);
    }
    private void acceptCookiesIfPresent()
    {
        List<WebElement> acceptCookiesButton = driver.findElements(By.className("cookie-agree-gdpr"));
        if (acceptCookiesButton.size() != 0) {
            acceptCookiesButton.get(0).click();
            wdWait.until(ExpectedConditions.invisibilityOf(acceptCookiesButton.get(0)));
        }
    }

    private void closeCountrySelectionModalIfPresent()
    {
        List<WebElement> closeModalButton = driver.findElements(By.className("close"));
        if (closeModalButton.size() != 0) {
            closeModalButton.get(0).click();
            wdWait.until(ExpectedConditions.invisibilityOf(closeModalButton.get(0)));
        }
    }

    private void clickOnSearchButton(){
        searchButton.click();
    }

    private void enterSearchTerm(String searchTerm){
        searchField.sendKeys(searchTerm);

    }

    private void clickKeyboardEnter(){
        searchField.sendKeys(Keys.ENTER);
    }

    public void searchFunctionality(String url,String searchTerm){
        navigateToPage(url);
        acceptCookiesIfPresent();
        clickOnSearchButton();
        enterSearchTerm(searchTerm);
        clickKeyboardEnter();
    }


}
