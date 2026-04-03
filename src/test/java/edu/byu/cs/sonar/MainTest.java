package edu.byu.cs.sonar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void testMain() {
        String[] args={"readMe1.txt", "readMe2.txt", "readMe3.txt", "5"};

        assertDoesNotThrow(() -> Main.main(args));

    }

    @ParameterizedTest
    @CsvSource({
            "bad1.txt, bad2.txt, bad3.txt, 5",
            "missing.txt, file.txt, file2.txt, 3"
    })
    void testMain_FileErrors(String f1, String f2, String f3, int count) {
        ByteArrayOutputStream errContent=new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Main.main(new String[]{f1, f2, f3, String.valueOf(count)});

        assertTrue(errContent.toString().contains("Did not find any dictionary file"));
    }
}
