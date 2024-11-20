package testRunner;

import config.ItemDataSet;
import config.Setup;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddCostPage;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;

public class AddCostTestRunner extends Setup {
    AddCostPage addCostPage;
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
    @Test(dataProvider="ItemCSVData", dataProviderClass = ItemDataSet.class)
    public void addCost(String itemName, String quantity, String amounts, String purchaseDates, String months, String remarks) throws InterruptedException {
        addCostPage=new AddCostPage(driver);
            addCostPage.btnAddCost.click();

            addCostPage.doAddCost(itemName,quantity,amounts,purchaseDates,months,remarks);
            Thread.sleep(2000);
            driver.switchTo().alert().accept();

    }
    @AfterTest
    public void doAssertion() throws InterruptedException {
        Thread.sleep(3000);
        String actualTotalCost=driver.findElement(By.xpath("//span[contains(text(),'Total Cost')]")).getText();
        String expectedTotalCost="1950";
        Assert.assertTrue(actualTotalCost.contains(expectedTotalCost));
        System.out.println(actualTotalCost);
    }
}
