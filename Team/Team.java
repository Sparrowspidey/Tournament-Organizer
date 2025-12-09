package Team;
//  Simple class representing a Team
public class Team {
    private String teamName;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

     @Override
    public String toString() {
        return "Team: " + teamName;
    }
}
