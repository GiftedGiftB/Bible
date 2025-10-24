package bibleXml;

import java.util.ArrayList;
import java.util.List;

public class BibleBook {
    private String name;
    private List<Chapter> chapters;

    public BibleBook(String name) {
        this.name = name;
        this.chapters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name);
        for (Chapter chapter : chapters) {
            stringBuilder.append(chapter).append("\n");
        }
        return stringBuilder.toString();
    }
}
