package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void setNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void testToString() {
        String newSentence = "New Sentence.";
        sut.setNewSentence(newSentence);
        String expected = "New Sentence. 0";
        assertEquals(expected, sut.toString());
    }

    @Test
    void testEquals() {
        String sentence1 = "This is a Sentence.";
        String sentence2 = "This is a Sentence.";

        CustomFileReader sut2 = new CustomFileReader("readMe1.txt");

        sut.setNewSentence(sentence1);
        sut2.setNewSentence(sentence2);

        boolean result = sut.equals(sut2);
        assertTrue(result);
    }

    @Test
    void testNotEqualsDifferentClass() {
        boolean result = sut.equals(new Object());
        assertFalse(result);
    }

    @Test
    void testNotEqualsDifferentSentence() {
        String sentence1 = "This is a Sentence.";

        sut.setNewSentence(sentence1);
        CustomFileReader sut2 = new CustomFileReader("readMe2.txt");
        sut2.setNewSentence(sentence1);

        boolean result = sut.equals(sut2);

        assertFalse(result);
    }
}