package snapshots;

import java.nio.file.Path;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSnapshot {
    protected Path filePath;
    protected BasicFileAttributes attributes;
    protected FileTime snapshotTime;
    protected String fileName;

    public FileSnapshot(Path filePath, BasicFileAttributes attributes, String fileName) {
        this.filePath = filePath;
        this.attributes = attributes;
        this.snapshotTime = FileTime.from(Instant.now());
        this.fileName = fileName;
    }

    public void printFileInfo() {
        System.out.println("File: " + filePath.getFileName());
        System.out.println("Created: " + formatTime(attributes.creationTime()));
        System.out.println("Last Modified: " + formatTime(attributes.lastModifiedTime()));

        if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
            System.out.println("File Type: Image");
        } else if (fileName.endsWith(".txt")) {
            System.out.println("File Type: Text");
        } else if (fileName.endsWith(".java") || fileName.endsWith(".py")) {
            System.out.println("File Type: Program");
        } else {
            System.out.println("File Type: Other");
        }
    }

    public String getStatus() {
        FileTime currentTime = FileTime.from(Instant.now());
        String changeStatus = attributes.lastModifiedTime().compareTo(snapshotTime) < 0 ? "No change" : "Changed";
        return changeStatus;
    }

    public FileTime getSnapshotTime() {
        return snapshotTime;
    }

    public String getFileName() {
        return fileName;
    }

    private String formatTime(FileTime fileTime) {
        ZonedDateTime localTime = fileTime.toInstant().atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return formatter.format(localTime);
    }


    public static FileSnapshot[] createSnapshots(String folderPath) {
        List<FileSnapshot> snapshots = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : directoryStream) {
                BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
                snapshots.add(new FileSnapshot(filePath, attr, filePath.getFileName().toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snapshots.toArray(new FileSnapshot[0]);
    }
}