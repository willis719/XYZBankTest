package test.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import test.utils.DriverUtil;
import test.utils.LoadProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println(result);
//        File screenshot = ((TakesScreenshot) DriverUtil.getDriver()).getScreenshotAs(OutputType.FILE);
//        //Copy the file to a location and use try catch block to handle execution
//        try {
//            FileUtils.copyFile(screenshot, new File( System.getProperty("user.dir") + "/failedScreenShots/" + result.getMethod().getMethodName() + ".png"));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        String methodName = result.getName();
        File srcFile = ((TakesScreenshot) DriverUtil.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/failedScreenShots/";
            File destFile = new File( (String) reportDirectory + "failure_screenshots/" + methodName + "_" + formatter.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(srcFile, destFile);
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "; height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        LoadProperties.init();
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
