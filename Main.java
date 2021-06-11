package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number = readNumber();
        if (number > 0) {
            System.out.printf("Properties of %d%n", number);
            evenOdd(number);
            buzz(number);
            duck(number);
        } else {
            System.out.println("This number is not natural!");
        }
    }

    private static int readNumber() {
        int number = 0;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        String input = scanner.nextLine();
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("This number is not natural!");
        }
        return number;
    }

    private static void evenOdd(int number) {
        System.out.println("Even: " + (number % 2 == 0));
        System.out.println("Odd : " + (number % 2 == 1));
    }

    
    private static void buzz(int number) {
        System.out.println("Buzz: " + (number % 7 == 0 || number % 10 == 7));
    }

    private static void duck(int number) {
        String num = String.valueOf(number);
        System.out.println("Duck: " + num.contains("0"));
    }
}
