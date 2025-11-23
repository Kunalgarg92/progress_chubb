package hdfc;
import java.util.Scanner;

class Car {
    String model;
    String color;
    double cost;
    Car(String model, String color, double cost) {
        this.model = model;
        this.color = color;
        this.cost = cost;
    }
    public void displayCarDetails() {
        System.out.println("Car Model " + model);
        System.out.println("Color " + color);
        System.out.println("Cost " + cost);
    }
}

class LoanCalculator {
    Car car;
    double loan;
    double rate;
    int tenureYears;
    double downPayment;
    LoanCalculator(Car car, double loan, double rate, int tenureYears, double downPayment) {
        this.car = car;
        this.loan = loan;
        this.rate = rate;
        this.tenureYears = tenureYears;
        this.downPayment = downPayment;
    }
    public double calculateTotalAmount() {
        double interest = (loan * rate * tenureYears) / 100;
        return loan + interest;
    }
    public double calculateEMI() {
        double total = calculateTotalAmount();
        int months = tenureYears * 12;
        return total / months;
    }
    public double calculateCompoundInterestTotal() {
        return loan * Math.pow(1 + rate / 100, tenureYears);
    }

    public double calculateCompoundEMI() {
        return calculateCompoundInterestTotal() / (tenureYears * 12);
    }
    void displayLoanDetails() {
        car.displayCarDetails();
        System.out.println("Down Payment:" + downPayment);
        System.out.println("Loan Amount " + loan);
        System.out.println("Interest Rate: " + rate);
        System.out.println("Tenure: " + tenureYears + " years");
        double total = calculateTotalAmount();
        double emi = calculateEMI();
        System.out.println("Simple Interest");
        System.out.println("Total Amount to be Paid : " + total);
        System.out.println("Monthly EMI:" + emi);
        double compoundTotal = calculateCompoundInterestTotal();
        double compoundEMI = calculateCompoundEMI();
        System.out.println("Compound Interest");
        System.out.println("Total Amount to be Paid:" + compoundTotal);
        System.out.println("Monthly EMI:" + compoundEMI);
    }
}

public class Selfbanking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter car model ");
        String model = sc.next();
        double cost = 0;
        switch (model.toLowerCase()) {
            case "delta": cost = 800000;
                break;
            case "beta": cost = 1000000;
                break;
            case "alfa": cost = 1200000;
                break;
            default: System.out.println("Invalid model");
                return;
        }
        System.out.println("Enter car color: ");
        String color = sc.next();
        Car car = new Car(model, color, cost);
        System.out.println("Enter down payment amount: ");
        double downPayment = sc.nextDouble();
        if (downPayment >= cost) {
            System.out.println("Down payment cannot be equal to or greater than car cost!");
            return;
        }
        double loanAmount = cost - downPayment;
        System.out.println("Calculated Loan Amount:" + loanAmount);
        System.out.println("Enter loan tenure");
        int tenure = sc.nextInt();
        System.out.println("Enter rate of interest");
        double rate = sc.nextDouble();
        LoanCalculator loan = new LoanCalculator(car, loanAmount, rate, tenure, downPayment);
        loan.displayLoanDetails();
    }
}
