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

    /**
     * <p>Checks if the supplied property is valid or not.</p>
     * <p>We handle the property regardless of if it begins with a hyphen ("-")
     * or not.</p>
     * @param property property to check for
     * @return true if in our list of properties
     */
    boolean hasProperty(String property) {
        return property.startsWith("-") ?
                properties.contains(property.substring(1)) :
                properties.contains(property);
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

    /**
     * <p>Checks the properties of a number.</p>
     * <p>Given a string array of properties that a number should or should not
     * have, check that each property either is (for a property that should be
     * included) or is not (for a property that should not be included) in the
     * properties of a number.</p>
     * @param props the properties that should/should not be listed
     * @param properties the properties of a particular number
     * @return true if the number has the properties to be included, and does
     * not have the properties that should be excluded
     */
    private static boolean containsProperty(String[] props, String properties) {
        for (String property : props) {
            if (property.startsWith("-")) {
                if (properties.contains(property.substring(1))) {
                    return false;
                }
            } else {
                if (!properties.contains(property)) {
                    return false;
                }
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

    /**
     * <p>Buzz numbers are either divisible by 7 or end in 7.</p>
     * @param number number to check
     * @return true if number qualifies as a buzz number
     */
    private boolean isBuzz(long number) {
        return (number % 7 == 0 || number % 10 == 7);
    }

    /**
     * <p>Duck numbers contain the digit zero (0).</p>
     * <p>Duck numbers are mutually exclusive from Spy numbers for inclusion on
     * a list, however, both can be specified for exclusion.</p>
     * @param number number to check
     * @return true if number qualifies as a duck number
     */
    private boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }

    /**
     * <p>Palindromic numbers read the same left to right as they do
     * right to left.</p>
     * @param number number to check
     * @return true if number qualifies as palindromic
     */
    private boolean isPalindromic(long number) {
        return String.valueOf(number)
                .equals(new StringBuilder().append(number).reverse().toString());
    }

    /**
     * <p>Gapful numbers contain at least three digits and are evenly divisible
     * by the number formed from their first and last digits.</p>
     * <p>By definition, they are greater than or equal to 100.</p>
     * @param number number to check
     * @return true if number qualifies as gapful
     */
    private boolean isGapful(long number) {
        if (number >= 100) {
            String numberString = String.valueOf(number);
            String modulo = numberString.charAt(0) +
                    numberString.substring(numberString.length() - 1);
            return number % Long.parseLong(modulo) == 0;
        }

        return false;
    }

    /**
     * <p>Numbers are spy if the sum of their digits is equal to the product of
     * their digits. By definition, any number lower than 10 is spy.</p>
     * <p>i.e. 1,214 is spy because the sum of the digits is 8
     * <code>(1 + 2 + 1 + 4)</code> and the product is also 8
     * <code>(1 * 2 * 1 * 4)</code>.</p>
     * <p>A spy number is mutually exclusive from duck numbers since the product
     * of a duck number is always going to be 0, hence it'd always fail the
     * test for being spy. However, both can be excluded as a property without
     * problem.</p>
     * @param number number to check
     * @return true if number qualifies as spy
     */
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

    /**
     * <p>Square numbers are the square of an integer - an integer raised to the
     * second power. <code>(n<sup>2</sup>)</code></p>
     * <p>Square numbers are mutually exclusive from sunny numbers, but both can
     * be specified as an a property to exclude in a list.</p>
     * @param number number to check
     * @return true if number qualifies as square
     */
    private boolean isSquare(long number) {
        double root = Math.sqrt((double) number);
        return number == Math.pow(Math.floor(root), 2.0);
    }

    /**
     * <p>Sunny numbers are one less than a square number.</p>
     * <p>i.e. <code>N + 1</code> is a square number. (63 is sunny since
     * <code>63 + 1 = 64</code> which is equal to <code>8 * 8</code>.</p>
     * <p>Sunny numbers are mutually exclusive from square numbers by definition
     * (being one less than a square), but both can be specified as a property
     * to exclude in a list.</p>
     * @param number number to check
     * @return true if number qualifies as sunny
     */
    private boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    /**
     * <p>Jumping numbers have consecutive digits which differ by one (1).</p>
     * <p>The difference between 9 and 0 is not considered as one. Single digit
     * numbers are considered jumping.</p>
     * <p>i.e. <code>78987</code> is jumping, while <code>78986</code> is not.</p>
     * @param number number to check
     * @return true if number qualifies as jumping
     */
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

    /**
     * <p>Happy numbers reach 1 after a sequence of adding the squares of each
     * digit. If a number is not Happy, it is Sad.</p>
     * <p>i.e. <code>13</code> is a happy number as <code>1<sup>2</sup> +
     * 3<sup>2</sup> = 10</code> which leads to <code>1<sup>2</sup> +
     * 0<sup>2</sup> = 1</code>.</p>
     * @param number number to check
     * @return true if number qualifies as happy
     */
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