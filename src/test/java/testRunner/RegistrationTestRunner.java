package testRunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    RegistrationPage userReg;
    @Test(priority = 1, description = "Registration with all fields")
    public void userRegistrationWithAllFields() throws InterruptedException, IOException, ParseException {
        userReg = new RegistrationPage(driver);
        userReg.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName+"@gmail.com";
        String password = "123456789";
        String phoneNumber = "0171" +Utils.generateRandomId(1000000,9999999);
        String address = faker.address().fullAddress();
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setPassword(password);
        userModel.setEmail(email);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        userReg.doRegistration(userModel);


        doAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName",firstName);
        userObj.put("lastName",lastName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);
        userObj.put("address", address);
        Utils.saveUserInfo("./src/test/resources/userinfo.json",userObj);

        Thread.sleep(2000);

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
    }

    @Test(priority = 2, description = "Registration with mandatory fields")
    public void userRegistrationWithMandatoryFields() throws InterruptedException, IOException, ParseException {
        driver.get("https://dailyfinance.roadtocareer.net/login");
        userReg = new RegistrationPage(driver);

        userReg.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String email = firstName+"@gmail.com";
        String password = "123456789";
        String phoneNumber = "0171" +Utils.generateRandomId(1000000,9999999);

        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setPassword(password);
        userModel.setEmail(email);
        userModel.setPhoneNumber(phoneNumber);
        userReg.doRegistration(userModel);


        doAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName",firstName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);
        Utils.saveUserInfo("./src/test/resources/userinfo.json",userObj);
    }

    @Test(priority = 3, description = "Registration without any mandatory fields")
    public void userRegistrationWithoutAnyMandatoryField(){
        driver.get("https://dailyfinance.roadtocareer.net/login");
        userReg = new RegistrationPage(driver);

        userReg.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String password = "123456789";
        String phoneNumber = "0171" +Utils.generateRandomId(1000000,9999999);

        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userReg.doRegistration(userModel);

        WebElement mandatoryEmailField = driver.findElement(By.id("email"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualRequiredMessage = (String) js.executeScript("return arguments[0].validationMessage;", mandatoryEmailField);
        String expectedRequiredMessage = "fill out";
        Assert.assertTrue(actualRequiredMessage.contains(expectedRequiredMessage));
//        System.out.println(actualRequiredMessage);
    }


    public void doAssertion() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Toastify__toast")));
//        String actualSuccessMessage=driver.findElement(By.className("Toastify__toast")).getText();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement toastElement = (WebElement) js.executeScript("return document.querySelector('.Toastify__toast')");
        String actualSuccessMessage = toastElement.getText();
        String expectedSuccessMessage = "registered successfully!";
        System.out.println(actualSuccessMessage);
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage));
    }
}
