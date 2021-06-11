package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.\n");
        mainLoop();
        System.out.println("Goodbye!");
    }

    private static void mainLoop() {
        long number;
         do {
            number = readNumber();
             if (number > 0) {
                 System.out.printf("Properties of %d%n", number);
                 evenOdd(number);
                 buzz(number);
                 duck(number);
                 palindromic(number);
                 System.out.println();
             }
         } while (number != 0);
    }

    private static long readNumber() {
        long number = Integer.MIN_VALUE;
        final Scanner scanner = new Scanner(System.in);

        while (number < 0) {
            System.out.print("Enter a request: ");
            String input = scanner.nextLine();
            System.out.println();
            if (!input.matches("\\d+")) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            }
            number = Long.parseLong(input);
            if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.\n");
            }
        }
        return number;
    }

    private static void evenOdd(long number) {
        System.out.println("       Even: " + (number % 2 == 0));
        System.out.println("       Odd : " + (number % 2 == 1));
    }

    private static void buzz(long number) {
        System.out.println("       Buzz: " + (number % 7 == 0 || number % 10 == 7));
    }

    private static void duck(long number) {
        String num = String.valueOf(number);
        System.out.println("       Duck: " + num.contains("0"));
    }

    private static void palindromic(long number) {
        String num = String.valueOf(number);
        System.out.println("Palindromic: " +
                num.equals(new StringBuilder(num).reverse().toString()));
    }
}
