package League;

import java.util.Scanner;

//  Class to store info about a sport
public class SportsInfo {
    private String sportName;   // Name of the sport
    private int maxPlayers;     // Max number of players allowed
    private String[] players;   // Array to hold player names

    // Constructor that initializes based on sport name and takes a Scanner
    public SportsInfo(String sportName, Scanner s) { 
        this.sportName = capitalize(sportName); 
        // Pass the scanner 's' to the helper method
        this.maxPlayers = getDefaultSlots(sportName.toLowerCase(), s); 
        this.players = (this.maxPlayers > 0) ? new String[this.maxPlayers] : new String[maxPlayers];
    }

    // Helper: decide default player slots for each sport
    // Now accepts a Scanner object 's'
    public int getDefaultSlots(String sport, Scanner s) {

        // No need for a try-finally block here since we didn't create the scanner
        switch(sport) {
            case "cricket": 
                return 16; // 11 + 4 subs + 1 coach

            case "football": 
                return 19; // 11 + 7 subs + 1 coach

            case "basketball": 
                return 13; // 5 + 7 subs + 1 coach

            case "volleyball": 
                return 13; // 6 + 6 subs + 1 coach

            case "tennis":
                String choice;
                
                // Show the initial prompt
                System.out.print("Singles or Doubles? (Enter 'S' for Singles, 'D' for Doubles): ");
                choice = s.nextLine().trim().toUpperCase();

                // Loop ONLY if the initial choice was invalid
                while (!choice.equals("S") && !choice.equals("D")) {
                    System.out.println("Invalid entry. Please enter 'S' for Singles or 'D' for Doubles.");
                    
                    // Reprompt with the full question
                    System.out.print("Singles or Doubles? (Enter 'S' for Singles, 'D' for Doubles): ");
                    choice = s.nextLine().trim().toUpperCase();
                }

                // Validation passed. Return based on the final, valid choice.
                if (choice.equals("D")) {
                    return 2; // Doubles
                } else {
                    System.out.println("'For singles Player name will be Team name.'\n'So please enter Team name as player name.'");

                    return 1; // Singles (must be 'S')
                }

            case "golf": 
                return 4;  // typical group size (no captain/coach)

            case "esports": 
                return 5;  // 4 players + 1 IGL

            default: 
                return 0; // invalid sport entered
        }
    }

    // --- Getters and Helpers ---

    // Getter for maxPlayers
    public int getMaxPlayers() {
        return maxPlayers;
    }

    //  Getter for sportName
    public String getSportName() {
        return sportName;
    }

    public String[] getPlayers() { return players; }

    // Helper: capitalize sport name properly
    public String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }

    // To print sport details
    public String toString() {
        return sportName + " | Slots for players: " + maxPlayers;
    }

    
}