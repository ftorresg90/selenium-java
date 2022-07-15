package pages;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class WebBasePage {

    private static WebDriver driver;

    private static final int WAIT_TIMEOUT = 90;

    private static final int POLLING = 100;

    private final WebDriverWait wait;
    private boolean acceptNextAlert = true;

    protected WebBasePage(WebDriver driver){
        WebBasePage.driver=driver;
        this.wait= new WebDriverWait(driver, WAIT_TIMEOUT, POLLING);
        PageFactory.initElements(driver,this);
    }

    protected WebBasePage(WebDriver driver, int timeOutSec){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, timeOutSec, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT), this);
    }

    protected WebBasePage(WebDriver driver, int timeOutSec, int pollingSec){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, timeOutSec, pollingSec);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT), this);
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
    protected boolean isVisible(WebElement webElement){
        try {
            return webElement.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
    public boolean isVisible(By webElement) {
        try {
            return getDriver().findElement(webElement).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isInvisible(WebElement element){
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e){
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected boolean isInvisible(By by){
        try {
            return !getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilElementIsVisible(WebElement element){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        }catch (ConditionTimeoutException e){
            throw new ConditionTimeoutException(String.format("No se encuentra el elemento despues de 30 segundos\nElemento: %s", element));
        }
    }

    public void waitUntilElementIsVisible(By element){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        }catch (ConditionTimeoutException e){
            throw new ConditionTimeoutException(String.format("No se encuentra el elemento despues de 30 segundos\nElemento: %s", element));
        }
    }

    public void waitUntilElementIsVisibleNonThrow(WebElement element, int WAIT_TIMEOUT){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        } catch (ConditionTimeoutException e) {
        }
    }

    public void waitUntilElementIsVisibleNonThrow(By element, int WAIT_TIMEOUT){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        } catch (ConditionTimeoutException e) {
        }
    }

}
