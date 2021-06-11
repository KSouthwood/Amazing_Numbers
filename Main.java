package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number = readNumber();
        if (number > 0) {
            evenOdd(number);
            buzz(number);
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
        System.out.println(number % 2 == 0 ?
                "This number is Even." :
                "This number is Odd.");
    }

    private static void buzz(int number) {
        String[] reasons = {"%d is neither divisible by 7 nor it ends with 7.",
        "%d is divisible by 7.",
        "%d ends with 7.",
        "%d is divisible by 7 and it ends with 7."};

        boolean divideBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;

        int reason = divideBy7 ? 1 : 0;
        reason += endsWith7 ? 2 : 0;

        System.out.println((divideBy7 || endsWith7) ?
                "It is a Buzz number." :
                "It is not a Buzz number.");
        System.out.println("Explanation:");
        System.out.printf(reasons[reason] + "%n", number);
    }
}
