package bases;

public class Background extends GameObject {
    public Background() {
        renderer = new Renderer("assets/images/background/rEtiNc.png");
        position.set(0, 0);
//        position.set(0, Settings.GAME_HEIGHT-Settings.BACKGROUND_HEIGHT);
//        velocity.set(0, 1);
        anchor.set(0, 0);
    }

    @Override
    public void run() {
        super.run(); //position.add(velocity.x, velocity.y);

        if (position.y >= 0) {
            position.y = 0;
        }

    }
}
