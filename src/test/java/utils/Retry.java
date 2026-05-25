package utils;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Log4j2
public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY = 3;
    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            log.warn(
                    "[RETRY] Test '{}' failed. Retry {}/{}",
                    result.getName(),
                    count,
                    MAX_RETRY
            );
            if (result.getThrowable() != null) {
                log.warn(
                        "[RETRY] Cause: {}",
                        result.getThrowable().toString()
                );
            }
            return true;
        }
        log.error(
                "[RETRY] Test '{}' FAILED окончательно после {} попыток",
                result.getName(),
                MAX_RETRY + 1
        );
        return false;
    }
}