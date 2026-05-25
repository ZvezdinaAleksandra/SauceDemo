package utils;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

        log.info("========================================= STARTING TEST {} ========================================",
                iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        log.info("========================================= FINISHED TEST {} Duration: {}s ========================================",
                iTestResult.getName(),
                getExecutionTime(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        log.error("========================================= FAILED TEST {} Duration: {}s ========================================",
                iTestResult.getName(),
                getExecutionTime(iTestResult));

        WebDriver driver =
                (WebDriver) iTestResult.getTestContext().getAttribute("driver");

        AllureUtils.takeScreenshot(driver, iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        log.warn("========================================= SKIPPING TEST {} ========================================",
                iTestResult.getName());
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return (iTestResult.getEndMillis() - iTestResult.getStartMillis()) / 1000;
    }
}