package scripts;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ProductPage;
import pages.ProductSearchPage;

public class OpenCartTest {
    private WebDriver driver;
    private String baseUrl;

    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testAddToCart() throws Exception {
        driver.get("http://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        String product = "MacBook";
        String price = "$602.00";
        ProductSearchPage productSearchPage = homePage.search(product);
        ProductPage productPage = productSearchPage.clickProduct(product);
        assertEquals(productPage.getPrice(), price);
        productPage.clickBtnAddToCart();
        assertTrue(productPage.isVisibleLabelSuccessYouHaveAdded());
        assertTrue(productPage.getTextLabelSuccessYouHaveAdded().contains(product));
        assertTrue(productPage.getTextLabelNumItemsCart().contains(price));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}

