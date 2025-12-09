package League;


import java.util.*;



// Class to handle user input for league
public class league {
    Scanner s = new Scanner(System.in);

    // Method that displays prompt based on league type entered
    public LeagueInfo display(String lg) {
        String location = "";
        String leagueName = "";
        lg = lg.toLowerCase();

        switch(lg) {
            case "international":
                System.out.println("You have selected International league");
                System.out.println("Please enter the name of the International league:");
                leagueName = s.nextLine();
                System.out.println("Please enter in which country it is going to be held:");
                location = s.nextLine();
                return new LeagueInfo("International", location, leagueName);

            case "national":
                System.out.println("You have selected National league");
                System.out.println("Please enter your nationality:");
                String nationality = s.nextLine();
                System.out.println("Please enter the name of the National league:");
                leagueName = s.nextLine();
                return new LeagueInfo("National", nationality, leagueName);

            case "state":
                System.out.println("You have selected State league");
                System.out.println("Please enter your state:");
                String state = s.nextLine();
                System.out.println("Please enter the name of the State league:");
                leagueName = s.nextLine();
                return new LeagueInfo("State", state, leagueName);

            case "district":
                System.out.println("You have selected District league");
                System.out.println("Please enter your district:");
                String district = s.nextLine(); 
                System.out.println("Please enter the name of the District league:");
                leagueName = s.nextLine();
                return new LeagueInfo("District", district, leagueName);

            case "city":
                System.out.println("You have selected City league");
                System.out.println("Please enter your city:");
                String city = s.nextLine(); 
                System.out.println("Please enter the name of the City league:");
                leagueName = s.nextLine();
                return new LeagueInfo("City", city, leagueName);

            case "school":
                System.out.println("You have selected School league");
                System.out.println("Please enter your school name:");
                String school = s.nextLine();   
                System.out.println("Please enter the name of the School league:");
                leagueName = s.nextLine();
                return new LeagueInfo("School", school, leagueName);

            case "college":
                System.out.println("You have selected College league");
                System.out.println("Please enter your college name:");
                String college = s.nextLine();          
                System.out.println("Please enter the name of the College league:");
                leagueName = s.nextLine();
                return new LeagueInfo("College", college, leagueName);

            default:
                System.out.println("Invalid league selection");
                return null;
        }
    }
}
