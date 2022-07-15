package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends  WebBasePage{
    private static WebDriver driver;

    ProductPage(WebDriver driver){
        super(driver);
        ProductPage.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "li h2")
    private WebElement price;

    @FindBy(id = "button-cart")
    private WebElement btnAddToCart;

    @FindBy(xpath = "//div[@class = 'alert alert-success alert-dismissible']")
    private WebElement labelSuccessYouHaveAdded;

    @FindBy(xpath = "//i[@class = 'fa fa-shopping-cart']//parent::span")
    private WebElement labelNumItemsCart;


    public String getPrice(){
        return price.getText();
    }


    public void clickBtnAddToCart() throws InterruptedException {
        waitUntilElementIsVisible(btnAddToCart);
        btnAddToCart.click();
    }

    public boolean isVisibleLabelSuccessYouHaveAdded(){
        waitUntilElementIsVisibleNonThrow(labelSuccessYouHaveAdded,10);
        return isVisible(labelSuccessYouHaveAdded);
    }

    public String getTextLabelSuccessYouHaveAdded(){
        waitUntilElementIsVisible(labelSuccessYouHaveAdded);
        return labelSuccessYouHaveAdded.getText();
    }

    public String getTextLabelNumItemsCart(){
        waitUntilElementIsVisible(labelNumItemsCart);
        return labelNumItemsCart.getText();
    }

}