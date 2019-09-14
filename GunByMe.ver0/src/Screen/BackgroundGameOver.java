package Screen;

import bases.GameObject;
import bases.KeyEventPress;
import bases.Renderer;
import bases.Settings;

import java.awt.*;

public class BackgroundGameOver extends GameObject {
    public BackgroundGameOver(){
        renderer = new Renderer("assets/images/gameover.png");
        position.set(Settings.GAME_WIDTH / 2, Settings.GAME_HEIGHT/2);
    }
    public Rectangle backButton = new Rectangle(400, 500, 250, 40);

    @Override
    public void render(Graphics g){
        super.render(g);
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.BLACK);
        g.drawString("You Failed", 390, 100);

        Font fnt1 = new Font("Arial Bold", Font.ITALIC, 25);
        g.setFont(fnt1);
        g.drawString("Back to Menu (ESC)", backButton.x + 12, backButton.y + 27);
        g2d.draw(backButton);
    }
    int framecount = 0;
    @Override
    public void run() {
        super.run();
        framecount++;
        if(KeyEventPress.ESCkey && framecount >100){
            SceneManager.signNewScreen(new WelcomeScreen());
        }
    }
}
