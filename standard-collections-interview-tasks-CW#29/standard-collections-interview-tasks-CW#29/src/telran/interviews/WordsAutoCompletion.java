package telran.interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordsAutoCompletion implements Words {
    
    private TrieNode root = new TrieNode();

    private static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        List<String> words = new ArrayList<>();
    }

    @Override
    public boolean addWord(String word) {
        TrieNode node = root;
//        System.out.println("Добавление слова: " + word);
        for (char ch : word.toLowerCase().toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
            if (!node.words.contains(word)) {
                node.words.add(word);
            }

        }
        if (!node.words.contains(word)) {
            node.words.add(word);
 //           System.out.println("Слово добавлено: " + word);
            return true;
        }
//        System.out.println("Слово уже существует: " + word);
        return false;
    }

    @Override
    public List<String> getWordsByPrefix(String prefix) {
        TrieNode node = root;
//        System.out.println("Поиск по префиксу: " + prefix);
        for (char ch : prefix.toLowerCase().toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
//                System.out.println("Ошибка: node становится null при поиске символа: " + ch);
                return Collections.emptyList();
            }
        }
//        System.out.println("Найденные слова: " + node.words);
        return Collections.unmodifiableList(node.words);
    }
}