package bases;

import game.Platform;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {

    public static ArrayList<GameObject> listObjects = new ArrayList<>();

    public static void runAll() {
        for (int i = 0; i < listObjects.size(); i++) {
            GameObject object = listObjects.get(i);
            if (object.active) {
                object.run();
            }
        }
    }

    public static void renderAll(Graphics g) {
        for (int i = 0; i < listObjects.size(); i++) {
            GameObject object = listObjects.get(i);
            if(object.active) {
                object.render(g);
            }
        }
    }

    public static Platform findPlatformIntersects(GameObject source) {
        for (int i = 0; i < listObjects.size(); i++) {
            GameObject object = listObjects.get(i);
            if (object.active
                    && object instanceof Platform
                    && object.hitBox != null
                    && object.hitBox.intersects(source.hitBox)) {
                return (Platform) object;
            }
        }
        return null;
    }

    // E ~ Player | Enemy | PlayerBullet ...
    // cls ~ Player.class | Enemy.class | PlayerBullet.class
    public static <E> E findIntersects(Class<E> cls, GameObject source) {
        for (int i = 0; i < listObjects.size(); i++) {
            GameObject object = listObjects.get(i);
            if (object.active
                    && cls.isAssignableFrom(object.getClass())
                    && object.hitBox != null
                    && object.hitBox.intersects(source.hitBox)) {
                return (E) object;
            }
        }
        return null;
    }

    public static Platform findHitBoxIntersects(BoxCollider source) {
        for (int i = 0; i < listObjects.size(); i++) {
            GameObject object = listObjects.get(i);
            if (object.active
                    && object instanceof Platform
                    && object.hitBox != null
                    && object.hitBox.intersects(source)) {
                return (Platform) object;
            }
        }
        return null;
    }

    public Renderer renderer;
    public Vector2D position;
    public Vector2D velocity;
    public boolean active;
    public BoxCollider hitBox; // null
    public Vector2D anchor;
    public Vector2D screenPosition;

    public GameObject() {
        listObjects.add(this);
        position = new Vector2D(); // (0, 0)
        velocity = new Vector2D(); // (0, 0)
        active = true;
        anchor = new Vector2D(0.5,0.5);
        screenPosition = new Vector2D();
    }

    public void render(Graphics g) {
        if(renderer != null) {
            renderer.render(g, this);
        }
    }

    public void run() {
        position.add(velocity.x, velocity.y);
        screenPosition = this.position;
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }
}
