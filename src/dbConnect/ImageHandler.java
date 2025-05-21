package dbConnect;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageHandler {
    private static String currentImagePath = "";

    public static void chooseImage(JLabel imageLabel) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Ensure folder exists
                String folderPath = "src/usersImages/";
                File directory = new File(folderPath);
                if (!directory.exists()) {
                    directory.mkdirs(); // create folder if it doesn't exist
                }

                String destinationPath = folderPath + selectedFile.getName();
                Path destination = Paths.get(destinationPath);

                if (Files.exists(destination)) {
                    JOptionPane.showMessageDialog(null, "File already exists! Rename or choose another.");
                } else {
                    Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                    imageLabel.setIcon(resizeImage(destinationPath, imageLabel));
                    currentImagePath = destinationPath; // optionally store path for use later
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Image copy failed: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public static boolean fileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static ImageIcon resizeImage(String imagePath, JLabel label) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage();
            int width = label.getWidth();
            int height = getHeightFromWidth(imagePath, width);
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            int originalWidth = img.getWidth();
            int originalHeight = img.getHeight();
            return (int) ((double) desiredWidth / originalWidth * originalHeight);
        } catch (IOException e) {
            e.printStackTrace();
            return desiredWidth; // fallback to square if error
        }
    }
}
