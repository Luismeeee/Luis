package Screen;

import bases.GameObject;
import bases.KeyEventPress;
import bases.Renderer;
import bases.Settings;

import java.awt.*;


public class backGroundWelcome extends GameObject {
    public backGroundWelcome(){
        renderer = new Renderer("assets/images/anh.png");
        position.set(Settings.GAME_WIDTH / 2, Settings.GAME_HEIGHT/2);
    }

    public Rectangle startButton = new Rectangle(340, 500, 80, 40);
    public Rectangle exstartButton = new Rectangle(440, 500, 150, 40);
    public Rectangle quitButton = new Rectangle(610, 500, 75, 40);
    @Override
    public void render(Graphics g){
        super.render(g);
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("TOUHOU GAME", 320, 100);

        Font fnt1 = new Font("Arial Bold", Font.ITALIC, 25);
        g.setFont(fnt1);
        g.drawString("Start", startButton.x + 12, startButton.y + 27);
        g2d.draw(startButton);
        g.drawString("Extra Start", exstartButton.x + 12, exstartButton.y + 27);
        g2d.draw(exstartButton);
        g.drawString("Quit", quitButton.x + 12, quitButton.y + 27);
        g2d.draw(quitButton);

        Font fnt2 = new Font("Arial Bold", Font.BOLD, 17);
        g.setFont(fnt2);
    }
    @Override
    public void run() {
        super.run();
        if(KeyEventPress.isSkey){
            SceneManager.signNewScreen(new PlayScreen());
        }

    }

}
