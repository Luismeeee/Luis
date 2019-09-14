package Screen;

import bases.Background;
import bases.GameObject;
import game.Platform;
import game.player.Player;
import game.player.Player01;
import game.player.Player02;

public class PlayScreen extends Screen {

    @Override
    public void init() {
        new Background();
        new Player();
        new Player01();
        new Player02();
        for (int i = 0; i < 5; i++) {
            new Platform(50 + 30*i, 402);
        }
        for (int i = 0; i < 5; i++) {
            new Platform(150 + 30*i, 500);
        }
        for (int i = 0; i < 25; i++) {
            new Platform(300  + 30*i, 400);
        }
    }

    @Override
    public void clear() {
        GameObject.clear();
    }
}
