package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactOverviewPage extends Page {
    /**
     * @Author Elias Beddegenoodts
     */

    public ContactOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=ContactsOverview");
    }

    public boolean containsName(String fullName) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found = false;
        for (WebElement listItem: listItems) {
            System.out.println(listItem.getText());
            if (listItem.getText().contains(fullName)) {
                found = true;
            }
        }
        return found;
    }

    public int countContacts() {
        List<WebElement> contacts = driver.findElements(By.xpath("/html/body/div/div/main/table/tbody/tr"));
        return contacts.size();
    }
}