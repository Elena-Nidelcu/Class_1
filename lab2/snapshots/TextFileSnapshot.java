package snapshots;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileSnapshot extends FileSnapshot {
    public TextFileSnapshot(Path filePath, BasicFileAttributes attributes, String fileName) {
        super(filePath, attributes, fileName);
    }

    @Override
    public void printExtraInfo() {
        int wordCount = countWords();
        int characterCount = countCharacters();
        int lineCountText = countLines();

        System.out.println("Word Count: " + wordCount);
        System.out.println("Character Count: " + characterCount);
        System.out.println("Line Count (Text): " + lineCountText);
    }

    public int countWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            String line;
            int wordCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }

            return wordCount;
        } catch (IOException e) {
            System.err.println("Error counting words: " + e.getMessage());
            return -1;
        }
    }

    public int countCharacters() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            int characterCount = 0;
            int c;

            while ((c = reader.read()) != -1) {
                characterCount++;
            }

            return characterCount;
        } catch (IOException e) {
            System.err.println("Error counting characters: " + e.getMessage());
            return -1;
        }
    }

    public int countLines() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            int lineCount = 0;

            while (reader.readLine() != null) {
                lineCount++;
            }

            return lineCount;
        } catch (IOException e) {
            System.err.println("Error counting lines: " + e.getMessage());
            return -1;
        }
    }
}