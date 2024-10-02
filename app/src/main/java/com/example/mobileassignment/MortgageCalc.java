package com.example.mobileassignment;

public class MortgageCalc {

    public static double calculateMonthlyPayment(double principal, String interestRateString, String termString) {
        // Parse interest rate
        double annualInterestRate = parseInterestRate(interestRateString);
        double monthlyInterestRate = annualInterestRate / 12 / 100;

        // Parse loan term
        double termInYears = parseTermInYears(termString);
        int numberOfPayments = (int) (termInYears * 12);

        // Calculate monthly payment
        return principal *
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }

    public static double parseInterestRate(String interestRateString) {
        // Extract the percentage from the string
        String[] parts = interestRateString.split("%");
        return Double.parseDouble(parts[0].substring(parts[0].lastIndexOf(' ') + 1));
    }

    public static double parseTermInYears(String termString) {
        // Extract the number of years from the string
        String[] parts = termString.split(" ");
        return Double.parseDouble(parts[0].trim());
    }
}
