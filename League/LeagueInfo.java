package League;




// Class to hold information about a league
public class LeagueInfo {
  private String leagueType;   // Type (International, National, etc.)
  private String location;     // Place (country, state, city, etc.)
  private String leagueName;   // Actual league name

    LeagueInfo(String leagueType, String location, String leagueName) {
        this.leagueType = leagueType;
        this.location = location;
        this.leagueName = leagueName;
    }

    // Getters
    public String getLeagueType() { return leagueType; }
    public String getLocation() { return location; }
    public String getLeagueName() { return leagueName; }

    // To print league details
    public String toString() {
        return leagueType + " League | Location: " + location + " | League Name: " + leagueName;
    }
}

