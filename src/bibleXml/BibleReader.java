package bibleXml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

public class BibleReader {

    public List<BibleBook> loadBible(String filePath) {
        List<BibleBook> bibleBooks = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList bookNodes = document.getElementsByTagName("BIBLEBOOK");

            for (int count = 0; count < bookNodes.getLength(); count++) {
                Element bookElement = (Element) bookNodes.item(count);
                String bookName = bookElement.getAttribute("Name");
                BibleBook book = new BibleBook(bookName);

                NodeList chapterNodes = bookElement.getElementsByTagName("CHAPTER");

                for (int counter = 0; counter < chapterNodes.getLength(); counter++) {
                    Element chapterElement = (Element) chapterNodes.item(counter);
                    int chapterNum = Integer.parseInt(chapterElement.getAttribute("Number"));
                    Chapter chapter = new Chapter(chapterNum);
                    NodeList verseNodes = chapterElement.getElementsByTagName("Verse");
                    for (int counts = 0; counts < verseNodes.getLength(); counts++) {
                        Element verseElement = (Element) verseNodes.item(counts);
                        int verseNum = Integer.parseInt(verseElement.getAttribute("Number"));
                        String verseText = verseElement.getTextContent().replaceAll("\\s+", " ").trim();
                        chapter.addVerse(new Verse(verseNum, verseText));
                    }

                    book.addChapter(chapter);
                }

                bibleBooks.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bibleBooks;
    }

        public void searchBible(String keyword, List<BibleBook> books) {
            keyword = keyword.trim();
            String lowerKeyword = keyword.toLowerCase();
            boolean found = false;

            for (int count = 0; count < books.size(); count++) {
                BibleBook book = books.get(count);

                for (int counter = 0; counter < book.getChapters().size(); counter++) {
                    Chapter chapter = book.getChapters().get(counter);

                    for (int counts = 0; counts < chapter.getVerses().size(); counts++) {
                        Verse verse = chapter.getVerses().get(counts);
                        String verseText = verse.getText().toLowerCase();
                        if (verseText.contains(lowerKeyword)) {
                            found = true;
                            System.out.println("Where you can find it: " + book.getName() + " " + chapter.getNumber() + ":" + verse.getNumber());
                            System.out.println("Here is your search: " + verse.getText());
                            System.out.println("--------------------------------------------------------");
                        }
                    }
                }
            }

            if (found == false) {
                System.out.println("No verse found containing: " + keyword);
            }
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BibleReader reader = new BibleReader();

        String filePath = "src/bibleXml/BibleKJV.xml";
        List<BibleBook> books = reader.loadBible(filePath);
        System.out.print("Enter a phrase to search: ");
        String keyword = scanner.nextLine();
        reader.searchBible(keyword, books);
    }
}
