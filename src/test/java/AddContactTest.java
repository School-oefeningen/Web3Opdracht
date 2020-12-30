import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.AddContactPage;
import pages.ContactOverviewPage;
import pages.HomePage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddContactTest {
    /**
     * @Author Elias Beddegenoodts
     */

    private WebDriver driver;
    private String path = "http://localhost:8080/Project_war_exploded/Controller";

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
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_AddContact_AllFieldsFilledInCorrectly_ContactIsAdded() {
        //Login as admin
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        //Create a contact
        int randomId = (int) (Math.random()*100);
        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName(randomId+"Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        ContactOverviewPage overview = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertTrue(overview.containsName(randomId + "Jan"));
    }

    @Test
    public void test_AddContact_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Add contact", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No first name given"));
        assertTrue(addContactPage.hasEmptyFirstName());
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Add contact", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No last name given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasEmptyLastName());
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_DateNotFilledIn_ErrorMessageGivenForDateAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDateEmpty();
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Add contact", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No valid date given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_HourNotFilledIn_ErrorMessageGivenForHourAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHourEmpty();
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Add contact", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No valid hour given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_GSMNotFilledIn_ErrorMessageGivenForGSMAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Add contact", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No phone number given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasEmptyGsm());
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsUser();

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("");
        addContactPage.pressButton();

        assertEquals("Add contact", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No email given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasEmptyEmail());
    }
}