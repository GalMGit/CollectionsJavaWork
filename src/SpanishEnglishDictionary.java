import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpanishEnglishDictionary {
    private static Map<String, WordEntry> dictionary = new HashMap<>();

    public static void main(String[] args) {
        SpanishEnglishDictionary dict = new SpanishEnglishDictionary();
        dict.run();
    }

    public void run() {
        initialDataEntry();

        System.out.println("=== НАЧАЛЬНЫЙ СЛОВАРЬ ===");
        showAll();

        manageTranslations("dog");
        manageTranslations("cat");

        manageWords();

        translateWord("dog");
        translateWord("dog");
        translateWord("cat");
        translateWord("house");
        translateWord("house");
        translateWord("house");
        translateWord("car");

        showTopPopular();
        showTopUnpopular();
    }

    public void initialDataEntry() {
        addWordToDictionary("hello", "hola");
        addWordToDictionary("dog", "perro");
        addWordToDictionary("cat", "gato");
        addWordToDictionary("house", "casa");
        addWordToDictionary("car", "coche");
        addWordToDictionary("blue", "azul");
        addWordToDictionary("red", "rojo");
        addWordToDictionary("big", "grande");
        addWordToDictionary("small", "pequeño");
        addWordToDictionary("good", "bueno");
        addWordToDictionary("bad", "malo");
        addWordToDictionary("eat", "comer");
        addWordToDictionary("drink", "beber");
        addWordToDictionary("sleep", "dormir");
    }

    private void addWordToDictionary(String word, String translation) {
        dictionary.put(word, new WordEntry(word, translation));
    }

    public void manageTranslations(String word) {
        if (!dictionary.containsKey(word)) {
            System.out.println("Слово не найдено: " + word);
            return;
        }

        System.out.println("\n=== УПРАВЛЕНИЕ ПЕРЕВОДОМ: " + word + " ===");
        System.out.println("Текущий перевод: " + dictionary.get(word).translation);

        replaceTranslation(word, "nuevo_" + dictionary.get(word).translation);

        deleteTranslation(word);
    }

    private void replaceTranslation(String word, String newTranslation) {
        System.out.println("Замена перевода для: " + word);
        dictionary.get(word).translation = newTranslation;
        System.out.println("Новый перевод: " + dictionary.get(word).translation);
    }

    private void deleteTranslation(String word) {
        System.out.println("Удаление слова: " + word);
        dictionary.remove(word);
        System.out.println("Слово удалено из словаря");
    }

    public void manageWords() {
        System.out.println("\n=== УПРАВЛЕНИЕ СЛОВАМИ ===");

        addWord("sun", "sol");
        addWord("moon", "luna");

        replaceWord("good", "excellent");

        deleteWord("bad");
    }

    private void addWord(String word, String translation) {
        if (dictionary.containsKey(word)) {
            System.out.println("Слово уже существует: " + word);
            return;
        }
        dictionary.put(word, new WordEntry(word, translation));
        System.out.println("Добавлено: " + word + " -> " + translation);
    }

    private void replaceWord(String oldWord, String newWord) {
        if (!dictionary.containsKey(oldWord)) {
            System.out.println("Слово не найдено: " + oldWord);
            return;
        }
        if (dictionary.containsKey(newWord)) {
            System.out.println("Новое слово уже существует: " + newWord);
            return;
        }
        WordEntry entry = dictionary.get(oldWord);
        dictionary.remove(oldWord);
        entry.word = newWord;
        dictionary.put(newWord, entry);
        System.out.println("Заменено: " + oldWord + " -> " + newWord);
    }

    private void deleteWord(String word) {
        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
            System.out.println("Удалено слово: " + word);
        } else {
            System.out.println("Слово не найдено: " + word);
        }
    }

    public void translateWord(String word) {
        if (dictionary.containsKey(word)) {
            dictionary.get(word).incrementPopularity();
            System.out.println(word + " -> " + dictionary.get(word).translation + " (популярность: " + dictionary.get(word).popularity + ")");
        } else {
            System.out.println("Перевод не найден для: " + word);
        }
    }

    public void showTopPopular() {
        System.out.println("\n=== ТОП-10 САМЫХ ПОПУЛЯРНЫХ СЛОВ ===");
        List<WordEntry> sorted = dictionary.values().stream()
                .sorted((a, b) -> Integer.compare(b.popularity, a.popularity))
                .limit(10)
                .collect(Collectors.toList());

        if (sorted.isEmpty()) {
            System.out.println("Словарь пуст");
            return;
        }

        System.out.printf("%-4s %-15s %-15s %-10s%n", "№", "Английский", "Испанский", "Популярность");
        for (int i = 0; i < sorted.size(); i++) {
            WordEntry entry = sorted.get(i);
            System.out.printf("%-4d %-15s %-15s %-10d%n",
                    i + 1, entry.word, entry.translation, entry.popularity);
        }
    }

    public void showTopUnpopular() {
        System.out.println("\n=== ТОП-10 САМЫХ НЕПОПУЛЯРНЫХ СЛОВ ===");
        List<WordEntry> sorted = dictionary.values().stream()
                .sorted(Comparator.comparingInt(a -> a.popularity))
                .limit(10)
                .collect(Collectors.toList());

        if (sorted.isEmpty()) {
            System.out.println("Словарь пуст");
            return;
        }

        System.out.printf("%-4s %-15s %-15s %-10s%n", "№", "Английский", "Испанский", "Популярность");
        for (int i = 0; i < sorted.size(); i++) {
            WordEntry entry = sorted.get(i);
            System.out.printf("%-4d %-15s %-15s %-10d%n",
                    i + 1, entry.word, entry.translation, entry.popularity);
        }
    }

    private void showAll() {
        for (WordEntry entry : dictionary.values()) {
            System.out.println(entry.word + " -> " + entry.translation);
        }
    }
}