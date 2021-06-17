package numbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                    "square",
                    "jumping",
                    "happy",
                    "sad"
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
            default:
                numbersWithProperty(request);
                break;
        }
    }

    private void propertiesOfNumber(long number) {
        System.out.printf("Properties of %,d%n", number);
        String format = "%12s: %b\n";
        System.out.printf(format, "Even", isEven(number));
        System.out.printf(format, "Odd", isOdd(number));
        System.out.printf(format, "Spy", isSpy(number));
        System.out.printf(format, "Buzz", isBuzz(number));
        System.out.printf(format, "Duck", isDuck(number));
        System.out.printf(format, "Palindromic", isPalindromic(number));
        System.out.printf(format, "Gapful", isGapful(number));
        System.out.printf(format, "Sunny", isSunny(number));
        System.out.printf(format, "Square", isSquare(number));
        System.out.printf(format, "Jumping", isJumping(number));
        boolean happy = isHappy(number);
        System.out.printf(format, "Happy", happy);
        System.out.printf(format, "Sad", !happy);
        System.out.println();
    }

    private void propertiesOfList(long startNumber, long size) {
        if (startNumber == 0) {
            System.out.println("Starting number cannot be 0. Please try again.\n");
        } else {
            for (long number = startNumber; number < startNumber + size; number++) {
                System.out.println(numberProperties(number));
            }
            System.out.println();
        }
    }

    private void numbersWithProperty(String[] request) {
        long startNum = Long.parseLong(request[0]);
        long count = Long.parseLong(request[1]);

        String[] props = new String[request.length - 2];
        System.arraycopy(request, 2, props, 0, request.length - 2);

        do {
            String properties = numberProperties(startNum);
            if (containsProperty(props, properties)) {
                System.out.println(properties);
                count--;
            }
            startNum++;
        } while (count > 0);

        System.out.println();
    }

    private static boolean containsProperty(String[] props, String properties) {
        for (String property : props) {
            if (!properties.contains(property)) {
                return false;
            }
        }

        return true;
    }

    private String numberProperties(long number) {
        return String.format("%,d", number) +
                " is " +
                (isEven(number) ? "even" : "odd") +
                (isBuzz(number) ? ", buzz" : "") +
                (isSpy(number) ? ", spy" : "") +
                (isDuck(number) ? ", duck" : "") +
                (isGapful(number) ? ", gapful" : "") +
                (isPalindromic(number) ? ", palindromic" : "") +
                (isSquare(number) ? ", square" : "") +
                (isSunny(number) ? ", sunny" : "") +
                (isJumping(number) ? ", jumping" : "") +
                (isHappy(number) ? ", happy" : ", sad");
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
        double root = Math.sqrt((double) number);
        return number == Math.pow(Math.floor(root), 2.0);
    }

    private boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    private boolean isJumping(long number) {
        if (number < 10) {
            return true;
        }

        long digit = number % 10;
        number /= 10;
        while (number > 0) {
            long prev = digit;
            digit = number % 10;
            if (Math.abs(digit - prev) != 1) {
                return false;
            }
            number /= 10;
        }

        return true;
    }

    private boolean isHappy(long number) {
        Set<Long> numbers = new HashSet<>();
        numbers.add(number);
        long result;
        long num = number;

        do {
            result = 0;
            do {
                long digit = num % 10;
                result += digit * digit;
                num /= 10;
            } while (num > 0);
            num = result;
        } while (result != 1 && numbers.add(result));

        return result == 1;
    }
}