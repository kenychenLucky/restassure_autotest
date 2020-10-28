package com.chancetop.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * auther :kenychen
 */
@Slf4j
public class TestLogListener extends TestListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(TestLogListener.class);

    /**
     * start
     *
     * @param iTestContext ITestContext
     */
    @Override
    public void onStart(ITestContext iTestContext) {
        super.onStart(iTestContext);

        log.info(String.format("====================%sSTART====================", iTestContext.getName()));
    }

    /**
     * teststart
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart(iTestResult);
        log.info(String.format("========%s.%sTEST START========", iTestResult.getInstanceName(), iTestResult.getName()));
    }

    /**
     * test pass
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess(iTestResult);
        log.info(String.format("========%s.%sTEST PASS========", iTestResult.getInstanceName(), iTestResult.getName()));
    }

    /**
     * Falt
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure(iTestResult);
        log.error(String.format("========%s.%sTEST FAIL,：\n%s========", iTestResult.getInstanceName(), iTestResult.getName(), iTestResult.getThrowable()));
    }

    /**
     *
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped(iTestResult);
        log.info(String.format("========%s.%sSKIP TEST========", iTestResult.getInstanceName(), iTestResult.getName()));
    }

    /**
     * end
     *
     * @param iTestContext ITestContext
     */
    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish(iTestContext);
        log.info(String.format("====================%sEND====================", iTestContext.getName()));
    }
}
