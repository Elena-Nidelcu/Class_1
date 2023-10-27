import snapshots.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String folderPath = "C:/Users/ASUS/Desktop/UTM/HW-2/secondLab/src/files"; // Replace with your folder path
    private static Map<String, FileSnapshot> fileSnapshots = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Initialize file snapshots
            updateSnapshots();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;

            while (true) {
                System.out.print("Enter command (commit/info <filename>/status): ");
                input = reader.readLine().trim();

                if (input.equals("commit")) {
                    // Commit action - update snapshots
                    updateSnapshots();
                    for (FileSnapshot snapshot : fileSnapshots.values()) {
                        System.out.println(snapshot.getFileName() + " - Snapshot Time: " + snapshot.getSnapshotTime());
                    }
                    System.out.println("Snapshot committed.");
                } else if (input.startsWith("info ")) {
                    // Info action - display file information
                    String fileName = input.substring(5);
                    FileSnapshot snapshot = fileSnapshots.get(fileName);

                    if (snapshot == null) {
                        System.out.println("File not found.");
                    } else {
                        snapshot.printFileInfo();

                        if (snapshot instanceof TextFileSnapshot) {
                            TextFileSnapshot textSnapshot = (TextFileSnapshot) snapshot;
                            int wordCount = textSnapshot.countWords();
                            int characterCount = textSnapshot.countCharacters();
                            int lineCountText = textSnapshot.countLines();

                            System.out.println("Word Count: " + wordCount);
                            System.out.println("Character Count: " + characterCount);
                            System.out.println("Line Count (Text): " + lineCountText);
                        } else if (snapshot instanceof ProgramFileSnapshot) {
                            ProgramFileSnapshot programSnapshot = (ProgramFileSnapshot) snapshot;
                            int lineCountProgram = programSnapshot.countLines();
                            int classCount = programSnapshot.countClasses();
                            int methodCount = programSnapshot.countMethods();

                            System.out.println("Line Count (Program): " + lineCountProgram);
                            System.out.println("Class Count: " + classCount);
                            System.out.println("Method Count: " + methodCount);
                        } else if (snapshot instanceof ImageFileSnapshot) {
                            ImageFileSnapshot imageSnapshot = (ImageFileSnapshot) snapshot;
                            imageSnapshot.displayImageSize();
                        }
                    }
                } else if (input.equals("status")) {
                    // Status action - check for changes
                    checkStatus();
                } else {
                    System.out.println("Invalid command. Available commands: commit, info <filename>, status");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateSnapshots() {
        fileSnapshots.clear();

        FileSnapshot[] snapshots = FileSnapshot.createSnapshots(folderPath);
        for (FileSnapshot snapshot : snapshots) {
            fileSnapshots.put(snapshot.getFileName(), snapshot);
        }
    }

    private static void checkStatus() {
        for (FileSnapshot snapshot : fileSnapshots.values()) {
            System.out.println(snapshot.getFileName() + " - " + snapshot.getStatus());
        }
    }
}
