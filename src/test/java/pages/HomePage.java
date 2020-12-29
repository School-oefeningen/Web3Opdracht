package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(id="userId")
    private WebElement userIdField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="login")
    private WebElement loginButton;

    @FindBy(id="logout")
    private WebElement logoutButton;

    public HomePage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Home");
    }

    public void setUserId(String userId) {
        userIdField.clear();
        userIdField.sendKeys(userId);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitForm() {
        loginButton.click();
    }

    public boolean isLoggedIn(String userId) {
        return driver.findElement(By.xpath("/html/body/div/div/main/p")).getText().equals("Welcome, " + userId + "!");
    }

    public boolean hasErrorMessage(String errorMessage) {
        return driver.findElement(By.xpath("/html/body/div/div/main/div/ul/li")).getText().equals(errorMessage);
    }

    public void loginAsUser() {
        setUserId("test");
        setPassword("test");
        submitForm();
    }

    public void loginAsAdmin() {
        setUserId("admin");
        setPassword("admin");
        submitForm();
    }

    public void logout() {
        logoutButton.click();
    }

    public boolean loginButtonIsPresent(){
        try {
            driver.findElement(By.id("login"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean logoutButtonIsPresent(){
        try {
            driver.findElement(By.id("logout"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}