# Task 1
mport java.util.Scanner;
import java.util.Random;

public class NumberGame {

    private static final int MAX_ATTEMPTS = 5;
    private static final int RANGE_LIMIT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        printWelcomeMessage();

        boolean playing = true;
        while (playing) {

            totalScore += playRound(scanner);

            System.out.print("\nFeel like another round? (y/n): ");
            String response = scanner.next().toLowerCase();
            playing = response.startsWith("y");
        }

        System.out.println("\nFinal Score: " + totalScore);
        System.out.println("Thanks for hanging out, see ya next time!");
        scanner.close();
    }



    private static int playRound(Scanner scanner) {
        int targetNumber = new Random().nextInt(RANGE_LIMIT) + 1;
        int attemptsUsed = 0;

        System.out.printf("\nAlright, I'm thinking of a number between 1 and %d.\n", RANGE_LIMIT);
        System.out.println("Can you get it in " + MAX_ATTEMPTS + " tries?");

        while (attemptsUsed < MAX_ATTEMPTS) {
            int guess = getValidGuess(scanner);
            attemptsUsed++;

            if (guess == targetNumber) {
                int points = (MAX_ATTEMPTS - attemptsUsed) + 1;
                System.out.printf("Nice! You got it in %d tries. +%d points!\n", attemptsUsed, points);
                return points;
            }

            provideHint(guess, targetNumber);
            int remaining = MAX_ATTEMPTS - attemptsUsed;
            if (remaining > 0) System.out.println("Attempts left: " + remaining);
        }

        System.out.println("Bummer! You're out of tries. The number was: " + targetNumber);
        return 0;
    }

    private static int getValidGuess(Scanner scanner) {
        while (true) {
            System.out.print("Your guess: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Oops, that's not a number. Try again!");
                scanner.next();
            }
        }
    }

    private static void provideHint(int guess, int target) {
        if (guess < target) {
            System.out.println("A bit higher...");
        } else {
            System.out.println("A bit lower...");
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("================================");
        System.out.println("   Welcome to the Number Game   ");
        System.out.println("================================");
    }
}
