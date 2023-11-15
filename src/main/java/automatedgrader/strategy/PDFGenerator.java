package automatedgrader.strategy;

import java.util.List;

import automatedgrader.composite.Submission;

// Strategy Interface
public interface PDFGenerator {
    void generatePDF(Submission submission, List<TestResult> testResults);
}

