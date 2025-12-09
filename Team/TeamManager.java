package Team;



import java.util.*;

//  Manager to handle all team creation
public class TeamManager {
    private List<TeamInfo> teams;

    public TeamManager() {
        this.teams = new ArrayList<>();
    }

    // Ask user for number of teams and team names
    // Note: pass sportName so TeamInfo.addPlayers can behave per-sport
    public void createTeams(Scanner sc, int maxPlayers, String sportName) {
        System.out.print("\nEnter number of teams: ");
        int numTeams;
        // read a whole line and parse to avoid nextInt/nextLine pitfalls
        while (true) {
            String line = sc.nextLine().trim();
            try {
                numTeams = Integer.parseInt(line);
                if (numTeams <= 1) {
                    System.out.print("No of Teams cannot be 1,0 or negative: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Enter number of teams: ");
            }
        }

        for (int i = 0; i < numTeams; i++) {
            System.out.print("\nEnter name of Team " + (i + 1) + ": ");
            String teamName = sc.nextLine().trim();
            if (teamName.isEmpty()) {
                System.out.println("Team name cannot be empty. Try again.");
                i--;
                continue;
            }

            Team t = new Team(teamName);
            TeamInfo ti = new TeamInfo(t);                   // use existing constructor
            ti.addPlayers(sc, maxPlayers, sportName);        // pass sportName here
            teams.add(ti);
        }
    }

    public List<TeamInfo> getTeams() {
        return teams;
    }

    public void displayTeams() {
        System.out.println("\n--- Teams & Players ---");
        for (TeamInfo ti : teams) {
            System.out.println(ti);
        }
    }
}

