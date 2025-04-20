import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        final int MAX_ATTEMPTS = 7;
        final int RANGE_MIN = 1;
        final int RANGE_MAX = 100;

        int roundsWon = 0;
        boolean playAgain = true;

        System.out.println("=== Welcome to the Number Guessing Game! ===");

        while (playAgain) {
            int targetNumber = random.nextInt(RANGE_MAX - RANGE_MIN + 1) + RANGE_MIN;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\nI have chosen a number between " + RANGE_MIN + " and " + RANGE_MAX + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attemptsLeft--;

                if (guess == targetNumber) {
                    System.out.println("🎉 Correct! You guessed the number.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The number was: " + targetNumber);
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nThanks for playing! You won " + roundsWon + " round(s).");
        scanner.close();
    }
}
