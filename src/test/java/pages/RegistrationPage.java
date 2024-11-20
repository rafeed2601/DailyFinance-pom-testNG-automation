package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {
    @FindBy (tagName = "a")
    public List<WebElement> btnRegister;
    @FindBy (id = "firstName")
    WebElement txtFirstName;
    @FindBy (id = "lastName")
    WebElement txtLastName;
    @FindBy (id = "email")
    WebElement txtEmail;
    @FindBy (id = "password")
    WebElement txtPassword;
    @FindBy (id = "phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy (id = "address")
    WebElement txtAddress;
    @FindBy (css = "[type='radio']")
    List<WebElement> rbGender;
    @FindBy (css = "[type='checkbox']")
    WebElement chkTerms;
    @FindBy (id = "register")
    WebElement btnSubmit;

    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doRegistration(UserModel userModel){
        txtFirstName.sendKeys(userModel.getFirstName()!=null?userModel.getFirstName():"");
        txtLastName.sendKeys(userModel.getLastName()!=null?userModel.getLastName():"");
        txtEmail.sendKeys(userModel.getEmail()!=null?userModel.getEmail():"");
        txtPassword.sendKeys(userModel.getPassword()!=null?userModel.getPassword():"");
        txtPhoneNumber.sendKeys(userModel.getPhoneNumber()!=null?userModel.getPhoneNumber():"");
        txtAddress.sendKeys(userModel.getAddress()!=null?userModel.getAddress():"");
        rbGender.get(0).click();
        chkTerms.click();
        btnSubmit.click();
    }
}
