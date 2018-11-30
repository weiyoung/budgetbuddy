package systemUI;

import model.BudgetBuddy;
import model.DateTimeParser;
import model.Saves;
import model.exceptions.NegativeInputException;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

//    public static void main(String[] args) {
//        new Application();
//    }

    Scanner scanner = new Scanner(System.in);
    BudgetBuddy buddy = BudgetBuddy.getInstance();
    Menu menu = Menu.getInstance(buddy);

    Application() {
        System.out.println("Buddy ID: " + System.identityHashCode(buddy));
        parseInfo();
        loadSaveFile();
        menu.menu();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void parseInfo() {
        try {
            String urlString = "http://api.timezonedb.com/v2.1/get-time-zone?key=6WI0HR0J6QJF&format=json&by=zone&zone=America/Vancouver";
            URL url = new URL(urlString);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append(System.lineSeparator());
            }
            DateTimeParser parser = new DateTimeParser();
            parser.parse(response.toString());
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file...");
        } catch (JSONException e) {
            System.out.println("Error parsing JSON data");
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void loadSaveFile() {
        try {
            Saves s = new Saves();
            s.load(buddy);
            System.out.println("Loaded from memory...");
        } catch (Exception e) {
            System.out.println("Save file unavailable...");
            System.out.println("\nHiii!! I am your Budget Buddy (^ ω ^)");
            start();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void start() {
        System.out.println("\nPlease enter monthly budget: ");
        try {
            inputBudget();
        } catch (NegativeInputException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Confirm monthly budget at $%.2f? (enter Y or N)", buddy.getLimit());
        String input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            System.out.printf("Monthly budget set at $%.2f \n", buddy.getLimit());
            System.out.println("\n[̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅] [̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅] [̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅]\n");
        } else {
            System.out.printf("\nLet's start again (＾u＾)\n");
            start();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void inputBudget() throws NegativeInputException {
        try {
            double budget = scanner.nextDouble();
            menu.checkInputValidity(budget);
            buddy.setLimit(budget);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

}
