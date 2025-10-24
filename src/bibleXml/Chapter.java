package bibleXml;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private int number;
    private List<Verse> verses;

    public Chapter(int number) {
        this.number = number;
        this.verses = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public void addVerse(Verse verse) {
        verses.add(verse);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Chapter " + number);
        for (Verse verse : verses) {
            stringBuilder.append(verse).append("\n");
        }
        return stringBuilder.toString();
    }
}
