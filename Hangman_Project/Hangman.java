import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String[] words = {"ant", "baboon", "badger", "bat", "bear","beaver", "camel",
        "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer", "dog", "donkey",
        "duck", "eagle", "ferret", "fox", "frog", "goat", "goose", "hawk", "lion",
        "lizard", "llama", "mole", "monkey", "moose", "mouse", "mule", "newt",
        "otter", "owl", "panda", "parrot", "pigeon", "python", "rabbit", "ram",
        "rat", "raven","rhino", "salmon", "seal", "shark", "sheep", "skunk", "sloth",
        "snake", "spider", "stork", "swan", "tiger", "toad", "trout",
        "turkey","turtle", "weasel", "whale", "wolf", "wombat", "zebra"};

        String chosenWord = words[generateRandom(words)];

        char[] charChosenWord = chosenWord.toCharArray();

        guessWord(charChosenWord);
    }

    public static void gallows(int numberOfTries) {
        String[] gallows = {
            "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +   //if the user didn't miss any guesses.
            "    |\n" +
            "    |\n" +
            "=========\n",
           
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +   //if the user missed one guess.
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",
             
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +    //if the user missed two guesses.
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",
            
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +   //if the user missed three guesses.
            "/|   |\n" +
            "     |\n" +
            "     |\n" +
            " =========\n",
            
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n"+   //if the user missed four guesses.
            "     |\n" +
            "     |\n" +
            " =========\n",
            
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +  //if the user missed five guesses.
            "/    |\n" +
            "     |\n" +
            " =========\n",
            
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +   //if the user missed six guesses.
            "/ \\  |\n" +
            "     |\n" +
            " =========\n"};

            System.out.println(gallows[numberOfTries]);
        
    }

    public static int generateRandom(String[] words) {
        return (int) (Math.random() * words.length);
    }

    public static char[] populateChar(char character, char[] array) {
        char[] wordArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            wordArray[i] = character;
        }
        return wordArray;
    }

    public static void printArray(char[] array) {
        for (char c : array) {
            System.out.print(c + " ");
        }
        System.out.print("\n");
    }

    public static void guessWord(char[] charChosenWord) {
        char[] wordArray = new char[charChosenWord.length];
        wordArray = populateChar('_', wordArray);

        char[] missedLetter = new char[charChosenWord.length];
        missedLetter = populateChar(' ', wordArray);

        int numberOfTries = 0;

        int idxMissedLetter = 0;

        while (numberOfTries != charChosenWord.length) {
            System.out.print("Word:\t");
            printArray(wordArray);

            System.out.print("\nMisses:\t");
            printArray(missedLetter);

            System.out.print("\nGuess:\t");
            String letter = scan.next();

            System.out.print("\n");

            while (letter.equals(" ")) {

                System.out.println("Please choose a letter");
                letter = scan.next();
            }

            char letterChar = letter.charAt(0);

            boolean findIT = false;

            for (int i = 0; i < charChosenWord.length; i++) {
                if (charChosenWord[i] == letterChar) {
                    wordArray[i] = letterChar;
                    findIT = true;
                }
            }

            if (!findIT) {

                missedLetter[idxMissedLetter] = letterChar;
                idxMissedLetter++;

                numberOfTries++;
            }

            if (Arrays.equals(wordArray, charChosenWord)) {
                System.out.print("Word:\t");
                printArray(wordArray);
                gallows(numberOfTries);

                System.out.println("GOOD JOB. YOU GUESSED THE WORD");
                System.exit(0);
            }

            gallows(numberOfTries);

        }

        if (numberOfTries == charChosenWord.length) {
            System.out.println("You have lost the game");
            System.exit(0);
        }

    }
}