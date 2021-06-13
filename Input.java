package numbers;

import java.util.Scanner;

public class Input {
    private final String[] errorMsg = {
            "The first parameter should be a natural number or zero.\n",
            "The second parameter should be a natural number.\n",
            "The property [%s] is wrong.\nAvailable properties: %s\n\n"
    };

    private final String[] patterns = {
            "\\d+",
            "\\d+",
            "\\w+"
    };

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

            String[] params = request.split(" ", 3);

            if (validateRequest(params)) {
                properties.processRequest(params);
            }
        } while (!request.equals("0"));
    }

    private boolean validateRequest(String[] params) {
        for (int index = 0; index < params.length; index++) {
            if (index < 2) {
                if (!params[index].matches(patterns[index])) {
                    System.out.println(errorMsg[index]);
                    return false;
                }
            } else {
                if (!properties.hasProperty(params[index])) {
                    System.out.printf(
                            errorMsg[index],
                            params[index].toUpperCase(),
                            properties.getProperties().toUpperCase()
                    );
                    return false;
                }
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
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }
}
