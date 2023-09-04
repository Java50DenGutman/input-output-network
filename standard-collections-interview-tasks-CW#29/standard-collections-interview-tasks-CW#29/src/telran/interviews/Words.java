package telran.interviews;

import java.util.List;

public interface Words {
    boolean addWord(String word);
    List<String> getWordsByPrefix(String prefix);
}
