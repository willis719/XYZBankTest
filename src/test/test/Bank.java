package test.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.pages.HomeCustomerLoginPage;
import test.pages.HomeManagerLoginPage;
import test.pages.ManagerPage;
import test.utils.ExcelUtils;
import test.utils.LoadProperties;

import java.io.IOException;
import java.util.Map;

public class Bank extends BaseTest {


    // This is a smoke test. Move the damn xpath
    @Test
    public void testManagerLogin() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomeManagerLoginPage homeManagerLoginPage = new HomeManagerLoginPage(driver);
        //Verify user can get to manager page
        homeManagerLoginPage.managerLogin();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-class='btnClass1']")));
        String Url = driver.getCurrentUrl();
        Log.info("page factory configured");
        Assert.assertEquals(Url, "https://www.way2automation.com/angularjs-protractor/banking/#/manager");
    }

    @Test (priority = 6)
    public void testAddCustomer() throws IOException {
        String url = LoadProperties.getProperty("url");
        driver.get(url + "/manager");
//        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/manager");
        Map<String, String> testData = ExcelUtils.readExcel().get("AddCustomer");
        boolean alert = false;
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.addCustomerTest(testData.get("FirstName"), testData.get("LastName"), testData.get("PostCode"));
        try {
            driver.switchTo().alert().accept();
            alert = true;
        } catch (NoAlertPresentException e) {
            alert = false;
        }
        Assert.assertTrue(alert);
        System.out.println("Customer was added");
    }

    @Test
    public void testOpenAccount() {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/manager/openAccount");
        boolean alert = false;
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.openAccountTest();
        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            System.out.println(text);
            alert = true;
        } catch (NoAlertPresentException e) {
            alert = false;
        }
        Assert.assertTrue(alert);
    }

    @Test
    public void testManagerCustomers() {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/manager/list");

        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.customersTableTest();
    }


    @Test (priority = 1)
    public void testCustomerLogin() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");

        HomeCustomerLoginPage homeCustomerLoginPage = new HomeCustomerLoginPage(driver);
        homeCustomerLoginPage.customerLogin();
        String Url = driver.getCurrentUrl();
        Assert.assertEquals(Url, "https://www.way2automation.com/angularjs-protractor/banking/#/account");
    }

    @Test (dependsOnMethods = "testCustomerLogin")
    public void testCustomerDeposit() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/account");
        HomeCustomerLoginPage homeCustomerLoginPage = new HomeCustomerLoginPage(driver);

        Map<String, String> testData = ExcelUtils.readExcel().get("Deposit");
        homeCustomerLoginPage.customerDeposit(testData.get("Amount"));
    }

    @Test (dependsOnMethods = "testCustomerLogin")
    public void testCustomerWithdrawal() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/account");
        HomeCustomerLoginPage homeCustomerLoginPage = new HomeCustomerLoginPage(driver);

        Map<String, String> withdrawalData = ExcelUtils.readExcel().get("Withdrawal");
        homeCustomerLoginPage.cutomerWithdraw(withdrawalData.get("Amount"));
    }

    @Test (dependsOnMethods = "testCustomerLogin")
    public void testCustomerTransactionTable() {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/account");
        HomeCustomerLoginPage homeCustomerLoginPage = new HomeCustomerLoginPage(driver);
        homeCustomerLoginPage.transactionTable();
        // Do assertions in the test case.
    }

    // all sendkeys are part of the Excel



}
