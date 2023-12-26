package be.kdg.model;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class provides factory methods for creating Sailboat objects.
 * It includes methods for creating empty Sailboat objects, Sailboat objects with specific values, and random Sailboat objects.
 */
public class SailboatFactory {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SailboatFactory() {
    }

    /**
     * Creates a new instance of Sailboat with default values.
     *
     * @return a new Sailboat instance with default values
     */
    public static Sailboat newEmptySailboat() {
        return new Sailboat();
    }

    /**
     * Creates a new instance of Sailboat with the provided values.
     *
     * @param name           the name of the sailboat
     * @param harbour        the harbour where the sailboat is located
     * @param depth          the depth of the sailboat in meters
     * @param length         the length of the sailboat in feet
     * @param classification the classification of the sailboat
     * @param buildYear      the build year of the sailboat
     * @return a new Sailboat instance with the provided values
     */
    public static Sailboat newFilledSailboat(String name, String harbour, double depth, int length, Classification classification, LocalDate buildYear) {
        return new Sailboat(name, harbour, depth, length, classification, buildYear);
    }

    /**
     * Generates a random character from the given array.
     *
     * @param array the array of characters to choose from
     * @param r     the Random instance to use for generating the random index
     * @return a random character from the array
     */
    private static char getRandomChar(char[] array, Random r) {
        return array[r.nextInt(array.length)];
    }

    /**
     * Generates a random word of a given maximum length.
     *
     * @param maxWordLength the maximum length of the word
     * @param initCap       whether the word should start with a capital letter
     * @param r             the Random instance to use for generating the random characters
     * @return a random word
     */
    private static String generateWord(int maxWordLength, boolean initCap, Random r) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};

        StringBuilder sb = new StringBuilder();
        int wordLength = r.nextInt(maxWordLength) + 2;
        for (int j = 0; j < wordLength; j++) {
            char c;
            if (r.nextInt(3) == 0) { // 1/3 chance for a vowel
                c = getRandomChar(vowels, r);
            } else { // 2/3 chance for a consonant
                c = getRandomChar(consonants, r);
            }
            if (j == 0 && initCap) {
                c = Character.toUpperCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Generates a random string of a given maximum word length and word count.
     *
     * @param maxWordLength the maximum length of each word
     * @param wordCount     the maximum number of words
     * @param initCap       whether each word should start with a capital letter
     * @return a random string
     */
    private static String generateString(int maxWordLength, int wordCount, boolean initCap) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        int words = r.nextInt(wordCount) + 1;

        for (int i = 0; i < words; i++) {
            sb.append(generateWord(maxWordLength, initCap, r));
            sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * Generates a random LocalDate between 1980-01-01 and the current date.
     *
     * @return a random LocalDate
     */
    private static LocalDate generateDate() {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    /**
     * Creates a new instance of Sailboat with random values.
     *
     * @return a new Sailboat instance with random values
     */
    public static Sailboat newRandomSailboat() {
        Random r = new Random();
        Classification[] c = Classification.values();
        return new Sailboat(generateString(10, 1, true),
                generateString(15, 2, true),
                r.nextDouble(2.9) + 0.2,
                r.nextInt(51) + 20,
                c[r.nextInt(c.length)],
                generateDate());
    }
}
