package automatedgrader.strategy;

// Strategy
public class TestResult {
    private String testName;
    private boolean passed;
    private String feedback;

    // Constructors, getters, setters...

    public TestResult(String testName, boolean passed, String feedback) {
        this.testName = testName;
        this.passed = passed;
        this.feedback = feedback;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
