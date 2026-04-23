package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Entry point and complete console implementation of a Hangman game.
 *
 * <p>This class contains the full game flow, word selection, hidden word state management,
 * input validation, win detection, loss detection, score tracking, and restart logic
 * for a single-player console-based Hangman game.</p>
 *
 * <p>Even though the entire solution is contained in a single class as requested,
 * the code is organized into small, focused methods to improve readability,
 * maintainability, and testability.</p>
 *
 * <p>Game rules:</p>
 * <ul>
 *   <li>A secret word is randomly selected from a predefined list.</li>
 *   <li>The word is shown as underscores, one per letter.</li>
 *   <li>The player guesses one letter per turn.</li>
 *   <li>Correct guesses reveal the letter in all matching positions.</li>
 *   <li>Wrong guesses add a body part to the hangman drawing.</li>
 *   <li>The player wins by revealing all letters before 6 wrong guesses.</li>
 *   <li>The player loses when 6 wrong guesses are accumulated.</li>
 * </ul>
 *
 * <p>Console messages are displayed in Spanish, while source code and Javadoc
 * are written in English.</p>
 *
 * @version 1.0
 * @since 17
 */
public class Main {

    /** Maximum number of wrong guesses allowed before losing. */
    private static final int MAX_WRONG_GUESSES = 6;

    /** Predefined pool of words to pick from. */
    private static final String[] WORD_LIST = {
        "JAVA", "PROGRAMACION", "COMPUTADORA", "ALGORITMO", "VARIABLE",
        "METODO", "CLASE", "OBJETO", "HERENCIA", "INTERFAZ",
        "COMPILADOR", "DEPURADOR", "CONSOLA", "BUCLE", "CONDICION",
        "ARREGLO", "CADENA", "ENTERO", "LOGICO", "FUNCION"
    };

    /** Scanner used to read user input from the console. */
    private static final Scanner SCANNER = new Scanner(System.in);

    /** Random instance used for word selection. */
    private static final Random RANDOM = new Random();

    /** The secret word for the current match, stored in uppercase. */
    private static String secretWord;

    /** The visible state of the word, showing revealed letters and underscores. */
    private static char[] visibleWord;

    /** List of letters the player has already guessed in the current match. */
    private static List<Character> usedLetters;

    /** Number of wrong guesses in the current match. */
    private static int wrongGuesses;

    /** Accumulated win counter across all matches. */
    private static int wins = 0;

    /** Accumulated loss counter across all matches. */
    private static int losses = 0;

    /**
     * Application entry point.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args) {
        printWelcomeMessage();
        boolean keepPlaying = true;

        while (keepPlaying) {
            startNewGame();
            playSingleMatch();
            printScoreboard();
            keepPlaying = askIfUserWantsToPlayAgain();
        }

        printFarewellMessage();
        SCANNER.close();
    }

    /**
     * Prints the welcome banner and basic rules.
     */
    private static void printWelcomeMessage() {
        System.out.println("==========================================");
        System.out.println("    BIENVENIDO AL JUEGO DEL AHORCADO      ");
        System.out.println("==========================================");
        System.out.println("Reglas básicas:");
        System.out.println("- Adivina la palabra secreta letra por letra.");
        System.out.println("- Tienes un máximo de " + MAX_WRONG_GUESSES + " errores.");
        System.out.println("- Cada error dibuja una parte del ahorcado.");
        System.out.println("- Ganas si descubres la palabra antes de agotar los intentos.");
        System.out.println();
    }

    /**
     * Initializes a new match by selecting a word and resetting all match state.
     */
    private static void startNewGame() {
        secretWord = selectRandomWord();
        initializeVisibleWord();
        usedLetters = new ArrayList<>();
        wrongGuesses = 0;
        System.out.println("¡Nueva partida iniciada! La palabra tiene " + secretWord.length() + " letras.");
        System.out.println();
    }

    /**
     * Selects a random word from the word list and returns it in uppercase.
     *
     * @return a randomly chosen word in uppercase
     */
    private static String selectRandomWord() {
        int index = RANDOM.nextInt(WORD_LIST.length);
        return WORD_LIST[index].toUpperCase();
    }

