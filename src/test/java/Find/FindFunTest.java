package Find;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class FindTest {
    Find finder = new Find();

    @Test
    void testOne() throws IOException {
        Set<String> listOfNames = Set.of("first.txt", "second.txt", "third.txt", "fourth.txt", "fifth.txt", "sixth.txt", "seventh.txt", "eighth.txt");
        assertEquals(finder.search("./input", List.of("first.txt", "second.txt", "third.txt", "fourth.txt", "fifth.txt", "sixth.txt", "seventh.txt", "eighth.txt"), true), listOfNames);

    }

    @Test
    void testTwo() throws IOException {
        Set<String> listOfNames = Set.of("fourth.txt", "third.txt");
        assertEquals(finder.search("./input/step1", List.of("first.txt", "third.txt", "second.txt", "fourth.txt"), false), listOfNames);

    }

    @Test
    void testThree() throws IOException {
        Set<String> listOfNames = Set.of("first.txt", "second.txt", "eighth.txt");
        assertEquals(finder.search("./input", List.of("first.txt", "15.txt", "second.txt", "eighth.txt"), true), listOfNames);

    }

    @Test
    void testFour() throws IOException {
        Set<String> listOfNames = Set.of("eighth.txt");
        assertEquals(finder.search("./input/step1/step2/step3", List.of("first.txt", "third.txt", "fifth.txt", "eighth.txt"), true), listOfNames);
    }

    @Test
    void testFive() throws IOException {
        Set<String> listOfNames = Set.of("first.txt", "second.txt");
        assertEquals(finder.search("./input", List.of("first.txt", "15.txt", "second.txt", "eighth.txt"), false), listOfNames);

    }
}