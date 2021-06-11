package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        printInstructions();
        mainLoop();
        System.out.println("Goodbye!");
    }

    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("  * separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void mainLoop() {
        final Scanner scanner = new Scanner(System.in);
        String request;
        do {
            System.out.print("Enter a request: ");
            request = scanner.nextLine();
            System.out.println();

            if (request.isEmpty()) {
                printInstructions();
                continue;
            }

            String[] params = request.split(" ");
            if (params.length == 1) {
                if (params[0].matches("\\d+")) {
                    processRequest(request);
                } else {
                    System.out.println("The first parameter should be a natural number or zero.\n");
                }
            }

            if (params.length == 2) {
                if (params[0].matches("\\d+")) {
                    if (params[1].matches("\\d+")) {
                        processRequest(request);
                    } else {
                        System.out.println("The second parameter should be a natural number.\n");
                    }
                } else {
                    System.out.println("The first parameter should be a natural number or zero.\n");
                }
            }
        } while (!request.equals("0"));
    }

    private static void processRequest(String request) {
        if (!request.equals("0")) {
            long[] params = Arrays.stream(request.split(" ")).mapToLong(Long::parseLong).toArray();

            if (params.length == 1) {
                propertiesOfNumber(params[0]);
            }

            if (params.length == 2) {
                propertiesOfList(params[0], params[1]);
            }
        }
    }

    private static void propertiesOfNumber(long number) {
        System.out.printf("Properties of %,d%n", number);
        System.out.println("       Even: " + isEven(number));
        System.out.println("        Odd: " + isOdd(number));
        System.out.println("       Buzz: " + isBuzz(number));
        System.out.println("       Duck: " + isDuck(number));
        System.out.println("Palindromic: " + isPalindromic(number));
        System.out.println("     Gapful: " + isGapful(number));
        System.out.println();
    }

    private static void propertiesOfList(long startNumber, long size) {
        StringBuilder output = new StringBuilder();
        for (long num = startNumber; num < startNumber + size; num++) {
            output.setLength(0);
            output.append(String.format("%,d", num));
            output.append(" is ");
            output.append(isBuzz(num) ? "buzz, " : "");
            output.append(isDuck(num) ? "duck, " : "");
            output.append(isEven(num) ? "even, " : "");
            output.append(isGapful(num) ? "gapful, " : "");
            output.append(isOdd(num) ? "odd, " : "");
            output.append(isPalindromic(num) ? "palindromic, " : "");
            output.setLength(output.length() - 2);
            System.out.println(output);
        }
        System.out.println();
    }

    private static boolean isEven(long number) {
        return (number % 2 == 0);
    }

    private static boolean isOdd(long number) {
        return (number % 2 == 1);
    }

    private static boolean isBuzz(long number) {
        return (number % 7 == 0 || number % 10 == 7);
    }

    private static boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }

    private static boolean isPalindromic(long number) {
        return String.valueOf(number)
                .equals(new StringBuilder().append(number).reverse().toString());
    }

    private static boolean isGapful(long number) {
        if (number >= 100) {
            String numberString = String.valueOf(number);
            String modulo = numberString.charAt(0) +
                    numberString.substring(numberString.length() - 1);
            return number % Long.parseLong(modulo) == 0;
        }

        return false;
    }
}
