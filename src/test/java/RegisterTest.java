import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class RegisterTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\loren\\Google Drive\\Hoge school (UCLL)\\Server stuff\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path + "?command=Register");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
        String userIdRandom = generateRandomuserIdInOrderToRunTestMoreThanOnce("jakke");
        submitForm(userIdRandom, "Jan", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Overview", title);
        assertEquals(userIdRandom, driver.findElement(By.id(userIdRandom)).getText());
    }

    @Test
    public void test_Register_userIdNotFilledIn_ErrorMessageGivenForuserIdAndOtherFieldsValueKept() {
        submitForm("", "Jan", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No userid given", errorMsg.getText());

        WebElement fielduserId = driver.findElement(By.id("userId"));
        assertEquals("", fielduserId.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("Jan", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("Janssens", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept() {
        submitForm("jakke", "", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No firstname given", errorMsg.getText());

        WebElement fielduserId = driver.findElement(By.id("userId"));
        assertEquals("jakke", fielduserId.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("Janssens", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept() {
        submitForm("jakke", "Jan", "", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No last name given", errorMsg.getText());

        WebElement fielduserId = driver.findElement(By.id("userId"));
        assertEquals("jakke", fielduserId.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("Jan", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept() {
        submitForm("jakke", "Jan", "Janssens", "", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No email given", errorMsg.getText());

        WebElement fielduserId = driver.findElement(By.id("userId"));
        assertEquals("jakke", fielduserId.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("Jan", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("Janssens", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("", fieldEmail.getAttribute("value"));
    }


    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept() {
        submitForm("jakke", "Jan", "Janssens", "jan.janssens@hotmail.com", "");

        String title = driver.getTitle();
        assertEquals("Sign Up", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No password given", errorMsg.getText());

        WebElement fielduserId = driver.findElement(By.id("userId"));
        assertEquals("jakke", fielduserId.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("Jan", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("Janssens", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven() {
        String userIdRandom = generateRandomuserIdInOrderToRunTestMoreThanOnce("pierke");
        submitForm(userIdRandom, "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        driver.get(path + "?command=Register");

        submitForm(userIdRandom, "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("User already exists", errorMsg.getText());

        WebElement fielduserId = driver.findElement(By.id("userId"));
        assertEquals(userIdRandom, fielduserId.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("Pieter", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("Pieters", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("pieter.pieters@hotmail.com", fieldEmail.getAttribute("value"));
    }

    private String generateRandomuserIdInOrderToRunTestMoreThanOnce(String component) {
        int random = (int) (Math.random() * 1000 + 1);
        return random + component;
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String userId, String firstName, String lastName, String email, String password) {
        fillOutField("userId", userId);
        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        fillOutField("email", email);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("signUp"));
        button.click();
    }

}
