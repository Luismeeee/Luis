package game;

import bases.BoxCollider;
import bases.GameObject;
import bases.Renderer;
import bases.Settings;

public class Platform extends GameObject {
    public Platform(int a, int b) {
        renderer = new Renderer("assets/images/platform/yellow_square.jpg");
        position.set(a, b);
        hitBox = new BoxCollider(this, Settings.PLATFORM_WIDTH + 2, Settings.PLATFORM_HEIGHT + 2);
    }
}
