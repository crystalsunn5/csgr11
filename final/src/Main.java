import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] teams = new String[8];
        System.out.println("Enter the names of 8 teams:");
        for (int i = 0; i < 8; i++) {
            while (true) {
                System.out.print("Team " + (i + 1) + ": ");
                String teamName = scanner.nextLine().trim();
                boolean isUnique = true;
                for (int j = 0; j < i; j++) {
                    if (teams[j].equalsIgnoreCase(teamName)) {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique) {
                    teams[i] = teamName;
                    break;
                } else  {
                    System.out.println("Team name must be unique. Try again.");
                }
            }
        }

        System.out.println("\nTeams entered:");
        for (String team : teams) {
            System.out.println(team);
        }
   List<String> groupA = new ArrayList<>();
        List<String> groupB = new ArrayList<>();
        List<String> allTeams = Arrays.asList(teams);
        Collections.shuffle(allTeams);

        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                groupA.add(allTeams.get(i));
            } else {
                groupB.add(allTeams.get(i));
            }
        }

        System.out.println("\nGroup A:");
        for (String team : groupA) {
            System.out.println(team);
        }

        System.out.println("\nGroup B:");
        for (String team : groupB) {
            System.out.println(team);
        }
 Map<String, Integer> points = new HashMap<>();
        Map<String, Integer> goalsScored = new HashMap<>();
        Map<String, Integer> goalsConceded = new HashMap<>();

        simulateGroupMatches(groupA, points, goalsScored, goalsConceded, random);
        simulateGroupMatches(groupB, points, goalsScored, goalsConceded, random);
        
System.out.println("\nGroup A Standings:");
        displayStandings(groupA, points, goalsScored, goalsConceded);

        System.out.println("\nGroup B Standings:");
        displayStandings(groupB, points, goalsScored, goalsConceded);

     String semiFinal1Winner = simulateMatch(groupA.get(0), groupB.get(1), random);
        String semiFinal2Winner = simulateMatch(groupB.get(0), groupA.get(1), random);

        System.out.println("\nSemifinal Results:");
        System.out.println(groupA.get(0) + " vs " + groupB.get(1) + " -> Winner: " + semiFinal1Winner);
        System.out.println(groupB.get(0) + " vs " + groupA.get(1) + " -> Winner: " + semiFinal2Winner);

        String champion = simulateMatch(semiFinal1Winner, semiFinal2Winner, random);

        System.out.println("\nFinal Match:");
        System.out.println(semiFinal1Winner + " vs " + semiFinal2Winner + " -> Winner: " + champion);

        System.out.println("\nTournament Champion: " + champion);
    }

    private static void simulateGroupMatches(List<String> group, Map<String, Integer> points,
                                             Map<String, Integer> goalsScored, Map<String, Integer> goalsConceded,
                                             Random random) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                String team1 = group.get(i);
                String team2 = group.get(j);
                int team1Goals = random.nextInt(5);
                int team2Goals = random.nextInt(5);

                goalsScored.put(team1, goalsScored.getOrDefault(team1, 0) + team1Goals);
                goalsScored.put(team2, goalsScored.getOrDefault(team2, 0) + team2Goals);

                goalsConceded.put(team1, goalsConceded.getOrDefault(team1, 0) + team2Goals);
                goalsConceded.put(team2, goalsConceded.getOrDefault(team2, 0) + team1Goals);

                if (team1Goals > team2Goals) {
                    points.put(team1, points.getOrDefault(team1, 0) + 3);
                } else if (team1Goals < team2Goals) {
                    points.put(team2, points.getOrDefault(team2, 0) + 3);
                } else {
                    points.put(team1, points.getOrDefault(team1, 0) + 1);
                    points.put(team2, points.getOrDefault(team2, 0) + 1);
                }
            }
        }
    }

    private static void displayStandings(List<String> group, Map<String, Integer> points,
                                         Map<String, Integer> goalsScored, Map<String, Integer> goalsConceded) {
        group.sort((team1, team2) -> points.get(team2) - points.get(team1));

        for (String team : group) {
            System.out.println(team + " - Points: " + points.getOrDefault(team, 0) + ", Goals Scored: " +
                    goalsScored.getOrDefault(team, 0) + ", Goals Conceded: " + goalsConceded.getOrDefault(team, 0));
        }
    }

    private static String simulateMatch(String team1, String team2, Random random) {
        int team1Goals = random.nextInt(5) + 1;
        int team2Goals = random.nextInt(5) + 1;

        while (team1Goals == team2Goals) {
            team1Goals = random.nextInt(5) + 1;
            team2Goals = random.nextInt(5) + 1;
        }

        return team1Goals > team2Goals ? team1 : team2;
    }
}
