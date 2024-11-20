package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Try to login with invalid credentials")
    public void adminLoginWithInvalidCredentials(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("sdfsdfwer@gmail.com","5678");
        String actualErrorLoginMessage=driver.findElement(By.tagName("p")).getText();
        String expectedErrorLoginMessage="Invalid";
        Assert.assertTrue(actualErrorLoginMessage.contains(expectedErrorLoginMessage));

        clearCredentials();
    }

    @Test(priority = 2, description = "try to login with valid credentials")
    public void adminLoginWithValidCredentials() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
//        loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        loginPage.doLogin("admin@test.com","admin123");
        String actualMessageAfterLogin=driver.findElement(By.tagName("h2")).getText();
        String expectedMessageAfterLogin="Admin";
        Assert.assertTrue(actualMessageAfterLogin.contains(expectedMessageAfterLogin));

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/userinfo.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String expectedFirstName= (String) userObj.get("firstName");
        String expectedEmail= (String) userObj.get("email");
        String expectedPhoneNumber= (String) userObj.get("phoneNumber");
        List<WebElement> trElemeents= driver.findElements(By.tagName("tr"));
        String trText=trElemeents.get(1).getText();
        System.out.println(trText);
        Assert.assertTrue(trText.contains(expectedFirstName));
        Assert.assertTrue(trText.contains(expectedEmail));
        Assert.assertTrue(trText.contains(expectedPhoneNumber));


        loginPage.doLogOut();
    }
    @Test(priority = 3, description = "login with user credentials")
    public void userLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/userinfo.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email= (String) userObj.get("email");
        String password= (String) userObj.get("password");

        loginPage.doLogin(email,password);
        Assert.assertTrue(loginPage.btnProfileIcon.isDisplayed());

        loginPage.doLogOut();
    }

    public void clearCredentials(){
        loginPage=new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
        loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);

        loginPage.txtPassoword.sendKeys(Keys.CONTROL,"a");
        loginPage.txtPassoword.sendKeys(Keys.BACK_SPACE);


    }
}
