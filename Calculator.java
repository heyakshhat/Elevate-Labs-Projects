import java.util.Scanner;

public class Calculator {

    // Method for addition
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    // Method for subtraction
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Method for multiplication
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Method for division with divide-by-zero handling
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Division by zero is not allowed!");
            return Double.NaN; // Not a Number
        }
        return num1 / num2;
    }

    // Method to display menu
    public static void displayMenu() {
        System.out.println("\n=== Java Console Calculator ===");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Exit");
        System.out.print("Choose an operation (1-5): ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        System.out.println("Welcome to Java Console Calculator!");
        System.out.println("You can perform basic arithmetic operations.");

        // Main loop for multiple calculations
        while (continueCalculating) {
            displayMenu();

            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Thank you for using the calculator. Goodbye!");
                continueCalculating = false;
                continue;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice! Please select 1-5.");
                continue;
            }

            // Get numbers from user
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            double result = 0;
            String operation = "";

            // Perform calculation based on user choice
            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    operation = "+";
                    break;
                case 2:
                    result = subtract(num1, num2);
                    operation = "-";
                    break;
                case 3:
                    result = multiply(num1, num2);
                    operation = "*";
                    break;
                case 4:
                    result = divide(num1, num2);
                    operation = "/";
                    if (Double.isNaN(result)) {
                        continue; // Skip displaying result if division by zero
                    }
                    break;
            }

            // Display result
            System.out.println("\nResult: " + num1 + " " + operation + " " + num2 + " = " + result);

            // Ask if user wants to continue
            System.out.print("\nDo you want to perform another calculation? (y/n): ");
            scanner.nextLine(); // consume newline
            String continueChoice = scanner.nextLine().toLowerCase();

            if (!continueChoice.equals("y") && !continueChoice.equals("yes")) {
                System.out.println("Thank you for using the calculator. Goodbye!");
                continueCalculating = false;
            }
        }

        scanner.close();
    }
}