    /**
     * Initializes the visible word array with underscores, one per letter.
     */
    private static void initializeVisibleWord() {
        visibleWord = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            visibleWord[i] = '_';
        }
    }

    /**
     * Runs the main loop for a single match until the player wins or loses.
     */
    private static void playSingleMatch() {
        while (true) {
            printHangman();
            printVisibleWord();
            printUsedLetters();

            if (hasPlayerWon()) {
                System.out.println("¡Felicitaciones! ¡Adivinaste la palabra: " + secretWord + "!");
                wins++;
                break;
            }

            if (hasPlayerLost()) {
                printHangman();
                System.out.println("¡Perdiste! La palabra era: " + secretWord);
                losses++;
                break;
            }

            char letter = readValidLetter();
            processLetter(letter);
        }
    }

    /**
     * Prints the hangman ASCII drawing based on the current number of wrong guesses.
     */
    private static void printHangman() {
        System.out.println();
        System.out.println("  +---+");
        System.out.println("  |   |");
        System.out.println((wrongGuesses >= 1 ? "  O   |" : "      |"));
        System.out.println((wrongGuesses == 2 ? "  |   |" : wrongGuesses == 3 ? " /|   |" : wrongGuesses >= 4 ? " /|\\  |" : "      |"));
        System.out.println((wrongGuesses == 5 ? " /    |" : wrongGuesses >= 6 ? " / \\  |" : "      |"));
        System.out.println("      |");
        System.out.println("=========");
        System.out.println();
    }

    /**
     * Prints the current state of the visible word with spaces between characters.
     */
    private static void printVisibleWord() {
        System.out.print("Palabra: ");
        for (int i = 0; i < visibleWord.length; i++) {
            System.out.print(visibleWord[i]);
            if (i < visibleWord.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * Prints the list of letters already used in the current match.
     */
    private static void printUsedLetters() {
        System.out.println("Letras usadas: " + usedLetters);
        System.out.println("Errores: " + wrongGuesses + " / " + MAX_WRONG_GUESSES);
        System.out.println();
    }

    /**
     * Reads and returns a valid letter from the player.
     *
     * <p>Keeps prompting until the input is a single alphabetic character
     * that has not been used in the current match.</p>
     *
     * @return the validated uppercase letter entered by the player
     */
    private static char readValidLetter() {
        while (true) {
            System.out.print("Ingresa una letra: ");
            String input = SCANNER.nextLine().trim().toUpperCase();

            if (!isValidLetter(input)) {
                System.out.println("Entrada inválida. Debes ingresar una única letra (A-Z).");
                continue;
            }

            char letter = input.charAt(0);

            if (isLetterAlreadyUsed(letter)) {
                System.out.println("La letra '" + letter + "' ya fue usada. Intenta con otra.");
                continue;
            }

            return letter;
        }
    }

    /**
     * Validates that the input is exactly one alphabetic character.
     *
     * @param input the raw string entered by the user
     * @return {@code true} if the input is a single letter; otherwise {@code false}
     */
    private static boolean isValidLetter(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    /**
     * Checks whether the given letter has already been guessed in this match.
     *
     * @param letter the letter to check
     * @return {@code true} if the letter is in the used list; otherwise {@code false}
     */
    private static boolean isLetterAlreadyUsed(char letter) {
        return usedLetters.contains(letter);
    }

    /**
     * Processes a guessed letter: registers it, checks if it belongs to the word,
     * and updates visible word or wrong guess counter accordingly.
     *
     * @param letter the validated letter guessed by the player
     */
    private static void processLetter(char letter) {
        usedLetters.add(letter);

        if (secretWord.indexOf(letter) >= 0) {
            updateVisibleWord(letter);
            System.out.println("¡Correcto! La letra '" + letter + "' está en la palabra.");
        } else {
            wrongGuesses++;
            System.out.println("Incorrecto. La letra '" + letter + "' no está en la palabra.");
        }

        System.out.println();
    }

    /**
     * Reveals all positions in the visible word where the given letter appears.
     *
     * @param letter the correctly guessed letter
     */
    private static void updateVisibleWord(char letter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                visibleWord[i] = letter;
            }
        }
    }

    /**
     * Determines whether the player has won by checking if all letters are revealed.
     *
     * @return {@code true} if no underscores remain in the visible word; otherwise {@code false}
     */
    private static boolean hasPlayerWon() {
        for (char c : visibleWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the player has lost by reaching the maximum wrong guesses.
     *
     * @return {@code true} if wrong guesses equal the maximum allowed; otherwise {@code false}
     */
    private static boolean hasPlayerLost() {
        return wrongGuesses >= MAX_WRONG_GUESSES;
    }

    /**
     * Prints the accumulated scoreboard showing wins and losses.
     */
    private static void printScoreboard() {
        System.out.println();
        System.out.println("Marcador acumulado:");
        System.out.println("Victorias: " + wins);
        System.out.println("Derrotas : " + losses);
        System.out.println();
    }

    /**
     * Asks the player whether they want to start a new match.
     *
     * @return {@code true} if the player wants to continue; otherwise {@code false}
     */
    private static boolean askIfUserWantsToPlayAgain() {
        while (true) {
            System.out.print("¿Deseas jugar otra partida? (S/N): ");
            String answer = SCANNER.nextLine().trim().toUpperCase();

            if ("S".equals(answer)) {
                System.out.println();
                return true;
            }

            if ("N".equals(answer)) {
                return false;
            }

            System.out.println("Respuesta inválida. Ingresa S para sí o N para no.");
        }
    }

    /**
     * Prints the farewell message before closing the application.
     */
    private static void printFarewellMessage() {
        System.out.println();
        System.out.println("Gracias por jugar El Ahorcado.");
        System.out.println("Programa finalizado.");
    }
}
