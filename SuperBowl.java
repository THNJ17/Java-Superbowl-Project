/* Tyler Harms
CS 314 01
Final Project
Uses paralell arrays to create a menu for a user to see
the Super Bowl winners, losers, and the years for each as well as how many times a team has appeared in the Super Bowl.
*/

import java.io.*;
import java.util.*;

public class SuperBowl {
    private static String[] years;
    private static String[] winningTeams;
    private static String[] losingTeams;

    public static void main(String[] args) throws IOException
    {
        readFromFile("Super Bowl Year.txt", "Super Bowl Winning Teams.txt", "Super Bowl Losing Teams.txt");
        
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            
            System.out.println("Please select an option:");
            System.out.println("1. Display the Super Bowl winning team and Super Bowl losing team for a specific year");
            System.out.println("2. Display the years and number of times a specific team has won the Super Bowl");
            System.out.println("3. Display the years and number of times a specific team has lost the Super Bowl");
            System.out.println("4. Display the number of times a specific team has been in the Super Bowl");
            System.out.println("5. Exit");

            choice = scanner.nextInt();

            if (choice == 1) 
            {
                System.out.print("Enter year: ");
                scanner.nextLine();
                int year = scanner.nextInt();
                if (year >= 1967 && year <= 2023)
                {
                    int index = display_Year(year);
                    if (index == -1) {
                        System.out.println("Year not found.");
                    } else {
                        System.out.println(years[index] + ": " + winningTeams[index] + " defeated " + losingTeams[index]);
                    }
                }
                else
                {
                    System.out.println("Invalid year inputted, please enter a year between 1967 and 2023.");
                }
            } 
            else if (choice == 2) 
            {
                System.out.print("Enter team name: ");
                scanner.nextLine();
                String teamName = scanner.nextLine();
                int wins = display_Wins(teamName);
                if (wins == 0) {
                    System.out.println("Team not found (Please enter the full name ex: Kansas City Chiefs)" +
                        "or team has not won a Super Bowl");
                } else {
                    System.out.println(teamName + " won the Super Bowl " + wins + " times in the following years:");
                    for (int i = 0; i < years.length; i++) {
                        if (winningTeams[i].equals(teamName)) {
                            System.out.println(years[i]);
                        }
                    }
                }
            } 
            else if (choice == 3) 
            {
                System.out.print("Enter team name: ");
                scanner.nextLine();
                String teamName2 = scanner.nextLine();
                int losses = display_Losses(teamName2);
                if (losses == 0) {
                    System.out.println("Team not found (Please enter the full name ex: Kansas City Chiefs)" +
                        "or team has not lost a Super Bowl");
                } else {
                    System.out.println(teamName2 + " lost the Super Bowl " + losses + " times in the following years:");
                    for (int i = 0; i < years.length; i++) {
                        if (losingTeams[i].equals(teamName2)) {
                            System.out.println(years[i]);
                        }
                    }
                }
            } 
            else if (choice == 4) 
            {
                System.out.print("Enter team name: ");
                scanner.nextLine();
                String teamName3 = scanner.nextLine();
                int appearances = display_Appearances(teamName3);
                if (appearances == 0) {
                    System.out.println("Team not found (Please enter the full name ex: Kansas City Chiefs)" +
                    "or team has not appeared in a Super Bowl");
                } else 
                {
                    System.out.println(teamName3 + " has been in the Super Bowl " + appearances + " times.");
                }
            } 
            else if (choice == 5) 
            {
                System.out.println("Goodbye!");
            } 
            else 
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //reads the txt files into arrays    
    private static void readFromFile(String yearFileName, String winningTeamFileName, String losingTeamFileName) throws IOException {
        BufferedReader yearReader = new BufferedReader(new FileReader(yearFileName));
        BufferedReader winningTeamReader = new BufferedReader(new FileReader(winningTeamFileName));
        BufferedReader losingTeamReader = new BufferedReader(new FileReader(losingTeamFileName));
    
        //adding the lines read into arrays
        years = yearReader.lines().toArray(String[]::new);
        winningTeams = winningTeamReader.lines().toArray(String[]::new);
        losingTeams = losingTeamReader.lines().toArray(String[]::new);
    
        yearReader.close();
        winningTeamReader.close();
        losingTeamReader.close();
    }
    

    //method to display the specific year of the superbowl
    private static int display_Year(int year) {
        for (int i = 0; i < years.length; i++) {
            if (Integer.parseInt(years[i]) == year) {
                return i;
            }
        }
        return -1;
    }

    //method for getting the wins for those that have won in the superbowl
    private static int display_Wins(String teamName) {
        int count = 0;
        for (int i = 0; i < winningTeams.length; i++) {
            if (winningTeams[i].equals(teamName)) {
                count++;
            }
        }
        return count;
    }

    //method for getting the losses for those that have lost in the superbowl
    private static int display_Losses(String teamName) {
        int losses = 0;
        for (int i = 0; i < losingTeams.length; i++) {
            if (losingTeams[i].equals(teamName)) {
                losses++;
            }
        }
        return losses;
    }

    //method for getting the appearance count for those that have been in the superbowl
    private static int display_Appearances(String teamName) {
        int appearances = 0;
        for (int i = 0; i < winningTeams.length; i++) {
            if (winningTeams[i].equals(teamName) || losingTeams[i].equals(teamName)) {
                appearances++;
            }
        }
        return appearances;
    }

}

