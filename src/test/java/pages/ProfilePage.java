package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {
    @FindBy(css = "[data-testid='AccountCircleIcon']")
    public WebElement btnProfileIcon;

    @FindBy (css = "[role='menuitem']")
    public List<WebElement> btnProfileMenuItems;

    @FindBy (css = "[type='button']")
    public List<WebElement> editProfilebtn;

    @FindBy (className = "upload-input")
    public WebElement chooseFile;

    @FindBy (css = "[type='button']")
    List<WebElement> btnUploadImage;

    public ProfilePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void updateProfile(){
        btnProfileIcon.click();
        btnProfileMenuItems.get(0).click();
        editProfilebtn.get(1).click();
        String relativePath="\\src\\test\\resources\\pumpkin.jpg";
        String absolutePath=System.getProperty("user.dir")+relativePath;
        chooseFile.sendKeys(absolutePath);
        btnUploadImage.get(1).click();
    }
}
