package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.Words;
import telran.interviews.WordsAutoCompletion;

public class WordsTest {
    String words[]= {"abcdef", "ab123", "aaa", "ab", "ablmn", "abbbb", "a", "ABd", "bbb", "B12", "*/"};
    String wordsStartB[] = {"bbb","B12"};
    String wordsStartAB[] = {"abcdef", "ab123", "ab", "ablmn", "abbbb", "ABd"};
    String wordsStartABC[] = {"abcdef"};
    String wordsStartAsteric[] = {"*/"};
    Words autoCompletion;

    @BeforeEach
    void setUp() {
        autoCompletion = new WordsAutoCompletion();
        for (String word : words) {
            autoCompletion.addWord(word);
        }
    }

    @Test
    void test() {
        assertArrayEquals(wordsStartABC, autoCompletion.getWordsByPrefix("abc").toArray(String[]::new));
        assertArrayEquals(wordsStartB, autoCompletion.getWordsByPrefix("B").toArray(String[]::new));
        assertArrayEquals(wordsStartAB, autoCompletion.getWordsByPrefix("ab").toArray(String[]::new));
        assertArrayEquals(wordsStartAsteric, autoCompletion.getWordsByPrefix("*").toArray(String[]::new));
    }
}