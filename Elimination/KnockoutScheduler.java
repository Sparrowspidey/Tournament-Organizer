package Elimination;
import java.util.*;

import Team.TeamInfo;

public class KnockoutScheduler {

    // Node for Knockout Tree
   public static class MatchNode {
       private String team1;
       private String team2;
       private MatchNode left;   // Winner of left match
       private MatchNode right;  // Winner of right match

        MatchNode(String t1, String t2) {
            this.team1 = t1;
            this.team2 = t2;
        }

         //  Getters
        public String getTeam1() { return team1; }
        public String getTeam2() { return team2; }
        public MatchNode getLeft() { return left; }
        public MatchNode getRight() { return right; }

        //  Setters
        public void setLeft(MatchNode left) { this.left = left; }
        public void setRight(MatchNode right) { this.right = right; }
    }

    // Generate Knockout Tournament Bracket
    public static MatchNode generateKnockout(List<TeamInfo> teams) {
        //  Randomize the team order
        Collections.shuffle(teams);
        Queue<MatchNode> queue = new LinkedList<>();

        // First round - pair teams
        for (int i = 0; i < teams.size(); i += 2) {
            String t1 = teams.get(i).getTeamName();
            String t2 = (i + 1 < teams.size()) ? teams.get(i + 1).getTeamName() : "BYE";
            queue.add(new MatchNode(t1, t2));
        }

        // Build tree upwards
        while (queue.size() > 1) {
            MatchNode left = queue.poll();
            MatchNode right = queue.poll();
            // Winner placeholder ("Winner of Match X")
            MatchNode parent = new MatchNode("Winner(" + left.team1 + " vs " + left.team2 + ")",
                                             "Winner(" + right.team1 + " vs " + right.team2 + ")");
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }

        return queue.poll(); // Root of the knockout bracket
    }

    // Print tournament bracket
    public static void printBracket(MatchNode root, int depth) {
        if (root == null) return;

        // Indentation by depth to visualize tree
        for (int i = 0; i < depth; i++) System.out.print("   ");
        System.out.println(root.team1 + " vs " + root.team2);

        printBracket(root.left, depth + 1);
        printBracket(root.right, depth + 1);
    }
}

