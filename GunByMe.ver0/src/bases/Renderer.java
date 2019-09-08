package bases;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Renderer {
    // 1. single image
    public BufferedImage image;
    // 2. animation
    public ArrayList<BufferedImage> images;
    public int currentIndex;
    public int delayFrame;
    public boolean isOnce;

    public Renderer(String url) {
        this(url, 6, false);
    }

    public Renderer(String url, int delayFrame) {
        this(url, delayFrame, false);
    }

    public Renderer(String url, int delayFrame, boolean isOnce) {
        File file = new File(url);
        if(file.isFile()) {
            image = SpriteUtils.loadImage(url);
        }
        if (file.isDirectory()) {
            images = SpriteUtils.loadImages(url);
            currentIndex = 0;
            this.delayFrame = delayFrame;
            this.isOnce = isOnce;
        }
    }

    int frameCount = 0;
    public void render(Graphics g, GameObject master) {
        if (image != null) {
            drawImage(g, image, master);
        }

        if (images != null && images.size() > 0) {
            frameCount++;
            BufferedImage currentImage = images.get(currentIndex);
            drawImage(g, currentImage, master);
            if (frameCount > delayFrame) {
                currentIndex++;
                if (currentIndex >= images.size()) {
                    currentIndex = 0;
                    if (isOnce) {
                        master.deactive();
                    }
                }
                frameCount = 0;
            }
        }

    }

    private void drawImage(Graphics g, BufferedImage image, GameObject master) {
        g.drawImage(
                image,
                (int) (master.position.x - master.anchor.x * image.getWidth()),
                (int) (master.position.y - master.anchor.y * image.getHeight()),
                null);

        // only for dev code
        // 1. mark position
        g.setColor(Color.RED);
        g.fillOval(
                (int) master.position.x - 1,
                (int) master.position.y - 1,
                3,
                3);
        // 2. draw hitBox if can
        if (master.hitBox != null) {
            g.setColor(Color.GREEN);
            g.drawRect((int) master.hitBox.left(), (int) master.hitBox.top(), master.hitBox.width, master.hitBox.height);
        }
    }
}
