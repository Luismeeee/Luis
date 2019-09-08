package game;

import bases.*;
import tklibs.Mathx;

import java.awt.*;

public class Player extends GameObject {
    public int hp;
    public boolean immune;
    private final float GRAVITY = 0.2f;

    public Player() {
        renderer = new Renderer("assets/images/players/straight");
        position.set(100, 100);
        hitBox = new BoxCollider(this, Settings.PLAYER_WIDTH - 12, Settings.PLAYER_HEIGHT - 8);
        immune = false;
    }

    int frameCount = 0;
    @Override
    public void run() {
        super.run();
        this.move();
        this.limitPosition();
        this.checkImmune();
    }

    @Override
    public void render(Graphics g) {
        if (immune) {
            if (immuneCount % 2 == 0) {
                super.render(g);
            }
        } else {
            super.render(g);
        }
    }

    int immuneCount = 0;
    private void checkImmune() {
        if (immune) {
            immuneCount++;
            if(immuneCount > 120) {
                immune = false;
                immuneCount = 0;
            }
        }
    }

    private void limitPosition() {
        // player limit
        int halfWidthPlayer = Settings.PLAYER_WIDTH / 2;
        int halfHeightPlayer = Settings.PLAYER_HEIGHT / 2;
        position.x = Mathx.clamp(position.x, halfWidthPlayer, Settings.BACKGROUND_WIDTH - halfWidthPlayer);
        position.y = Mathx.clamp(position.y, halfHeightPlayer, Settings.GAME_HEIGHT - halfHeightPlayer);
    }

    private void move() {
        // player move
        freeFall();
        moveVertical();
        moveLeft();
        moveRight();
        moveUp();
//        moveDown();
    }

    private void moveLeft() {
        if (KeyEventPress.isLeftPress) {
            position.x -= 3;
        }
    }
    private void moveRight() {
        if (KeyEventPress.isRightPress) {
            position.x += 3;
        }
    }
    private void moveUp() {
        if (KeyEventPress.isUpPress) {
            position.y -= 10;
            freeFall();
        }
    }
    private void moveDown() {
        if (KeyEventPress.isDownPress) {
            position.y += 3;
        }
    }

    private void freeFall() {
        this.velocity.y += GRAVITY;
    }

    private void moveVertical() {
        Vector2D nextPosition = this.screenPosition.clone();
        nextPosition.add(0, velocity.y);
        BoxCollider nextHitBox = new BoxCollider(
                nextPosition.x,
                nextPosition.y,
                this.hitBox.width,
                this.hitBox.height
        );

        Platform platform = GameObject.findHitBoxIntersects(nextHitBox);
        if (platform != null) {
            while(!this.hitBox.intersects(platform.hitBox)) {
                this.position.add(0, 1);
            }
            Platform currentIntersects = GameObject.findIntersects(Platform.class, this);
            if(!(currentIntersects != null && new Double(this.hitBox.bot()).intValue() > new Double(currentIntersects.hitBox.top()).intValue())) {
                velocity.y = 0;
            }
        }
    }
}
