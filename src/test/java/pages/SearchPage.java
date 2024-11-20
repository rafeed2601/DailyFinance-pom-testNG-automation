package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    @FindBy (className = "search-input")
    WebElement searchBox;

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doSearch(String itemName){
        searchBox.click();
        searchBox.sendKeys(itemName);

    }
}
