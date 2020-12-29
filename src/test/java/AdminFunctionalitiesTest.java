import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class AdminFunctionalitiesTest {
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
    public void adminCanDeleteAnyUser() {
        assertEquals("Home", driver.getTitle());
        loginAsAdmin();
        driver.get(path + "?command=UsersOverview");

        assertTrue(retrieveDeleteButtonsFromUsersOverview());
    }

    @Test
    public void userCannotDeleteAnyUser() {
        assertEquals("Home", driver.getTitle());
        loginAsUser();
        driver.get(path + "?command=UsersOverview");

        assertFalse(retrieveDeleteButtonsFromUsersOverview());
    }

    @Test
    public void adminSeesAllContacts() {
        assertEquals("Home", driver.getTitle());
        loginAsAdmin();
        driver.get(path + "?command=ContactsOverview");

        assertTrue(isThereContactsFromDifferentUsers());
    }

    @Test
    public void usersDoesNotSeeAllContact() {
        assertEquals("Home", driver.getTitle());
        loginAsUser();
        driver.get(path + "?command=ContactsOverview");

        assertFalse(isThereContactsFromDifferentUsers());
    }

    @Test
    public void userNotLoggedInDoesNotSeeContactsGivesErrorMessage() {
        assertEquals("Home", driver.getTitle());
        driver.get(path + "?command=ContactsOverview");
        assertEquals("Please log in to see this content.", driver.findElement(By.id("alert-danger")).getText());
    }

    private boolean isThereContactsFromDifferentUsers() {
        List<WebElement> contactsUserIds = driver.findElements(By.cssSelector("table tr td#userId"));
        Set<String> userIds = new HashSet<>();

        for (WebElement contactUserId: contactsUserIds) {
            userIds.add(contactUserId.getText());
        }

        System.out.println(userIds.size());
        System.out.println(userIds.size() != 1);
        return userIds.size() > 1;
    }

    private boolean retrieveDeleteButtonsFromUsersOverview() {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr td input"));

        for (WebElement listItem : listItems) {
            if (listItem.getAttribute("id").equals("removeButton")) return true;
        }
        return false;
    }

    private void loginAsUser() { submitForm(userId, userPassword); }

    private void loginAsAdmin() {
        submitForm(adminId, adminPassword);
    }

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