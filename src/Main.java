void main() {

    ArrayList<Integer> numbers = new ArrayList<>();
    numbers.add(5);
    numbers.add(12);
    numbers.add(8);
    numbers.add(23);
    numbers.add(17);
    numbers.add(9);
    numbers.add(31);
    numbers.add(14);
    numbers.add(6);
    numbers.add(20);

    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    double average = (double) sum / numbers.size();

    IO.println("Сумма всех элементов: " + sum);
    IO.println("Среднее арифметическое: " + average);


    ArrayList<String> words = new ArrayList<>();
    words.add("солнце");
    words.add("компьютер");
    words.add("автомобиль");
    words.add("программирование");
    words.add("книга");
    words.add("телефон");
    words.add("велосипед");
    words.add("путешествие");
    words.add("море");
    words.add("дерево");

    String longest = words.getFirst();
    for (String word : words) {
        if (word.length() > longest.length()) {
            longest = word;
        }
    }

    IO.println("Самое длинное слово: " + longest);


    ArrayList<Character> chars = new ArrayList<>();
    chars.add('a');
    chars.add('b');
    chars.add('c');
    chars.add('b');
    chars.add('a');

    boolean isPalindrome = isPalindrome(chars);
    IO.println("Список является палиндромом: " + isPalindrome);
}


public static boolean isPalindrome(ArrayList<Character> list) {
    int left = 0;
    int right = list.size() - 1;

    while (left < right) {
        if (list.get(left) != list.get(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}