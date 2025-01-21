import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

       
        String[] teams = new String[8];
        for (int i = 0; i < teams.length; i++) {
            System.out.print("Enter team " + (i + 1) + " name: ");
            String teamName = input.nextLine().trim();
            boolean duplicate = false;
            for (int j = 0; j < i; j++) {
                if (teams[j].equalsIgnoreCase(teamName)) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) {
                System.out.println("Team name already taken. Enter a unique name.");
                i--;
            } else {
                teams[i] = teamName;
            }
        }

       
        for (int i = 0; i < teams.length; i++) {
            int index = random.nextInt(teams.length);
            String temp = teams[i];
            teams[i] = teams[index];
            teams[index] = temp;
        }

        String[] groupA = {teams[0], teams[1], teams[2], teams[3]};
        String[] groupB = {teams[4], teams[5], teams[6], teams[7]};

        System.out.println("\nGroup A: " + groupA[0] + ", " + groupA[1] + ", " + groupA[2] + ", " + groupA[3]);
        System.out.println("Group B: " + groupB[0] + ", " + groupB[1] + ", " + groupB[2] + ", " + groupB[3]);
