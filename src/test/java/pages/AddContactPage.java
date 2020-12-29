package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends Page {
    /**
     * @Author Elias Beddegenoodts
     */

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="date")
    private WebElement dateField;

    @FindBy(id="hour")
    private WebElement hourField;

    @FindBy(id="phoneNumber")
    private WebElement gsmField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="addContact")
    private WebElement signUpButton;

    public AddContactPage (WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=AddContactForm");
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void setDateEmpty() {
        dateField.sendKeys(Keys.DELETE, Keys.TAB, Keys.DELETE, Keys.TAB, Keys.DELETE);
    }

    public void setHour(String hour) {
        hourField.clear();
        hourField.sendKeys(hour);
    }

    public void setHourEmpty() {
        hourField.sendKeys(Keys.DELETE, Keys.TAB, Keys.DELETE, Keys.TAB, Keys.DELETE);
    }

    public void setGsm(String gsm) {
        gsmField.clear();
        gsmField.sendKeys(gsm);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void pressButton() {
        signUpButton.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("#alert-danger > ul > li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyFirstName (String firstname) {
        return firstname.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName (String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyDate (String date) {
        System.out.println(dateField.getAttribute("value"));
        return date.equals(dateField.getAttribute("value"));
    }

    public boolean hasStickyHour (String hour) {
        return hour.equals(hourField.getAttribute("value"));
    }

    public boolean hasStickyGsm (String gsm) {
        return gsm.equals(gsmField.getAttribute("value"));
    }

    public boolean hasStickyEmail (String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName () {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName () {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyDate () {
        return dateField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyHour () {
        return hourField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyGsm () {
        return gsmField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail () {
        return emailField.getAttribute("value").isEmpty();
    }



}