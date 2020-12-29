import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminSearchTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";
    private String userId = "lorenzo";
    private String userPassword = "lorenzo";
    private String adminId = "admin";
    private String adminPassword = "admin";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lorenzo\\Google Drive\\Hoge school (UCLL)\\Server stuff\\chromedriver87.exe");
        driver = new ChromeDriver();
        driver.get(path + "?command=Home");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void userNotLoggedInNavigatesToAdminSearchThrowsError() {
        driver.get(path + "?command=AdminSearch");
        assertEquals("Error", driver.getTitle());
        assertEquals("Please log in to see this content.", driver.findElement(By.id("alert-danger")).getText());
    }

    @Test
    public void userLoggedInNotAdminNavigatesToAdminSearchThrowsError() {
        loginAsUser();
        driver.get(path + "?command=AdminSearch");
        assertEquals("Error", driver.getTitle());
        assertEquals("You are not authorized to see this content!", driver.findElement(By.id("alert-danger")).getText());
    }

    @Test
    public void userLoggedInIsAdminNavigatesToAdminSearchThrowsNoError() {
        loginAsAdmin();
        driver.get(path + "?command=AdminSearch");
        assertEquals("Admin search", driver.getTitle());
        assertTrue(driver.findElements(By.className("alert-danger")).isEmpty());
    }

    private void loginAsUser() { submitForm(userId, userPassword); }

    private void loginAsAdmin() { submitForm(adminId, adminPassword); }

    private void submitForm(String userId, String password) {
        fillOutField("userId", userId);
        fillOutField("password", password);

        driver.findElement(By.id("login")).click();
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }
}