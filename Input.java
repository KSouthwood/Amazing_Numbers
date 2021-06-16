package numbers;

import java.util.Map;
import java.util.Scanner;

public class Input {
    private static final Map<String, String> errorMsg = Map.of(
            "natural1", "The first parameter should be a natural number or zero.\n",
            "natural2", "The second parameter should be a natural number.\n",
            "one_property", "The property [%s] is wrong.\nAvailable properties: %s\n\n",
            "two_properties", "The properties [%s, %s] are wrong.\nAvailable properties: %s\n\n",
            "exclusive", "The request contains mutually exclusive properties: [%s, %s]\n" +
                    "There are no numbers with these properties.\n\n",
            "same_properties",
            "The properties [%s, %s] are the same. Please type two different properties.\n" +
                    "Available properties: %s\n\n"
    );

    private final Map<String, String> patterns = Map.of(
            "natural", "\\d+",
            "property", "\\w+"
    );

    Properties properties = new Properties();

    void inputLoop() {
        final Scanner scanner = new Scanner(System.in);
        printInstructions();
        String request;
        do {
            System.out.print("Enter a request: ");
            request = scanner.nextLine().toLowerCase();
            System.out.println();

            if (request.equals("0")) {
                continue;
            }

            if (request.isBlank()) {
                printInstructions();
                continue;
            }

            String[] params = request.split(" ", 4);

            if (validateRequest(params)) {
                properties.processRequest(params);
            }
        } while (!request.equals("0"));
    }

    private boolean validateRequest(String[] params) {
        switch (params.length) {
            case 1:
            case 2:
                return validateNumbers(params);
            case 3:
                return validateNumbers(params) && validateOneProperty(params[2]);
            case 4:
                return validateNumbers(params) &&
                        validateTwoProperties(params[2], params[3]);
            default:
                return false;
        }
    }

    /**
     * <p>Check that the number(s) entered are natural (positive integer greater
     * than zero (0).</p>
     *
     * @param params the user input as an array
     * @return true if the number(s) are natural
     */
    private boolean validateNumbers(String[] params) {
        if (!params[0].matches(patterns.get("natural"))) {
            printError("natural1");
            return false;
        }

        if (params.length > 1) {
            if (!params[1].matches(patterns.get("natural"))) {
                printError("natural2");
                return false;
            }
        }

        return true;
    }

    /**
     * <p>Ensures that the user entered a valid property to search for.</p>
     *
     * @param property value to search for
     * @return true if property is valid
     */
    private boolean validateOneProperty(String property) {
        if (properties.hasProperty(property)) {
            return true;
        }

        printError("one_property", property, properties.getProperties());
        return false;
    }

    /**
     * <p>Ensure that both properties entered are valid.</p>
     * <p>Checks that the properties entered by the user are different, valid
     * properties to search for. If they are, also calls the method to ensure
     * they're not mutually exclusive.</p>
     *
     * @param first  property one
     * @param second property two
     * @return true if different, valid and not mutually exclusive
     */
    private boolean validateTwoProperties(String first, String second) {
        // check if the two properties are the same
        if (first.equals(second)) {
            printError("same_properties", first, second, properties.getProperties());
            return false;
        }

        boolean property1 = properties.hasProperty(first);
        boolean property2 = properties.hasProperty(second);

        // check that both properties are valid
        if (property1 && property2) {
            return isNotMutuallyExclusive(first, second);
        }

        // one or both of the properties is/are not valid, determine which one
        if (property1 || property2) {
            // one property is invalid
            String property;
            if (!property1) {   // determine which one
                property = first;
            } else {
                property = second;
            }
            printError("one_property", property, properties.getProperties());
        } else {
            // both properties are invalid
            printError("two_properties", first, second, properties.getProperties());
        }

        return false;
    }

    /**
     * <p>Makes sure the properties are not mutually exclusive.</p>
     * <p>Calling method ensures both properties are different and valid.</p>
     *
     * @param first  property one
     * @param second property two
     * @return true if the properties are NOT mutually exclusive
     */
    private boolean isNotMutuallyExclusive(String first, String second) {
        String[] exclusives = {
                "even odd", "duck spy", "sunny square"
        };

        for (String exclusive : exclusives) {
            if (exclusive.contains(first) && exclusive.contains(second)) {
                printError("exclusive", first, second);
                return false;
            }
        }

        return true;
    }

    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void printError(String message) {
        System.out.println(errorMsg.get(message));
    }

    private static void printError(String message, String first, String second) {
        System.out.printf(
                errorMsg.get(message),
                first.toUpperCase(),
                second.toUpperCase()
        );
    }

    private static void printError(String message, String first, String second, String third) {
        System.out.printf(
                errorMsg.get(message),
                first.toUpperCase(),
                second.toUpperCase(),
                third.toUpperCase()
        );
    }
}
