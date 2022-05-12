package Find;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FindTest {
    Find finder = new Find();

    public static Set<String> splitter(String name) {
        return Set.of(name.split("\n"));
    }

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    void fileInsteadOfDirectory() {
        assertThrows(IllegalArgumentException.class,
                () -> finder.search("pom.xml",
                        List.of("first.txt", "second.txt", "third.txt", "fourth.txt", "fifth.txt", "sixth.txt", "seventh.txt", "eighth.txt"), true));
    }

    @Test
    void nonExistedDirectory() {
        assertThrows(FileNotFoundException.class,
                () -> finder.search("./inputT",
                        List.of("first.txt", "second.txt", "third.txt", "fourth.txt", "fifth.txt", "sixth.txt", "seventh.txt", "eighth.txt"), true));
    }

    @Test
    public void allFiles() throws IOException {
        finder.search("./input", List.of("first.txt", "second.txt", "third.txt", "fourth.txt", "fifth.txt", "sixth.txt", "seventh.txt", "eighth.txt"), true);
        String names = "first.txt\nsecond.txt\nfourth.txt\nthird.txt\nfifth.txt\nsixth.txt\neighth.txt\nseventh.txt\n";
        Assertions.assertEquals(splitter(names), splitter(output.toString()));
    }

    @Test
    void allFilesInCertainDir() throws IOException {
        finder.search("./input/step1", List.of("first.txt", "third.txt", "second.txt", "fourth.txt"), false);
        String names = "fourth.txt\nthird.txt\n";
        Assertions.assertEquals(splitter(names), splitter(output.toString()));
    }

    @Test
    void searchWithWrongFile() throws IOException {
        finder.search("./input", List.of("first.txt", "15.txt", "second.txt", "eighth.txt"), true);
        String names = "first.txt\nsecond.txt\neighth.txt\n";
        Assertions.assertEquals(splitter(names), splitter(output.toString()));
    }

    @Test
    void filesFromOtherDirs() throws IOException {
        finder.search("./input/step1/step2/step3", List.of("first.txt", "third.txt", "fifth.txt", "eighth.txt"), true);
        String names = "eighth.txt\n";
        Assertions.assertEquals(splitter(names), splitter(output.toString()));
    }

    @Test
    void emptyResult() throws IOException {
        finder.search("./input/step1/step2/step3", List.of("10.txt"), true);
        String names = "";
        Assertions.assertEquals(splitter(names), splitter(output.toString()));
    }
}