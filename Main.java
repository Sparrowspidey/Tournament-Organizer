import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Elimination.KnockoutScheduler;
import League.*;
import Schedule.ScheduleExporter;
import Team.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // use one scanner

        Map<String, List<String>> choices = new HashMap<>();
        List<LeagueInfo> leagueDetails = new ArrayList<>();
        List<SportsInfo> sportsDetails = new ArrayList<>();

        league League = new league();

        //  Ask repeatedly until user gives valid league
        LeagueInfo leagueObj = null;
        String lg = "";
        while (leagueObj == null) {
            System.out.println("Enter league from the below list:");
            System.out.println("1. International\n2. National\n3. State\n4. District\n5. City\n6. School\n7. College");
            lg = sc.nextLine();
            leagueObj = League.display(lg);
            if (leagueObj == null) {
                System.out.println("Invalid league! Please try again.\n");
            }
        }
        leagueDetails.add(leagueObj);

        //  Ask repeatedly until user gives valid sport
        SportsInfo si = null;
        String sport = "";
        while (si == null || si.getMaxPlayers() == 0) {
            System.out.println("Enter sport from the below list:");
            System.out.println("1. Cricket\n2. Football\n3. Tennis\n4. Basketball\n5. Golf\n6. Volleyball\n7. Esports");
            sport = sc.nextLine();
            si = new SportsInfo(sport,sc);
            if (si.getMaxPlayers() == 0) {
                System.out.println("Invalid sport! Please try again.\n");
            }
        }
        sportsDetails.add(si);

        //  Store user choices in HashMap
        choices.putIfAbsent(lg, new ArrayList<>());
        choices.get(lg).add(sport);

        //  Create teams
        TeamManager tm = new TeamManager();
        tm.createTeams(sc, si.getMaxPlayers(), si.getSportName());
        

        
        KnockoutScheduler.MatchNode root = KnockoutScheduler.generateKnockout(tm.getTeams());
        

        //  Ask user for start date
     
           String startDate = "";

       while (true) {
        System.out.print("\nEnter tournament start date (yyyy-MM-dd): ");
        startDate = sc.nextLine();

        try {
            LocalDate date = LocalDate.parse(startDate);
            LocalDate today = LocalDate.now();
       if (date.isBefore(today)){
        System.out.println("Invalid date entry! please try again");
       } else {
        System.out.println("Start Date :- "+ date);
        break;
       }
    }  catch (DateTimeParseException e) {
        System.out.println(" Invalid date format! Please enter again in yyyy-MM-dd format.");
    }
}


        //  Export PDF
       //  Create dynamic file name using sport name and timestamp
String fileName = si.getSportName().toLowerCase() + "_schedule_" + System.currentTimeMillis() + ".pdf";
String outputPath = "C:\\Users\\VIVEK\\Desktop\\DSA Project\\DSA" + fileName;

//  Export the schedule to PDF
ScheduleExporter.exportSchedule(root, startDate, outputPath,
        leagueObj.getLeagueName(), leagueObj.getLocation(), si.getSportName());

System.out.println("\n Tournament setup complete! Schedule saved at:\n" + outputPath);




//  Post-tournament interactive menu
        while (true) {
            System.out.println("\n--- Post-Tournament Menu ---");
            System.out.println("1. Show all team names");
            System.out.println("2. Show players in a specific team");
            System.out.println("3. Show all team details");
            System.out.println("4. Open schedule PDF");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int ch;
            try {
                ch = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                continue;
            }

            switch (ch) {
                case 1:
                    System.out.println("\n=== Teams in the Tournament ===");
                    for (TeamInfo team : tm.getTeams()) {
                        System.out.println("- " + team.getTeamName());
                    }
                    break;

                case 2:
                    System.out.print("Enter team name: ");
                    String teamName = sc.nextLine();
                    boolean found = false;
                    for (TeamInfo team : tm.getTeams()) {
                        if (team.getTeamName().equalsIgnoreCase(teamName)) {
                            System.out.println("\n🏏 Team: " + team.getTeamName());
                            System.out.println("Players:");
                            for (String player : team.getPlayersWithRoles()) {
                                System.out.println("  - " + player);
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Team not found. Please check the name and try again.");
                    }
                    break;


                case 3:
                    System.out.println("\n=== All Teams and Players ===");
                    tm.displayTeams();
                    break;
                    
                    
                case 4:
                    try {
                        System.out.println("Opening schedule PDF...");
                        java.awt.Desktop.getDesktop().open(new java.io.File(outputPath));
                    } catch (Exception e) {
                        System.out.println(" Unable to open file automatically. Please open it manually from: " + outputPath);
                    }
                    break;

                case 5:
                    System.out.println("Exiting program. Thank you for using Tournament Organiser!");
                    sc.close();  
                    return;    

                default:
                    System.out.println("Invalid choice! Please select again.");
            }

        }
       
    
}
}
