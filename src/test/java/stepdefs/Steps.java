package stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Step definitions for performing basic selenium tasks
 * Most actions are performed on the <b>selectedElement</b> property.
 * Multiple WebElements can be found and stored in <b></b>selectedElements</b> and then moved into selectedElement
 * WebElements can also be named and referenced by storing them in <b>namedElements</b>
 * <p>
 * When selecting elements, if <b>selectedElement</b> is null then the whole dom hierarchy will be searched. If
 * it is not null then the select methods will be actioned relative to the currently selected element. If you
 * have selected an element and then afterwards want to search the whole dom then make sure to clear the selected
 * element (and giving it an alias to add into namedElements if you want to use it later)
 */


public class Steps {

    private WebDriver driver;

    private HashMap<String, WebElement> namedElements;
    private List<WebElement> selectedElements;
    private WebElement selectedElement;

    public Steps() {
        this.namedElements = new HashMap<>();
        this.selectedElements = new ArrayList<>();
    }

    @Given("(?:the)? ?\"([^\"]*)\" browser is open")
    public void openGivenWebBrowser(String browserName) throws Throwable {
        launchBrowser(browserName);
    }

    @Given("^implicit wait is set to (\\d+) nanoseconds$")
    public void setImplicitWaitNano(long nanos) {
        driver.manage().timeouts().implicitlyWait(nanos, TimeUnit.NANOSECONDS);
    }

    @Given("^implicit wait is set to (\\d+) milliseconds$")
    public void setImplicitWaitMs(long ms) {
        driver.manage().timeouts().implicitlyWait(ms, TimeUnit.MILLISECONDS);
    }

