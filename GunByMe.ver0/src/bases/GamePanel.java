package bases;

import Screen.PlayScreen;
import Screen.SceneManager;
import Screen.WelcomeScreen;
import game.Platform;
import game.player.Player01;
import game.player.Player02;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Player01 player01;
    Player02 player02;
    Background background;
    Platform platform;



    public GamePanel() {
        SceneManager.signNewScreen(new WelcomeScreen());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        GameObject.renderAll(g);

        if (SceneManager.currentScreen instanceof PlayScreen){
            g.setColor(Color.black);
            g.fillRect(Settings.BACKGROUND_WIDTH,0, Settings.GAME_WIDTH - Settings.BACKGROUND_WIDTH,600);}
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
