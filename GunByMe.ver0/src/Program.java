import bases.GamePanel;
import bases.KeyEventPress;
import bases.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Program {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setTitle("GunByMe");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_X){
                    KeyEventPress.isSkey = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    KeyEventPress.ESCkey = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    KeyEventPress.isLeftPress2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    KeyEventPress.isUpPress2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    KeyEventPress.isRightPress2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    KeyEventPress.isDownPress2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    KeyEventPress.isFirePress2 = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    KeyEventPress.ESCkey = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_X){
                    KeyEventPress.isSkey  = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    KeyEventPress.isLeftPress2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    KeyEventPress.isUpPress2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    KeyEventPress.isRightPress2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    KeyEventPress.isDownPress2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    KeyEventPress.isFirePress2 = false;
                }
            }
        });

        GamePanel panel = new GamePanel();
        window.add(panel);
        panel.setPreferredSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT)); // kich thuoc panel mong muon
        window.pack(); // mo rong cua so cho vua panel

        window.setVisible(true);
        panel.gameLoop();
    }
}
