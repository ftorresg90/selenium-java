package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends WebBasePage{
    private static WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        HomePage.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "search")
    private WebElement searchBar;

    public ProductSearchPage search(String product){
        searchBar.sendKeys(product);
        searchBar.sendKeys(Keys.ENTER);
        return new ProductSearchPage(driver);
    }



}