import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//Thao De Clercq - Mirte Theunis

public class LogInTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/pageobjectpairprogramming_Thaodeclercq_war_exploded/Controller"; //VERANDER

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lorenzo\\Google Drive\\Hoge school (UCLL)\\Server stuff\\chromedriver87.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_LogIn_AllFieldsFilledInCorrectly_UserIsLoggedIn() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserId("test"); //VERANDER
        homePage.setPassword("test"); //VERANDER
        homePage.submitForm();

        assertEquals("Home", homePage.getTitle()); //VERANDER
        assertTrue(homePage.isLoggedIn("test")); //VERANDER
    }

    @Test
    public void testUserIdCorrectPasswordWrongGivesErrorMessage() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserId("test"); //VERANDER
        homePage.setPassword("wrongpassword"); //VERANDER
        homePage.submitForm();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("Wrong password")); // VERANDER
    }

    @Test
    public void testUserIncorrectGivesErrorMessage() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserId("useridnotindatabase"); //VERANDER
        homePage.setPassword("test"); //VERANDER
        homePage.submitForm();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("Person not in database")); // VERANDER
    }
}
