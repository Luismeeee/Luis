package bases;

import game.Platform;
import game.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Player player;
    Background background;
    Platform platform;

    public GamePanel() {
        background = new Background();
        player = new Player();
        for (int i = 0; i < 5; i++) {
            platform = new Platform(50 + 30*i, 402);
        }
        for (int i = 0; i < 5; i++) {
            platform = new Platform(150 + 30*i, 500);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        GameObject.renderAll(g);

        g.setColor(Color.BLACK);
        g.fillRect(Settings.BACKGROUND_WIDTH,0,Settings.GAME_WIDTH - Settings.BACKGROUND_WIDTH,Settings.GAME_HEIGHT);
    }

    public void gameLoop() {
        long lastTime = 0;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastTime >= 1000/60) {
                // render + logic
                repaint(); // >> call to paint()
                runAll();
                lastTime = currentTime;
            }
        }
    }

    public void runAll() {
        GameObject.runAll();
    }
}
