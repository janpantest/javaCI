package com.yourname.selenium.fixtures;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RetryRule implements TestRule {
    private final int retryCount;

    public RetryRule(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable caughtThrowable = null;

                for (int i = 1; i <= retryCount; i++) {
                    try {
                        base.evaluate(); // Run the test
                        return; // Success!
                    } catch (Throwable t) {
                        caughtThrowable = t;
                        System.out.println(description.getDisplayName() + ": run " + i + " failed.");
                    }
                }
                System.out.println(description.getDisplayName() + ": giving up after " + retryCount + " failures.");
                throw caughtThrowable; // All retries failed
            }
        };
    }
}
