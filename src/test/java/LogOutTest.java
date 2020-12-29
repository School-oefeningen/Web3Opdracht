import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Lennert Van Oosterwyck
public class LogOutTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";

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
    public void test_logout_works_when_you_are_logged_in(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        // kijk of je de logout button kan zien
        assertTrue(homePage.logoutButtonIsPresent());
        // uitloggen
        homePage.logout();
        // kijk of je bent uitgelogd en ook dus terug kan inloggen
        assertTrue(homePage.loginButtonIsPresent());

    }

    @Test
    public void test_can_not_logout_when_not_logged_in() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        boolean gelukt = false;
        // kijken of dat login button zichtbaar is
        assertTrue(homePage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertFalse(homePage.logoutButtonIsPresent());
    }
}
