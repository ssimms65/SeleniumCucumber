package unusedstepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.lexer.Th;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class StepDefinitions {

    private WebDriver driver;
    WebElement webElement;


    @Given("^I start the browser$")
    public void iStartTheBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.google.com/");

        System.out.println("I start the browser");
    }

    @Then("^I close the browser$")
    public void iCloseTheBrowser() {
        driver.close();

        System.out.println("I close the browser");
    }

    @Given("I go to url$")
    public void iGoToUrl() {
        driver.get("https://www.neueda.com/");

        System.out.println("I go to url");
    }

    @Then("^I? ?I select about us$")
    public void iSelectAboutUs() {
        driver.findElement(By.linkText("ABOUT US")).click();

        System.out.println("I select about us");
    }

    @Then("^I? ?I select careers$")
    public void iSelectCareers() {
        WebElement element = driver.findElement(By.xpath("/html/body/div[4]/header/div/div[4]/div/nav/div[3]/div[1]"));
        element.click();

        System.out.println("I select careers");
    }


    @Then("^I? ?I select password by xpath ([^\"]*)")
    public void iSelectPasswordByXpath(String password) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);

        System.out.println("I select password");
    }


    @Given("^I? ?I go to this url \"([^\"]*)\"$")
    public void iGoToThisUrl(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        System.out.println("I go to this url");
    }

    @Then("^I? ?I click on element \"([^\"]*)\"$")
    public void iClickOnElement(String text) {
        this.webElement = driver.findElement(By.linkText(text));
        webElement.click();

        System.out.println("I click on element");
    }

    @Then("^I? ?I select element \"([^\"]*)\"$")
    public void iSelectElement(String text) {
        this.webElement = driver.findElement(By.linkText(text));

        System.out.println("I select element");
    }

    @Then("^I check attribute \"([^\"]*)\" exists")
    public void iCheckAttributeExists(String attribute){
        String expectedAttribute = webElement.getAttribute(attribute);
        //assertTrue(expectedAttribute != null);
        assertNotNull(expectedAttribute);

        //System.out.println("I check attribute exists");
    }

//    @And("^I enter ([^\"]*) and ([^\"]*)$")
//    public void iEnterUsernameAndPassword(String user, String password) throws Throwable {
////        String expectedUser = webElement.getText(user);
//
//        System.out.println("UserName is : " + user);
//        System.out.println("password is : " + password);
//    }

    @And("^I enter user ([^\"]*)")
    public void iEnterUsername(String user) {
        driver.findElement(By.id("user")).sendKeys(user);

        System.out.println("UserName is : " + user);
    }

    @And("^I enter password ([^\"]*)")
    public void iEnterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);

        System.out.println("Password is : " + password);
    }
}


//StringContains contains = webElement.getAttribute(webElement.isDisplayed(attribute));


    /*@Then("I match an attribute \"([^\"]*)\"$")
    public void iMatchAnAttribute(String text) throws Throwable {
        AttributedCharacterIterator attributedCharacterIterator = webElement.getAttribute(By.ByLinkText(text));
        text.matches();
        this.
    }*/






