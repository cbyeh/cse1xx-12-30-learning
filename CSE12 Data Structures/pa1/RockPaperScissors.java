
/**
 * Class RockPaperScissors. Plays repeated games of Rock Paper Scissors with a user
 *
 * @author Christopher Yeh
 * @date 10/9/2018
 * @PID A15720503
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    static String[] systemMoves;
    static LinkedList<String> userMoves;
    static int initialCapacity = 5;
    static Counter totalGames;
    static Counter playerWin;
    static Counter cpuWin;
    static String status;

    static String gen_cpu_move() {
        // Generates CPU move. Move is represented by "r"/"p"/"s".
        int random = (int) (Math.random() * 3);
        String[] randomChoice = new String[]{"r", "p", "s"};
        switch (random) {
            case 0:
                return randomChoice[0]; // random generated rock
            case 1:
                return randomChoice[1]; // random generated paper
        }
        return randomChoice[2]; // random generated scissors
    }

    public static void main(String[] args) {
        // Store the total number of games played.
        totalGames = new Counter();
        // Store the number of times player wins.
        playerWin = new Counter();
        // Store the number of times cpu wins.
        cpuWin = new Counter();
        // Store the System's move history
        systemMoves = new String[initialCapacity];
        // Store the user's move history
        userMoves = new LinkedList<String>();
        // for systemMoves counting
        int counter = 0;
        // boolean flag = false; // will be set true if no exception is thrown
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("Let's play!  What's your move? " +
                "(r=rock, p=paper, s=scissors or q to quit)");
        while (scanner.hasNext()) {
            String computerMove = gen_cpu_move();
            userInput = scanner.next();
            if (userInput.equalsIgnoreCase("q"))
                break;
            try {
                if (!userInput.equalsIgnoreCase("r") &&
                        !userInput.equalsIgnoreCase("p") &&
                        !userInput.equalsIgnoreCase("s"))
                    throw new IllegalArgumentException(("Invalid input!"));
                playGame(userInput, computerMove);
                userMoves.add(userInput);
                if (counter == systemMoves.length)
                    systemMoves = doubleArraySize(systemMoves);
                systemMoves[counter] = computerMove;
                counter++;
            } catch (IllegalArgumentException ex) {
                System.out.println("INVALID ARGUMENT. Please try again");
            }
            System.out.println("Let's play!  What's your move? " +
                    "(r=rock, p=paper, s=scissors or q to quit)");
        }
        System.out.println("Our most recent games (in reverse order) were: ");
        // print out last 10 moves made by cpu and user
        if (totalGames.getCount() < 10)
            printRecentGames(totalGames.getCount());
        else
            printRecentGames(10);
        double winPercentage = (double) cpuWin.getCount() / totalGames.getCount() * 100;
        double lossPercentage = (double) playerWin.getCount() / totalGames.getCount() * 100;
        System.out.println("\nOur overall stats are:\n\tI won: " +
                winPercentage + "%\tYou won: " +
                lossPercentage + "%\tWe tied: " +
                (100 - winPercentage - lossPercentage) + "%");
    }

    //  playGame() will one instance of a game
    static void playGame(String userMoves, String cpuMoves) {
        // winner logic: rock beats scissors, scissors beats paper, paper beats rock
        if (cpuMoves.equals("r")) {
            if (userMoves.equals("r"))
                status = "TIE";
            if (userMoves.equals("p"))
                status = "WON";
            if (userMoves.equals("s"))
                status = "LOST";
        } else if (cpuMoves.equals("p")) {
            if (userMoves.equals("r"))
                status = "LOST";
            if (userMoves.equals("p"))
                status = "TIE";
            if (userMoves.equals("s"))
                status = "WON";
        } else if (cpuMoves.equals("s")) {
            if (userMoves.equals("r"))
                status = "WON";
            if (userMoves.equals("p"))
                status = "LOST";
            if (userMoves.equals("s"))
                status = "TIE";
        }
        switch (status) {
            case "WON":
                playerWin.increment();
                totalGames.increment();
                System.out.println("\nI chose " + cpuMoves + ", you win!");
                break;
            case "LOST":
                cpuWin.increment();
                totalGames.increment();
                System.out.println("\nI chose " + cpuMoves + ", you lost ;)");
                break;
            case "TIE":
                totalGames.increment();
                System.out.println("\nI chose " + cpuMoves + ", It's a tie!");
                break;
        }
    }

    static void printRecentGames(int numberOfLists) {
        for (int i = userMoves.size() - 1; i >= userMoves.size() - numberOfLists; i--) {
            System.out.println("\tMe: " + systemMoves[i] +
                    "\tYou: " + userMoves.get(i));
        }
    }

    //
    static String[] doubleArraySize(String[] array) {
        String[] newArray = new String[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}