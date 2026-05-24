package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.printf(
                "========================================= STARTING TEST %s ========================================%n",
                iTestResult.getName()
        );
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.printf(
                "========================================= FINISHED TEST %s Duration: %ss ========================================%n",
                iTestResult.getName(),
                getExecutionTime(iTestResult)
        );
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.printf(
                "========================================= FAILED TEST %s Duration: %ss ========================================%n",
                iTestResult.getName(),
                getExecutionTime(iTestResult)
        );

        WebDriver driver =
                (WebDriver) iTestResult.getTestContext().getAttribute("driver");

        AllureUtils.takeScreenshot(driver, iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.printf(
                "========================================= SKIPPING TEST %s ========================================%n",
                iTestResult.getName()
        );
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return (iTestResult.getEndMillis() - iTestResult.getStartMillis()) / 1000;
    }
}