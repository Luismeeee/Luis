package Screen;

import bases.GameObject;

public class WelcomeScreen extends Screen {
    @Override
    public void init() {
        new backGroundWelcome();
    }



    @Override
    public void clear() {
        GameObject.clear();
    }
}
