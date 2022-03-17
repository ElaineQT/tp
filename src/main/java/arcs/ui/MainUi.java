package arcs.ui;

import arcs.commands.CommandResult;
import arcs.data.validitychecker.RawInputChecker;
import arcs.data.validitychecker.YesNoChecker;
import arcs.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class MainUi {

    /**
     * Scanner for user input.
     */
    static final Scanner scannerIn = new Scanner(System.in);

    private static final String lineDivider = ("==================================================");

    private static final String appLogo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * Parser to parse user input.
     */
    static Parser parser;

    /**
     * Initializes Ui with parser.
     *
     * @param parser Parser object.
     */
    public MainUi(Parser parser) {
        MainUi.parser = parser;
    }

    /**
     * Empty constructor.
     */
    MainUi() {

    }

    /**
     * Shows message to user.
     *
     * @param message Message to show.
     */
    public void displayMessage(String message) {
        for (String line : message.split(System.lineSeparator())) {
            System.out.println(message);
        }
    }

    /**
     * Prints welcome message.
     */
    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + appLogo);
    }

    public void displayMainMenu() {
        printLineDivider();
        System.out.println(" Hi Staff, please select an option 1-3: ");
        printLineDivider();
        System.out.println("(1) Manage menu\t\t\t\t(2) Manage flight route");
        System.out.println("(3) Save and exit application");
        System.out.println("\nEnter your choice: ");
    }


    public void printLineDivider() {
        System.out.println(lineDivider);
    }

    /**
     * Prints exit message when user exits the app.
     */
    public void printExitMessage() {
        printLineDivider();
        System.out.println("Please hold your data is being saved!");
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        displayMainMenu();
        String fullCommand = scannerIn.nextLine();
        return fullCommand;
    }

    public String getUserCommand() {
        System.out.println("You are now updating flight route details.");
        String fullInputLine = scannerIn.nextLine();
        fullInputLine = fullInputLine.trim();
        return fullInputLine;
    }

    /**
     * Asks user for exit and return.
     *
     * @return True if user want to exit, false otherwise.
     */
    public boolean getUserExitStatus() {
        return parser.isYes(getUserInput("Do you want to exit? Enter YES or NO", new YesNoChecker()));
    }

    /**
     * Returns user input.
     *
     * @param message      Message to show.
     * @param rawInputChecker check whether raw input is valid
     * @return Input from user.
     */
    String getUserInput(String message, RawInputChecker rawInputChecker) {
        System.out.println(message);
        String line = scannerIn.nextLine().trim();
        while (!rawInputChecker.isValid(line)) {
            System.out.println("Invalid input!");
            System.out.println(message);
            line = scannerIn.nextLine().trim();
        }
        return line;
    }

    /**
     * Takes in user input with scanner
     *
     * @return String representation of input
     */
    public String getUserInput() {
        String userInput = scannerIn.nextLine();
        return userInput;
    }

    public void showResultToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
        // Show routes information if it exists
        ArrayList<String> routesInfo = result.getRoutesInfo();
        if (routesInfo != null) {
            for (int i = 1; i <= routesInfo.size(); i++) {
                System.out.println(i + ".");
                System.out.println(routesInfo.get(i - 1));
            }
        }
    }

    public void showToUser(String message) {
        System.out.println(message);
    }
}
