package com.bajaj.qualifier1.config;

public class SqlQuerySolver {

    public static String solveSqlProblem(String regNo) {
        int lastDigit = Integer.parseInt(regNo.substring(regNo.length() - 1));

        if (lastDigit % 2 == 0) {
            // Even → Question 2
            return "SELECT department, COUNT(*) AS employee_count FROM employees GROUP BY department;";
        } else {
            // Odd → Question 1
            return "SELECT name, salary FROM employees WHERE salary > 50000;";
        }
    }
}
