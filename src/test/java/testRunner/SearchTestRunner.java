package testRunner;

import config.ItemDataSet;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SearchTestRunner extends Setup {
    SearchPage searchPage;
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/userinfo.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email= (String) userObj.get("email");
        String password= (String) userObj.get("password");

        loginPage.doLogin(email,password);
    }
    @Test
    public void searchItem() throws InterruptedException {
        searchPage=new SearchPage(driver);
        searchPage.doSearch("item01");
        List<WebElement> tdElements=driver.findElements(By.tagName("td"));

        String actualAmount=tdElements.get(2).getText();
        WebElement amountText= driver.findElement(By.xpath("//span[contains(text(),'Total Cost')]"));
        String expectedAmount=amountText.getText();
//        Thread.sleep(3000);
        Assert.assertTrue(expectedAmount.contains(actualAmount));
        System.out.println(actualAmount);
        System.out.println(expectedAmount);
    }
}
