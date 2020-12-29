import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.ContactOverviewPage;
import pages.HomePage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Evert
 */

public class ContactsOverviewTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lorenzo\\Google Drive\\Hoge school (UCLL)\\Server stuff\\chromedriver87.exe");

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }

    @After
    public void clean() { driver.quit(); }

    @Test
    public void test_Not_Logged_In_User_Navigates_From_Home_To_Contacts_Throws_Exception()  {
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals(driver.getTitle(),"Error");
    }

    @Test
    public void test_User_Without_Contacts_Shows_No_Contacts() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserId("nina");
        homePage.setPassword("nina");
        homePage.submitForm();

        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals(contactOverviewPage.countContacts(), 0);
    }

    @Test
    public void test_User_With_Contacts_Shows_Positive_Number_Of_Contacts()  {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertNotEquals(contactOverviewPage.countContacts(), 0);
    }
}
