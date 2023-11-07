package snapshots;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageFileSnapshot extends FileSnapshot {
    public ImageFileSnapshot(Path filePath, BasicFileAttributes attributes, String fileName) {
        super(filePath, attributes, fileName);
    }

    @Override
    public void printExtraInfo() {
        try {
            BufferedImage image = ImageIO.read(filePath.toFile());
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Image Size: " + width + "x" + height + " pixels");
        } catch (IOException e) {
            System.err.println("Error reading image: " + e.getMessage());
        }
    }

    public void displayImageSize() {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
            try {
                BufferedImage image = ImageIO.read(filePath.toFile());
                int width = image.getWidth();
                int height = image.getHeight();
                System.out.println("Image Size: " + width + "x" + height + " pixels");
            } catch (IOException e) {
                System.err.println("Error reading image: " + e.getMessage());
            }
        } else {
            System.out.println("File is not an image.");
        }
    }
}