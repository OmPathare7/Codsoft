package task1;

import java.util.Random;
import java.util.Scanner;

public class numberGuess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain;
        do {
            int score = 0;
            int maxAttempts = 7;
            int attempts = 0;
            int numberToGuess = random.nextInt(100) + 1;
            boolean isGuessed = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Guess a number between 1 and 100. You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts && !isGuessed) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess > numberToGuess) {
                    System.out.println("Too high, try again.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low, try again.");
                } else {
                    System.out.println("Yes, you guessed the number!");
                    isGuessed = true;
                }
            }

            if (!isGuessed) {
                System.out.println("Sorry, you've used all your attempts. The number was: " + numberToGuess);
            } else {
                score = maxAttempts - attempts + 1; // Score based on remaining attempts
                System.out.println("Your score: " + score);
            }

            System.out.print("Do you want to play again? (true/false): ");
            playAgain = scanner.nextBoolean();

        } while (playAgain);

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
