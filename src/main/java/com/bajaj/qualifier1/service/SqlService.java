package com.bajaj.qualifier1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlService {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlService.class);
    
    /**
     * Determines which SQL question to solve based on the last digit of regNo
     * ODD last digit → Question 1
     * EVEN last digit → Question 2
     */
    public int determineQuestion(String regNo) {
        if (regNo == null || regNo.isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be null or empty");
        }
        
        // Extract last digit
        char lastChar = regNo.charAt(regNo.length() - 1);
        int lastDigit;
        
        try {
            lastDigit = Character.getNumericValue(lastChar);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid registration number format. Last character must be a digit.");
        }
        
        if (lastDigit < 0 || lastDigit > 9) {
            throw new IllegalArgumentException("Last character must be a digit (0-9)");
        }
        
        int questionNumber = (lastDigit % 2 == 0) ? 2 : 1;
        logger.info("Registration Number: {}, Last Digit: {}, Question Selected: {}", 
            regNo, lastDigit, questionNumber);
        
        return questionNumber;
    }
    
    /**
     * Solves the SQL question and returns the final query
     * Since we don't have the actual questions, I'll create placeholder solutions
     * In a real scenario, these would be replaced with the actual SQL queries
     */
    public String solveSqlQuestion(int questionNumber) {
        logger.info("=== Solving SQL Question {} ===", questionNumber);
        
        String sqlQuery;
        
        switch (questionNumber) {
            case 1:
                // Question 1 solution (ODD last digit)
                // Placeholder - replace with actual SQL query
                sqlQuery = solveQuestion1();
                break;
            case 2:
                // Question 2 solution (EVEN last digit)
                // Placeholder - replace with actual SQL query
                sqlQuery = solveQuestion2();
                break;
            default:
                throw new IllegalArgumentException("Invalid question number: " + questionNumber);
        }
        
        logger.info("SQL Query Generated: {}", sqlQuery);
        return sqlQuery;
    }
    
    /**
     * Solve Question 1 (for ODD last digit)
     * Replace this with the actual SQL query
     */
    private String solveQuestion1() {
        // TODO: Replace with actual SQL query for Question 1
        // Example placeholder:
        return "SELECT * FROM table_name WHERE condition = 'value';";
    }
    
    /**
     * Solve Question 2 (for EVEN last digit)
     * Replace this with the actual SQL query
     */
    private String solveQuestion2() {
        // TODO: Replace with actual SQL query for Question 2
        // Since regNo ends with 6 (even), this will be used
        // Example placeholder:
        return "SELECT * FROM table_name WHERE condition = 'value';";
    }
}


