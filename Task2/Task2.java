import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 3;     
        int maxAttempts = 7;     
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("Rules:");
        System.out.println("1. The computer will generate a number between 1 and 100.");
        System.out.println("2. You have " + maxAttempts + " attempts to guess it in each round.");
        System.out.println("3. Points will be awarded based on how quickly you guess.\n");

        for (int round = 1; round <= totalRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("----- Round " + round + " -----");

            while (attemptsUsed < maxAttempts) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attemptsUsed++;

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    int points = (maxAttempts - attemptsUsed + 1) * 10; // fewer attempts → more points
                    totalScore += points;
                    System.out.println(" Correct! You guessed the number in " + attemptsUsed + " attempts.");
                    System.out.println("You earned " + points + " points this round.\n");
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" Out of attempts! The number was: " + numberToGuess + "\n");
            }
        }

        System.out.println(" Game Over! Your total score: " + totalScore);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
