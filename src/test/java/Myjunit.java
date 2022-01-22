import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Myjunit {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headed");
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void getTitle() {
        driver.get("https://demoqa.com");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("ToolsQA"));
    }

    @Test
    public void checkIfElementExits() {
        driver.get("https://demoqa.com");
        //boolean status=driver.findElement(By.className("banner-image")).isDisplayed();
        //WebElement imageCheck = driver.findElement(By.className("banner-image"));
        // boolean status = imageCheck.isDisplayed();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement image = wait.until(ExpectedConditions.elementToBeClickable(By.className("banner-image")));
        boolean status = image.isDisplayed();
        Assert.assertTrue(status);
    }

    @Test
    public void fillUpForm() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.cssSelector("[placeholder='Full Name']")).sendKeys("sudipto");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("sudipto1294@gmail.com");
        driver.findElement(By.cssSelector("[id='permanentAddress']")).sendKeys("Bogura");
        driver.findElement(By.cssSelector("#currentAddress")).sendKeys("Dhaka");
        // driver.findElement(By.cssSelector("#submit")).click();

    }

    @Test
    public void clickButton() {
        driver.get("https://demoqa.com/buttons");
        WebElement doubleClickBtnElement = driver.findElement(By.cssSelector("#doubleClickBtn"));
        WebElement rightClickBtnElement = driver.findElement(By.cssSelector("#rightClickBtn"));


        Actions action = new Actions(driver);
        action.doubleClick(doubleClickBtnElement).perform();
        action.contextClick(rightClickBtnElement).perform();

        String message1 = driver.findElement(By.id("doubleClickMessage")).getText();
        String message2 = driver.findElement(By.id("rightClickMessage")).getText();

        Assert.assertTrue(message1.contains("You have done a double click"));
        Assert.assertTrue(message2.contains("You have done a right click"));


    }

    @Test
    public void clickIfMultipleButton() {
        driver.get("https://demoqa.com/buttons");
        List<WebElement> clickButton = driver.findElements(By.cssSelector("[type=button]"));
        Actions actions = new Actions(driver);

        actions.doubleClick(clickButton.get(1)).perform();
        actions.contextClick(clickButton.get(2)).perform();
        actions.click(clickButton.get(3)).perform();

    }

    @Test
    public void handleAlerts() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();
        driver.findElement(By.id("promtButton")).click();
        driver.switchTo().alert().sendKeys("sudipto");

        driver.switchTo().alert().accept();
        String message = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(message.contains("You entered sudipto"));

    }
    @Test
    public void selectDate(){
        driver.get("https://demoqa.com/date-picker");
        driver.findElement(By.id("datePickerMonthYearInput")).clear();
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("02/18/2022");
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);

    }
    @Test
    public void selectDropDown(){
        driver.get("https://demoqa.com/select-menu");
        Select select=new Select(driver.findElement(By.id("oldSelectMenu")));
       select.selectByValue("5");
        //Select select2=new Select(driver.findElement(By.xpath("//b[contains(text(),'Standard multi select')]")));
        //if(select2.isMultiple()){
          //  select2.selectByValue("audi");
            //select2.selectByValue("saab");
        //}

    }
    @After
    public void closeBrowser(){
        driver.quit();
    }

}
