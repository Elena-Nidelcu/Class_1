import snapshots.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class Main {
    private static final String folderPath = "E:\\labs\\secondLab\\src\\files";
    private static Map<String, FileSnapshot> fileSnapshots = new HashMap<>();


    public static void main(String[] args) {
        try {
            // Initialize file snapshots
            Date timeSnapshot = null;
            File folder = new File("E:\\labs\\secondLab\\src\\files");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;

            while (true) {
                File[] files = folder.listFiles();
                System.out.print("Enter command (commit/info <filename>/status): ");
                input = reader.readLine().trim();

                if (input.equals("commit")) {
                    // Commit action - update snapshots
                    timeSnapshot = new Date();
                    System.out.println("Snaphot commited at " + timeSnapshot);

                    updateSnapshots();
                } else if (input.startsWith("info ")) {
                    // Info action - display file information
                    String fileName = input.substring(5);

                    FileSnapshot snapshot = fileSnapshots.get(fileName);

                    if (fileSnapshots.get(fileName) == null) {
                        System.out.println("File not found.");
                    } else {
                        snapshot.printFileInfo();

                        if (fileName.endsWith(".txt")) {
                            TextFileSnapshot textSnapshot = new TextFileSnapshot(snapshot.getFilePath(), snapshot.getAttributes(), snapshot.getFileName());
                            textSnapshot.printExtraInfo();
                        }
                        else if (fileName.endsWith(".java") || fileName.endsWith(".py")) {
                            ProgramFileSnapshot programSnapshot = new ProgramFileSnapshot(snapshot.getFilePath(), snapshot.getAttributes(), snapshot.getFileName());
                            programSnapshot.printExtraInfo();
                        }
                        else if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                            ImageFileSnapshot imageSnapshot = new ImageFileSnapshot(snapshot.getFilePath(), snapshot.getAttributes(), snapshot.getFileName());
                            imageSnapshot.printExtraInfo();
                        }
                    }
                } else if (input.equals("status")) {
                    // Status action - check for changes
                    System.out.println("File status:");
                    for (File file : files) {
                        System.out.print("File: " + file.getName());
                        long snapshotTimeMillis = timeSnapshot.getTime();
                        long lastModifiedMillis = file.lastModified();
                        if (snapshotTimeMillis < lastModifiedMillis) System.out.println(": Changed");
                        else System.out.println(": No change");
                        System.out.println();
                    }
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
}
