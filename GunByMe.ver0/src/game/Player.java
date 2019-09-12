package game;

import bases.*;
import tklibs.Mathx;

import java.awt.*;

public class Player extends GameObject {
    public int hp;
    public boolean immune;
    private final float GRAVITY = 0.4f;
    private final float JUMPSPEED = 10f;

    public Player() {
        this(100, 100);
    }

    public Player(int a, int b) {
        renderer = new Renderer("assets/images/players/straight");
        position.set(a, b);
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
        position.y = Mathx.clamp(position.y, halfHeightPlayer, Settings.GAME_HEIGHT - halfHeightPlayer + 50);
    }

    private void move() {
        // player move
        isTouchingGround = false;
        keyMove();
        freeFall();
        moveVertical();
        moveLeft();
        moveRight();
        jump();
    }

    private boolean isJumping ;
    private boolean isTouchingGround;

    private void freeFall() {
        this.velocity.y += GRAVITY;
    }
    private void moveLeft() {
        if (isLeft) {
            position.x -= 3;
        }
    }
    private void moveRight() {
        if (isRight) {
            position.x += 3;
        }
    }

    private void jump() {
        if (isUp && isTouchingGround) {
            isJumping = true;
            velocity.y -= JUMPSPEED;
        }
        else isJumping = false;
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
                this.position.add(0, Math.signum(velocity.y));
            }
            Platform currentIntersects = GameObject.findIntersects(Platform.class, this);
//            if(!(currentIntersects != null && new Double(this.hitBox.bot()).intValue() > new Double(currentIntersects.hitBox.top()).intValue())) {
//                velocity.y = 0;
//                isTouchingGround = true;
//            }
            if(!(currentIntersects != null && (int) this.hitBox.bot() > (int) currentIntersects.hitBox.top() )) {
                velocity.y = 0;
                isTouchingGround = true;
            }
        }
    }

    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;
    public void keyMove() {
        isUp = KeyEventPress.isUpPress;
        isDown = KeyEventPress.isDownPress;
        isLeft = KeyEventPress.isLeftPress;
        isRight = KeyEventPress.isRightPress;
    }
}
