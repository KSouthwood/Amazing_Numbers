package numbers;

import java.util.*;

public class Input {
    enum Errors {
        NATURAL_1 ("The first parameter should be a natural number or zero.\n"),
        NATURAL_2 ("The second parameter should be a natural number.\n"),
        PROPERTY ("The property %s is wrong.\nAvailable properties: %s\n\n"),
        PROPERTIES ("The properties %s are wrong.\nAvailable properties: %s\n\n"),
        EXCLUSIVE ("The request contains mutually exclusive properties: [%s, %s]\n" +
                "There are no numbers with these properties.\n\n"),
        DUPLICATE ("WARNING: The property/properties %s was/were typed more than once.\n\n");

        private final String message;

        Errors(String s) {
            message = s;
        }

        public String getMessage() {
            return message;
        }
    }

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

            String[] params = request.split(" ");

            if (validateRequest(params)) {
                properties.processRequest(params);
            }
        } while (!request.equals("0"));
    }

    private boolean validateRequest(String[] params) {
        if (params.length > 2) {
            List<String> props = new ArrayList<>();
            Collections.addAll(props, Arrays.copyOfRange(params, 2, params.length));
            return validateNumbers(params) && validateProperties(props);
        }

        return validateNumbers(params);
    }

    /**
     * <p>Check that the number(s) entered are natural (positive integer greater
     * than zero (0).</p>
     *
     * @param params the user input as an array
     * @return true if the number(s) are natural
     */
    private boolean validateNumbers(String[] params) {
        if (!params[0].matches("\\d+")) {
            printError(Errors.NATURAL_1);
            return false;
        }

        if (params.length > 1) {
            if (!params[1].matches("\\d+")) {
                printError(Errors.NATURAL_2);
                return false;
            }
        }

        return true;
    }

    /**
     * <p>Validate properties to ensure they're all valid, not mutually exclusive, and
     * we have no duplicates.</p>
     * @param params the array of parameters input by the user
     * @return true if all properties are valid
     */
    private boolean validateProperties(List<String> params) {
        Set<String> included = new HashSet<>();
        Set<String> excluded = new HashSet<>();
        Set<String> invalid = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        // populate set with the properties we want to check for validating them at the same time
        for (String property : params) {
            // valid property check
            if (!properties.hasProperty(property)) {
                invalid.add(property);
            }

            // duplicate property check - add() is false if we're adding a property again
            if (property.startsWith("-")) {
                if (!excluded.add(property)) {
                    duplicates.add(property);
                }
            } else {
                if (!included.add(property)) {
                    duplicates.add(property);
                }
            }
        }

        if (!invalid.isEmpty()) {
            printError(invalid.size() == 1 ? Errors.PROPERTY : Errors.PROPERTIES,
                    invalid.toString(),
                    properties.getProperties());
            return false;
        }

        if (!duplicates.isEmpty()) {
            printError(Errors.DUPLICATE, duplicates.toString(), properties.getProperties());
        }

        return isNotMutuallyExclusive(included, excluded);
    }

    /**
     * <p>Makes sure the properties are not mutually exclusive.</p>
     * <p>Calling method ensures both sets contain different and
     * valid properties.</p>
     *
     * @param inclusion Set of inclusive properties to be checked
     * @param exclusion Set of exclusive properties to be checked
     * @return true if the properties are NOT mutually exclusive
     */
    private boolean isNotMutuallyExclusive(Set<String> inclusion, Set<String> exclusion) {
        String[][] exclusives = {
                {"even", "odd"}, {"-even", "-odd"},
                {"duck", "spy"},
                {"sunny", "square"},
                {"happy", "sad"}, {"-happy", "-sad"}
        };

        Set<String> allProperties = new HashSet<>();
        allProperties.addAll(inclusion);
        allProperties.addAll(exclusion);

        for (String[] set : exclusives) {
            if (allProperties.contains(set[0]) && allProperties.contains(set[1])) {
                printError(Errors.EXCLUSIVE, set[0], set[1]);
                return false;
            }
        }

        for (String property : exclusion) {
            if (inclusion.contains(property.substring(1))) {
                printError(Errors.EXCLUSIVE, property.substring(1), property);
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
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must bot be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void printError(Errors error) {
        System.out.println(error.getMessage());
    }

    private static void printError(Errors error, String first, String second) {
        System.out.printf(
                error.getMessage(),
                first.toUpperCase(),
                second.toUpperCase()
        );
    }
}
