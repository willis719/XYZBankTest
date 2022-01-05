package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeCustomerLoginPage {
    private WebDriver driver;

    public HomeCustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='center'][1]//button")
    private WebElement customerLoginButton;

    @FindBy(xpath = "//select[@id='userSelect']")
    private WebElement customerSelect;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitLogin;

    @FindBy(xpath = "//button[@ng-click='deposit()']")
    private WebElement depositButton;

    @FindBy(xpath = "//label[text()='Amount to be Deposited :']")
    private WebElement depositLabel;

    @FindBy(xpath = "//input[@ng-model='amount']")
    private WebElement depositBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement depositSubmit;

    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement depositConfirm;

    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    private WebElement withdrawButton;

    @FindBy(xpath = "//label[text()='Amount to be Withdrawn :']")
    private WebElement withdrawLabel;

    @FindBy(xpath = "//input[@ng-model='amount']")
    private WebElement withdrawBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement withdrawSubmit;

    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement withdrawConfirm;

    @FindBy(xpath = "//button[@ng-click='transactions()']")
    private WebElement transactionButton;

    @FindBy(xpath = "//tbody//tr//td[1]")
    private List<WebElement> dateTime;

    @FindBy(xpath = "//tbody//td[1]")
    private WebElement firstTransactionRow;

    @FindBy(xpath = "//select[@id='userSelect']")
    private WebElement userSelect;

    public void customerLogin() {
        customerLoginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(userSelect));

        Select selectCustomer = new Select(customerSelect);
        List<WebElement> names = selectCustomer.getOptions();

        for (WebElement option : names) {
            String customer = option.getText();
            System.out.println(customer);
        }

        names.get(2).click();

        wait.until(ExpectedConditions.visibilityOf(submitLogin));
        submitLogin.click();
        wait.until(ExpectedConditions.urlMatches("https://www.way2automation.com/angularjs-protractor/banking/#/account"));
    }

    public void customerDeposit(String str) {
        depositButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(depositLabel));
        depositBox.click();
        depositBox.sendKeys(str);
        depositSubmit.click();
        wait.until(ExpectedConditions.visibilityOf(depositConfirm));
    }

    public void cutomerWithdraw(String str) {
        withdrawButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(withdrawLabel));
        withdrawBox.click();
        withdrawBox.sendKeys(str);
        withdrawSubmit.click();
        wait.until(ExpectedConditions.visibilityOf(withdrawConfirm));
    }

    // regression testing
    public void transactionTable() {
        transactionButton.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        System.out.println("Date and Time of Transactions");
        for (WebElement option : dateTime) {
            String time = option.getText();
            System.out.println(time);
        }


    }
}
