package snapshots;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProgramFileSnapshot extends FileSnapshot {
    public ProgramFileSnapshot(Path filePath, BasicFileAttributes attributes, String fileName) {
        super(filePath, attributes, fileName);
    }

        @Override
        public void printExtraInfo() {
            int lineCountProgram = countLines();
            int classCount = countClasses();
            int methodCount = countMethods();

            System.out.println("Line Count (Program): " + lineCountProgram);
            System.out.println("Class Count: " + classCount);
            System.out.println("Method Count: " + methodCount);
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

    public int countClasses() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            int classCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("class ") || line.trim().startsWith("public class ")) {
                    classCount++;
                }
            }

            return classCount;
        } catch (IOException e) {
            System.err.println("Error counting classes: " + e.getMessage());
            return -1;
        }
    }

    public int countMethods() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            int methodCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("public") && line.contains("(") && line.contains(")")) {
                    methodCount++;
                }
            }

            return methodCount;
        } catch (IOException e) {
            System.err.println("Error counting methods: " + e.getMessage());
            return -1;
        }
    }
}