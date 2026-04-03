package edu.byu.cs.sonar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is a class that will handle the file reading
 */
class CustomFileReader {
    /**
     * The scanner that will read the dictionary
     */
    private final String path;

    /**
     * The sentence that will be constructed
     */
    private String newSentence;
    /**
     * The number of words read in by the scanner
     */
    private int count;

    /**
     * Constructor for our class
     *
     * @param fileName the file to be read in
     */
    CustomFileReader(final String fileName) {
        path = fileName;
        newSentence = "";
        count = 0;
    }

    /**
     * This will find how many words are in the text file provided
     *
     * @return how many words in the file
     */
    int howManyWordsInFile() throws FileNotFoundException {
        try (Scanner s = createScanner()) {
            while (s.hasNext()) {
                s.next();
                count++;
            }
        }
        return count;
    }

    /**
     * This will return the word at the given index in the text file
     *
     * @param index which number word should be taken back
     * @return correct word
     */
    String returnThatWord(final int index) throws FileNotFoundException {
        String returnWord = "";
        try (Scanner s = createScanner()) {
            for (int i=0; i < index; i++) {
                returnWord=s.next();
            }
        }
        return returnWord;
    }

    /**
     * This will search for a word that contains the same letter as the
     * one provided in the argument and return that word
     *
     * @param letter eventually will be the character we are looking for in the word
     */
    void findNewWord(final CharSequence letter) throws FileNotFoundException {
        String word;

        try (Scanner s = createScanner()) {
            word=s.next();

            while (!word.contains(letter)) {
                word=s.next();
            }
        }
            newSentence=newSentence + word + " ";
    }

    /**
     * This is a standard getter
     *
     * @return the sentence created by the reader
     */
    String getNewSentence() {
        return newSentence;
    }

    /**
     * This is standard setter
     *
     * @param betterSentence The new sentence for the reader
     */
    void setNewSentence(final String betterSentence) {
        newSentence = betterSentence;
    }

    private Scanner createScanner() throws FileNotFoundException {
        return new Scanner(new InputStreamReader(
                new FileInputStream(path), StandardCharsets.UTF_8));
    }

    /**
     * Returns information: of the sentence this reader holds,
     * as well as the number of words in the dictionary from
     * which this reader reads.
     *
     * @return String for this specific instance of a reader
     */
    @Override
    public String toString() {
        return newSentence + " " + count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomFileReader that=(CustomFileReader) o;
        return count == that.count && Objects.equals(path, that.path) && Objects.equals(newSentence, that.newSentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, newSentence, count);
    }
}
