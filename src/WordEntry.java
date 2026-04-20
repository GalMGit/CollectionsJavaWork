class WordEntry {
    String word;
    String translation;
    int popularity;

    public WordEntry(String word, String translation) {
        this.word = word;
        this.translation = translation;
        this.popularity = 0;
    }

    public void incrementPopularity() {
        this.popularity++;
    }
}