package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddCostPage {
    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;

    @FindBy(id = "itemName")
    WebElement txtItemName;

    @FindBy(css = "[type='button']")
    List<WebElement> btnQuantity;

    @FindBy(id = "amount")
    WebElement txtAmount;

    @FindBy(id = "purchaseDate")
    WebElement purchasedDate;

    @FindBy(id = "month")
    WebElement dropDownMonth;

    @FindBy(id = "remarks")
    WebElement txtRemarks;

    @FindBy(css = "[type='submit']")
    WebElement btnSubmit;

    public AddCostPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doAddCost(String itemName, String quantity, String amount, String purchaseDate, String months, String remarks){
        txtItemName.sendKeys(itemName);
        txtAmount.sendKeys(amount);
        int count = Integer.parseInt(quantity);
        for(int i = 1; i<count; i++){
            btnQuantity.get(2).click();
        }
        purchasedDate.sendKeys(Keys.CONTROL,"a");
        purchasedDate.sendKeys(Keys.BACK_SPACE);
        purchasedDate.sendKeys(purchaseDate);
        dropDownMonth.click();
        Select selectMonth = new Select(dropDownMonth);
        selectMonth.selectByVisibleText(months);
//        System.out.println(months);
        txtRemarks.sendKeys(remarks);
        btnSubmit.click();

    }
}
