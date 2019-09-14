package Screen;

import bases.GameObject;

public class GameOverScreen extends Screen {
    @Override
    public void init() {
        new BackgroundGameOver();
    }

    @Override
    public void clear() {
        GameObject.clear();
    }
}
