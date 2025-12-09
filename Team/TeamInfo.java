package Team;




import java.util.*;

//  Holds players + roles (depends on sport)
public class TeamInfo {
    private Team team;
    private List<String> mainPlayers;
    private List<String> substitutes;
    private String captain;
    private String viceCaptain;
    private String coach;
    private String igl; // for esports


    public TeamInfo(Team team) {
        this.team = team;
        this.mainPlayers = new ArrayList<>();
        this.substitutes = new ArrayList<>();
        
    }

    // Add players with roles based on sport
    public void addPlayers(Scanner sc, int maxPlayers, String sportName) {
        sportName = sportName.toLowerCase();
        System.out.println("\nEnter players for team: " + team.getTeamName());
        System.out.println("Maximum slots: " + maxPlayers);

        if (sportName.equals("cricket")) {
            // 11 main + 4 subs + 1 coach = 16
            inputCaptainViceCoach(sc, 11); 
            inputCoach(sc);

            for (int i = mainPlayers.size(); i < 11; i++) {
                System.out.print("Enter Main Player " + (i + 1) + " name: ");
                mainPlayers.add(sc.nextLine());
            }

            for (int i = 0; i < 4; i++) {
                System.out.print("Enter Substitute Player " + (i + 1) + " name: ");
                substitutes.add(sc.nextLine());
            }
        } 
        else if (sportName.equals("football")) {
            // 11 main + 7 subs + coach = 19
            inputCaptainViceCoach(sc, 11);
            inputCoach(sc);

            for (int i = mainPlayers.size(); i < 11; i++) {
                System.out.print("Enter Main Player " + (i + 1) + " name: ");
                mainPlayers.add(sc.nextLine());
            }

            for (int i = 0; i < 7; i++) {
                System.out.print("Enter Substitute Player " + (i + 1) + " name: ");
                substitutes.add(sc.nextLine());
            }
        } 
        else if (sportName.equals("basketball")) {
            // 5 main + 7 subs + coach = 13
            inputCaptainViceCoach(sc, 5);
            inputCoach(sc);

            for (int i = mainPlayers.size(); i < 5; i++) {
                System.out.print("Enter Main Player " + (i + 1) + " name: ");
                mainPlayers.add(sc.nextLine());
            }

            for (int i = 0; i < 7; i++) {
                System.out.print("Enter Substitute Player " + (i + 1) + " name: ");
                substitutes.add(sc.nextLine());
            }
        } 
        else if (sportName.equals("volleyball")) {
            // 6 main + 6 subs + coach = 13
            inputCaptainViceCoach(sc, 6);
            inputCoach(sc);

            for (int i = mainPlayers.size(); i < 6; i++) {
                System.out.print("Enter Main Player " + (i + 1) + " name: ");
                mainPlayers.add(sc.nextLine());
            }

            for (int i = 0; i < 6; i++) {
                System.out.print("Enter Substitute Player " + (i + 1) + " name: ");
                substitutes.add(sc.nextLine());
            }
        }
        else if (sportName.equals("esports")) {
            // IGL + 3 other players
            System.out.print("Enter IGL (In-Game Leader) name: ");
            this.igl = sc.nextLine();
            mainPlayers.add(igl);

            for (int i = 1; i < maxPlayers; i++) {
                System.out.print("Enter Player " + (i + 1) + " name: ");
                mainPlayers.add(sc.nextLine());
            }
        } 
        else {
            // Tennis, Golf → no special roles
            for (int i = 0; i < maxPlayers; i++) {
                System.out.print("Enter Player " + (i + 1) + " name: ");
                mainPlayers.add(sc.nextLine());
            }
        }
    }

    // Helper to input Captain & Vice (part of main players)
    private void inputCaptainViceCoach(Scanner sc, int mainCount) {
        System.out.print("Enter Captain name (part of Main squad): ");
        this.captain = sc.nextLine();
        mainPlayers.add(captain);

        System.out.print("Enter Vice-Captain name (part of Main squad): ");
        this.viceCaptain = sc.nextLine();
        mainPlayers.add(viceCaptain);
    }

    private void inputCoach(Scanner sc) {
        System.out.print("Enter Coach name: ");
        this.coach = sc.nextLine();
    }

     //  NEW Getters (to fix KnockoutScheduler errors)
    public Team getTeam() {
        return this.team; 
    }

    public String getTeamName() {
        return (this.team != null) ? this.team.getTeamName() : "";
    }

    //  Return list of players with roles
    public List<String> getPlayersWithRoles() {
        List<String> playersWithRoles = new ArrayList<>();

        for (String p : mainPlayers) {
            if (p.equals(captain)) playersWithRoles.add(p + " (Captain)");
            else if (p.equals(viceCaptain)) playersWithRoles.add(p + " (Vice-Captain)");
            else playersWithRoles.add(p);
        }

        if (coach != null && !coach.isEmpty()) {
            playersWithRoles.add(coach + " (Coach)");
        }

        if (igl != null && !igl.isEmpty()) {
            playersWithRoles.add(igl + " (IGL)");
        }

        for (String sub : substitutes) {
            playersWithRoles.add(sub + " (Substitute)");
        }

        return playersWithRoles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nTeam: " + team.getTeamName());
        sb.append("\nPlayers:");
        for (String p : getPlayersWithRoles()) {
            sb.append("\n  - ").append(p);
        }
        return sb.toString();
    }
}
