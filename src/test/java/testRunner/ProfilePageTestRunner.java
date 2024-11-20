package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProfilePageTestRunner extends Setup {
    ProfilePage profilePage;

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
    public void editingProfileImage(){
        profilePage=new ProfilePage(driver);
        profilePage.updateProfile();

    }
}