    @Given("^implicit wait is set to (\\d+) seconds$")
    public void setImplicitWaitSeconds(long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    @Given("^I? ?open (?:the)? ?browser$")
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    /**
     * Opens a url
     *
     * @param url should be a fully qualified url such as https://www.google.com rather than google.com
     */
    @Given("^I? ?(?:(?:go to)|(?:visit)) (?:the)? ?(?:website|url) \"([^\"]*)\"$")
    public void goToUrl(String url) {
        driver.get(url);
    }

    @Given("^(?:the)? ?window is maximized$")
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    //-------------------------//
    //    Selector methods     //
    //-------------------------//

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by css selector using value \"([^\"]*)\"$")
    public void selectElementByCss(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.cssSelector(selector)) :
                selectedElement.findElement(By.cssSelector(selector));
    }

    /**
     * Selected elements will be stored in selectedElements and any of these can be assigned to selectedElement
     * by calling selectFromElements(int index) step definition.
     * Selenium returns an empty list if no elements found, however NoSuchElementException is thrown to
     * remain consistent with the selectElementBy... methods.
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select elements by css selector using value \"([^\"]*)\"$")
    public void selectElementsByCss(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.cssSelector(selector)) :
                selectedElement.findElements(By.cssSelector(selector));
    }

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by xpath using value \"([^\"]*)\"$")
    public void selectElementByXpath(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.xpath(selector)) :
                selectedElement.findElement(By.xpath(selector));
    }

    /**
     * Selected elements will be stored in selectedElements and any of these can be assigned to selectedElement
     * by calling selectFromElements(int index) step definition.
     * Selenium returns an empty list if no elements found, however NoSuchElementException is thrown to
     * remain consistent with the selectElementBy... methods.
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select elements by xpath using value \"([^\"]*)\"$")
    public void selectElementsByXpath(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.xpath(selector)) :
                selectedElement.findElements(By.xpath(selector));
    }

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by id using value \"([^\"]*)\"$")
    public void selectElementById(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.id(selector)) :
                selectedElement.findElement(By.id(selector));
    }

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by tag using value \"([^\"]*)\"$")
    public void selectElementByTag(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.tagName(selector)) :
                selectedElement.findElement(By.tagName(selector));
    }

    /**
     * Selected elements will be stored in selectedElements and any of these can be assigned to selectedElement
     * by calling selectFromElements(int index) step definition.
     * Selenium returns an empty list if no elements found, however NoSuchElementException is thrown to
     * remain consistent with the selectElementBy... methods.
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select elements by tag using value \"([^\"]*)\"$")
    public void selectElementsByTag(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.tagName(selector)) :
                selectedElement.findElements(By.tagName(selector));
    }

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     * If multiple elements are found the first element will be assigned to selectedElement
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by class name using value \"([^\"]*)\"$")
    public void selectElementByClassName(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.className(selector)) :
                selectedElement.findElement(By.className(selector));
    }

    /**
     * Selected elements will be stored in selectedElements and any of these can be assigned to selectedElement
     * by calling selectFromElements(int index) step definition.
     * Selenium returns an empty list if no elements found, however NoSuchElementException is thrown to
     * remain consistent with the selectElementBy... methods.
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select elements by class name using value \"([^\"]*)\"$")
    public void selectElementsByClassName(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.className(selector)) :
                selectedElement.findElements(By.className(selector));
    }

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by link text using value \"([^\"]*)\"$")
    public void selectElementByLinkText(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.linkText(selector)) :
                selectedElement.findElement(By.linkText(selector));
    }

    /**
     * Selected element will be stored in selectedElement which is the webElement used by default for most step defs
     * An exception is thrown if no element is found and the value of selectedElement will remain unchanged
     *
     * @param selector a string that targets an element in the dom.
     * @throws NoSuchElementException when no element is not found
     */
    @When("^I? ?select element by partial link text using value \"([^\"]*)\"$")
    public void selectElementByPartialLinkText(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.partialLinkText(selector)) :
                selectedElement.findElement(By.partialLinkText(selector));
    }

    /**
     * Selects WebElement from selectedElements and assigned to selectedElement
     *
     * @param index to select from selectedElements
     */
    @When("^I? ?select index (\\d+) from selected elements$")
    public void selectFromElements(Integer index) {
        selectedElement = selectedElements.get(index);
    }

    @When("^I? ?filter selected elements by xpath using value \"([^\"]*)\"$")
    public void filterSelectedElementsByXPath(String filter) {
        selectedElements.removeIf(element -> element.findElements(By.xpath(filter)).size() == 0);
    }

    @When("^I? ?filter selected elements based on if their descendents contain the text \"([^\"]*)\"$")
    public void filterSelectedElementsByText(String filterText) {
        selectedElements.removeIf(element -> element.findElements(By.xpath(".//*[contains(text(),'" + filterText + "')]")).size() == 0);
    }

    /**
     * If the named element exists it will become the selectedElement so that actions can be performed on it
     *
     * @param elementName alias to be selected
     */
    @When("^I? ?select (?:the)? ?named element \"([^\"]*)\"$")
    public void selectNamedElement(String elementName) {
        selectedElement = namedElements.get(elementName);
    }

    @When("^I? ?(?:left)? ?click (?:the)? ?selected element$")
    public void clickElement() {
        selectedElement.click();
    }

    @When("^I? ?double click selected element$")
    public void doubleClickElement() {
        Actions actions = new Actions(driver);
        actions.doubleClick(selectedElement).perform();
    }

    @When("^I? ?click and hold element$")
    public void clickAndHold() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(selectedElement).perform();
    }

    /**
     * Convenience method - Submit a form if element is contained within a form
     */
    @When("^I? ?submit(?: form)?$")
    public void submit() {
        selectedElement.submit();
    }

    @When("^I? ?(?:(?:hover over)|(?:move to)) (?:the)? ?selected element$")
    public void hoverElement() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectedElement).perform();
    }

    @When("^I? ?(?:type|enter|input) (?:the text|text)? ?\"([^\"]*)\"$")
    public void enterText(String text) {
        selectedElement.sendKeys(text);
    }

    @When("^I? ?(?:press|select|click|click on)? ?(?:refresh|reload) ?(?:the)? ?(?:page)?$")
    public void refresh() {
        driver.navigate().refresh();
    }

    @When("^I? ?go back a page$")
    public void back() {
        driver.navigate().back();
    }

    @When("^I? ?go forward a page$")
    public void forward() {
        driver.navigate().forward();
    }

    @When("^I? ?pause for (\\d+) milliseconds$")
    public void pauseMs(long milliseconds) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofMillis(milliseconds));
    }

    @When("^I? ?pause for (\\d+) seconds$")
    public void pauseSecs(long seconds) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(seconds));
    }

    @When("^I? ?name (?:the)? ?selected element as \"([^\"]*)\"$")
    public void nameSelectedElement(String elementName) {
        namedElements.put(elementName, selectedElement);
    }

    // Temporary method until explicit waits are implemented
    @When("^a (\\d+) second pause$")
    public void pause(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Then("^I? ?check (?:the)? ?current url is \"([^\"]*)\"$")
    public void checkCurrentUrl(String expectedUrl) {
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Then("^I? ?check (?:the)? ?current url contains \"([^\"]*)\"$")
    public void checkCurrentUrlContains(String urlPart) {
        assertThat(driver.getCurrentUrl(), CoreMatchers.containsString(urlPart));
    }

    @Then("^I? ?check (?:the)? ?page title is \"([^\"]*)\"$")
    public void checkPageTitle(String pageTitle) {
        assertEquals(pageTitle, driver.getTitle());
    }

    @Then("^I? ?check (?:the)? ?page title contains \"([^\"]*)\"$")
    public void checkPageTitleContains(String expectedTitlePart) {
        assertThat(driver.getTitle(), CoreMatchers.containsString(expectedTitlePart));
    }

    @Then("^I? ?check (?:the)? ?page contains the text \"([^\"]*)\"$")
    public void checkPageContainsText(String expectedText) {
        List<WebElement> elementsFound = driver.findElements(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
        assertTrue("Text " + expectedText + " not found", elementsFound.size() > 0);
    }

    @Then("^I? ?check (?:the)? ?page does not contain the text \"([^\"]*)\"$")
    public void checkPageDoesNotContainsText(String unExpectedText) throws Exception {
        List<WebElement> elementsFound = driver.findElements(By.xpath("//*[contains(text(),'" + unExpectedText + "')]"));
        assertTrue("Text " + unExpectedText + " not found", elementsFound.size() == 0);
    }


    @Then("^I? ?check (?:the)? ?element's inner text is equal to \"([^\"]*)\"$")
    public void checkInnerText(String expectedText) {
        assertEquals(expectedText, selectedElement.getText());
    }

    @Then("^I? ?check (?:the)? ?element's inner text contains \"([^\"]*)\"$")
    public void checkInnerTextContains(String expectedSubText) {
        assertThat(selectedElement.getText(), CoreMatchers.containsString(expectedSubText));
    }

    @Then("^I? ?check if the element's descendents contain the text \"([^\"]*)\"$")
    public void checkDescendents(String expectedText) {
        assertTrue(selectedElement == null ?
                driver.findElements(By.xpath("//*[contains(text(),'" + expectedText + "')]")).size() > 0 :
                selectedElement.findElements(By.xpath(".//*[contains(text(),'" + expectedText + "')]")).size() > 0);
    }

    @Then("^I? ?check (?:the)? ?attribute \"([^\"]*)\" exists$")
    public void iCheckAttributeExists(String attribute) {
        String expectedAttribute = selectedElement.getAttribute(attribute);
        assertNotNull(expectedAttribute);
    }

    @Then("^I? ?check (?:the)? ?element's attribute \"([^\"]*)\" is equal to \"([^\"]*)\"$")
    public void checkAttributeValue(String attribute, String value) {
        assertEquals(value, selectedElement.getAttribute(attribute));
    }

    @Then("^I? ?check (?:the)? ?element's attribute \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void checkAttributeValueContains(String attribute, String value) {
        assertThat(selectedElement.getAttribute(attribute), CoreMatchers.containsString(value));
    }

    /**
     * @param expectedCount expected number of elements in selectedElements
     */
    @Then("^I? ?check (?:the)? ?number of elements found is (\\d+)$")
    public void checkNumberOfElementsFound(int expectedCount) {
        assertEquals(expectedCount, selectedElements.size());
    }

    /**
     * @param expectedCount expected number of elements in selectedElements
     */
    @Then("^I? ?check (?:the)? ?number of elements found is at least (\\d+)$")
    public void checkNumberOfElementsFoundAtLeast(int expectedCount) {
        assertTrue(selectedElements.size() >= expectedCount);
    }

    @Then("^I? ?check (?:the)? ?element is displayed$")
    public void checkElementIsDisplayed() {
        assertTrue(selectedElement.isDisplayed());
    }

    @Then("^I? ?check (?:the)? ?element is selected$")
    public void checkElementIsSelected() {
        assertTrue(selectedElement.isSelected());
    }

    @Then("^I? ?check (?:the)? ?element is enabled$")
    public void checkElementIsEnabled() {
        assertTrue(selectedElement.isEnabled());
    }

    /**
     * Clears both selectedElements list and the single selectedElement
     */
    @Then("^I? ?clear (?:the)? ?selected elements$")
    public void clearSelectedElements() {
        selectedElement = null;
        selectedElements = new ArrayList<>();
    }

    /**
     * Clears only named elements
     */
    @Then("^I? ?clear (?:the)? ?named elements$")
    public void clearNamedElements() {
        namedElements = new HashMap<>();
    }

    @Then("^I? ?(?:close|quit)(?:(?: the)? browser)?$")
    public void closeBrowser() {
        driver.close();
    }

    //-------------------------//
    //         ALIASES         //
    //-------------------------//

    @Given("^I (?:choose|select|am using) ?(?:the)? driver \"([^\"]*)\"$")
    public void selectDriver(String driver) throws Throwable {
        openGivenWebBrowser(driver);
    }

    @Given("^I? ?set implicit wait to (\\d+) nanoseconds$")
    public void setImplicitWaitNanoAlias(long nanos) {
        setImplicitWaitNano(nanos);
    }

    @Given("^I? ?set implicit wait to (\\d+) milliseconds$")
    public void setImplicitWaitMsAlias(long ms) {
        setImplicitWaitMs(ms);
    }

    @Given("^I? ?set implicit wait to (\\d+) seconds$")
    public void setImplicitWaitSecondsAlias(long seconds) {
        setImplicitWaitSeconds(seconds);
    }

    @Given("^I? ?maximize (?:the)? ?window$")
    public void maximizeWindowAlias() {
        maximizeWindow();
    }

    @Given("^I? ?launch (?:the )? ?browser$")
    public void openBrowserAlias() {
        openBrowser();
    }

    @Given("^(?:the)? ?browser (?:is|has been) (?:launched|(?:opened|open))$")
    public void openBrowserAlias2() {
        openBrowser();
    }

    @When("^element at index (\\d+) (?:is selected|from results is selected)$")
    public void selectFromElementsAlias(Integer index) {
        selectFromElements(index);
    }

    @When("^I (?:select|choose|get|grab|fetch|focus in on) element number (\\d+) from (?:results|selected elements|list|elements|group)$")
    public void selectFromElementsAlias2(Integer index) {
        selectFromElements(index);
    }

    @When("^I? ?(?:press|select|click|click on|go) back$")
    public void backAlias() {
        back();
    }

    @When("^I? ?(?:press|select|click|click on|go) forward$")
    public void forwardAlias() {
        forward();
    }

    // Utility methods

    /**
     * Initiates the specified browser driver
     *
     * @param browserName name of the browser
     * @throws SeleniumStepException if browser is not supported
     */
    private void launchBrowser(String browserName) throws SeleniumStepException {
        if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver(options);
        } else {
            throw new SeleniumStepException("Browser " + browserName + " is not supported");
        }
    }

    @And("^I enter user ([^\"]*)")
    public void iEnterUsername(String user) {
        driver.findElement(By.id("user")).sendKeys(user);

    }

    @And("^I enter password ([^\"]*)")
    public void iEnterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);

    }

    @Then("^\"([^\"]*)\" user with email \"([^\"]*)\"$")
    public void deleteUserByEmail(String function, String email) {
        WebElement table = driver.findElement(By.xpath("//div[@class='ReactVirtualized__Grid ReactVirtualized__Table__Grid']/div[@class='ReactVirtualized__Grid__innerScrollContainer']"));

        List<WebElement> rows = table.findElements(By.className("ReactVirtualized__Table__row"));

        for (WebElement row : rows) {
            if (row.getText().contains(email)) {
                row.findElement(By.tagName("button")).click();
            }
        }
    }

    @Then("^I? ?check (?:the)? ?element's inner text does not contain \"([^\"]*)\"$")
    public void checkInnerTextDoesNotContain(String unwantedSubText) {
        List<WebElement> notFound = driver.findElements(By.xpath("//*[contains(text(),'" + unwantedSubText + "')]"));
        assertEquals(0, notFound.size());
        notFound.clear();
    }

    @Given("^I log on as \"([^\"]*)\" using password \"([^\"]*)\"$")
    public void login(String userName, String password) {
        driver.findElement(By.id("user")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
    }

    //  @After
    //  public void forceCloseBrowser() {
    //     driver.close();
    //  }
}
