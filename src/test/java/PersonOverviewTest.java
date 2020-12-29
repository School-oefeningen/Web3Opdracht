import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.PersonOverviewPage;

import static org.junit.Assert.assertEquals;

/**
 * @Author Lorenzo Catalano
 */

public class PersonOverviewTest {

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
    public void userNotLoggedInNavigatesToPersonOverview() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Users overview", driver.getTitle());
    }

    @Test
    public void userLoggedInNavigatesToPersonOverview() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Users overview", driver.getTitle());
    }

    @Test
    public void adminLoggedInNavigatesToPersonOverview() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsAdmin();
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Users overview", driver.getTitle());
    }
}
