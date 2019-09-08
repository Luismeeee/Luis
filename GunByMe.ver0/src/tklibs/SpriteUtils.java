package tklibs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by huynq on 5/11/17.
 */
public class SpriteUtils {

    public static BufferedImage loadImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<BufferedImage> loadImages(String url) {
        ArrayList<BufferedImage> listImages = new ArrayList<>();

        File folder = new File(url);
        if(folder.exists() && folder.isDirectory()) {
            String[] fileNames = folder.list();
            Arrays.sort(fileNames);
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = fileNames[i];
                if (fileName.toLowerCase().endsWith(".png")) {
                    BufferedImage image = loadImage(url + "/" + fileName);
                    listImages.add(image);
                }
            }
        }

        return listImages;
    }

    public static void renderAtCenter(Graphics graphics, BufferedImage image, double x, double y) {
        graphics.drawImage(image, (int)(x - (double)image.getWidth() / 2), (int)(y - (double) image.getHeight() / 2), null);
    }

    public static BufferedImage maskWhite(BufferedImage image) {
        BufferedImage returnImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                int alpha = color & 0xFF000000;
                if (alpha != 0) {
                    returnImage.setRGB(x, y, color | 0x00FFFFFF | alpha);
                } else {
                    returnImage.setRGB(x, y, color);
                }
            }
        }

        return returnImage;
    }

    public static void main(String[] args) {
        String fileUrl = "assets/images/players/straight/0.png";
        String folderUrl = "assets/images/players/straight";

        File file = new File(fileUrl);
        File folder = new File(folderUrl);

        System.out.println("file exists " + file.exists());
        System.out.println("folder exists " + folder.exists());

        System.out.println(file.isFile());
        System.out.println(folder.isDirectory());

        if(folder.exists() && folder.isDirectory()) {
            String[] fileNames = folder.list();
            Arrays.sort(fileNames);
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = fileNames[i];
                System.out.println("file name  " + fileName);
            }
        }
    }
}
