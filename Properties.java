package numbers;

import java.util.ArrayList;
import java.util.List;

public class Properties {
    private final List<String> properties = new ArrayList<>(
            List.of(
                    "even",
                    "odd",
                    "buzz",
                    "gapful",
                    "palindromic",
                    "spy",
                    "duck",
                    "sunny",
                    "square"
            )
    );

    String getProperties() {
        return properties.toString();
    }

    boolean hasProperty(String property) {
        return properties.contains(property);
    }

    void processRequest(String[] request) {
        switch (request.length) {
            case 1:
                propertiesOfNumber(Long.parseLong(request[0]));
                break;
            case 2:
                propertiesOfList(Long.parseLong(request[0]),
                        Long.parseLong(request[1]));
                break;
            case 3:
                numbersWithProperty(request);
                break;
        }
    }

    private void propertiesOfNumber(long number) {
        System.out.printf("Properties of %,d%n", number);
        System.out.println("       Even: " + isEven(number));
        System.out.println("        Odd: " + isOdd(number));
        System.out.println("        Spy: " + isSpy(number));
        System.out.println("       Buzz: " + isBuzz(number));
        System.out.println("       Duck: " + isDuck(number));
        System.out.println("Palindromic: " + isPalindromic(number));
        System.out.println("     Gapful: " + isGapful(number));
        System.out.println("      Sunny: " + isSunny(number));
        System.out.println("     Square: " + isSquare(number));
        System.out.println();
    }

    private void propertiesOfList(long startNumber, long size) {
        for (long number = startNumber; number < startNumber + size; number++) {
            System.out.println(numberProperties(number));
        }
        System.out.println();
    }

    private void numbersWithProperty(String[] request) {
        long startNum = Long.parseLong(request[0]);
        long count = Long.parseLong(request[1]);
        String property = request[2];

        do {
            String properties = numberProperties(startNum);
            if (properties.contains(property)) {
                System.out.println(properties);
                count--;
            }
            startNum++;
        } while (count > 0);

        System.out.println();
    }

    private String numberProperties(long number) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%,d", number));
        output.append(" is ");
        output.append(isEven(number) ? "even" : "");
        output.append(isOdd(number) ? "odd" : "");
        output.append(isBuzz(number) ? ", buzz" : "");
        output.append(isSpy(number) ? ", spy" : "");
        output.append(isDuck(number) ? ", duck" : "");
        output.append(isGapful(number) ? ", gapful" : "");
        output.append(isPalindromic(number) ? ", palindromic" : "");
        output.append(isSquare(number) ? ", square" : "");
        output.append(isSunny(number) ? ", sunny" : "");
        return output.toString();
    }

    private boolean isEven(long number) {
        return (number % 2 == 0);
    }

    private boolean isOdd(long number) {
        return (number % 2 == 1);
    }

    private boolean isBuzz(long number) {
        return (number % 7 == 0 || number % 10 == 7);
    }

    private boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }

    private boolean isPalindromic(long number) {
        return String.valueOf(number)
                .equals(new StringBuilder().append(number).reverse().toString());
    }

    private boolean isGapful(long number) {
        if (number >= 100) {
            String numberString = String.valueOf(number);
            String modulo = numberString.charAt(0) +
                    numberString.substring(numberString.length() - 1);
            return number % Long.parseLong(modulo) == 0;
        }

        return false;
    }

    private boolean isSpy(long number) {
        long num = number;
        long sum = 0;
        long product = 1;

        while (num > 0) {
            long digit = num % 10;
            sum += digit;
            product *= digit;
            num /= 10;
        }

        return sum == product;
    }

    private boolean isSquare(long number) {
        Double root = Math.sqrt((double) number);
        return number == root.longValue() * root.longValue();
    }

    private boolean isSunny(long number) {
        return isSquare(number + 1);
    }
}