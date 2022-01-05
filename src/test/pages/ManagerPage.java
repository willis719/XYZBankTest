package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerPage {
    private WebDriver driver;

    public ManagerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Add Customer Variable
    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement customer;

    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement fNameField;

    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lNameField;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement customerSubmit;


    // Open Account
    @FindBy(xpath = "//select[@id='userSelect']")
    private WebElement customerSelect;

    @FindBy(xpath = "//select[@id='currency']")
    private WebElement currencySelect;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement accountSubmit;


    // Customers
    @FindBy(xpath = "//thead//td[1]//a")
    private WebElement fNameHead;

    @FindBy(xpath = "//thead//td[2]//a")
    private WebElement lNameHead;

    @FindBy(xpath = "//thead//td[3]//a")
    private WebElement postCodeHead;

    @FindBy(xpath = "//tbody//tr//td[1]")
    private List<WebElement> fNames;

    @FindBy(xpath = "//tbody//tr//td[2]")
    private List<WebElement> lnames;

    @FindBy(xpath = "//tbody//tr//td[3]")
    private List<WebElement> postCodes;


    public void addCustomerTest(String fName, String lName, String postCode) {
        customer.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(fNameField));
        fNameField.sendKeys(fName);
        lNameField.sendKeys(lName);
        postCodeBox.sendKeys(postCode);
        customerSubmit.click();
    }

    public void openAccountTest() {
        // See it as a dropdown
        Select selectCustomer = new Select(customerSelect);
        List<WebElement> names = selectCustomer.getOptions();

        // display all names
        for (WebElement option : names) {
            String customerName = option.getText();
            System.out.println(customerName);
        }
        // select second name on the list
        names.get(2).click();

        Select selectCurrency = new Select(currencySelect);
        List<WebElement> currencies = selectCurrency.getOptions();

        for (WebElement money : currencies) {
            String currency = money.getText();
            System.out.println(currency);
        }

        // Select first currency
        currencies.get(1).click();

        accountSubmit.click();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void customersTableTest() {
        // What happens when a user clicks the first name header
        ArrayList<String> noOrder = new ArrayList<>();
        System.out.println("--------- No order before First Name header click ---------");
        for (WebElement element1 : fNames) {
            String name = element1.getText();
            noOrder.add(name);
            System.out.println(name);
        }

        fNameHead.click();
        ArrayList<String> order = new ArrayList<>();
        System.out.println("-------- After First Name header Click (Reverse Order) -----------");
        for (WebElement element : fNames) {
            String name1 = element.getText();
            order.add(name1);
            System.out.println(name1);
        }


        Collections.sort(noOrder, Collections.reverseOrder());
        boolean reverseCheck = noOrder.equals(order);
        System.out.println(reverseCheck);

        // What happens when a user clicks the Last Name header
        ArrayList<String> lNameNoOrder = new ArrayList<>();
        System.out.println("-------- No order before Last Name header click --------------");
        for (WebElement lname : lnames) {
            String lastName = lname.getText();
            lNameNoOrder.add(lastName);
            System.out.println(lastName);
        }

        lNameHead.click();

        ArrayList<String> lNameOrder = new ArrayList<>();
        System.out.println("------- After Last Name header click -----------");
        for (WebElement lname2 : lnames) {
            String lastName2 = lname2.getText();
            lNameOrder.add(lastName2);
            System.out.println(lastName2);
        }

        Collections.sort(lNameNoOrder);
        boolean reverseCheck2 = lNameNoOrder.equals(lNameOrder);
        System.out.println(reverseCheck2);



        // What happens when a user clicks the Post Code header
        ArrayList<String> postCodeNoOrder = new ArrayList<>();
        System.out.println("-------- No order before Postal Code header click --------------");
        for (WebElement pCode : postCodes) {
            String postCode = pCode.getText();
            postCodeNoOrder.add(postCode);
            System.out.println(postCode);
        }

        postCodeHead.click();

        ArrayList<String> postCodeOrder = new ArrayList<>();
        System.out.println("------- After Postal Code header click -----------");
        for (WebElement pCode2 : postCodes) {
            String postCode2 = pCode2.getText();
            postCodeOrder.add(postCode2);
            System.out.println(postCode2);
        }

        Collections.sort(postCodeNoOrder, Collections.reverseOrder());
        boolean reverseCheck3 = postCodeNoOrder.equals(postCodeOrder);
        System.out.println(reverseCheck3);
    }

}
