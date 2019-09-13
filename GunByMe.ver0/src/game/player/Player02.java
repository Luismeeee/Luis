package game.player;

import bases.*;
import game.Platform;
import game.playerBullet.PlayerBullet2;
import tklibs.Mathx;

import java.awt.*;

public class Player02 extends GameObject {
    public int hp;
    public boolean immune;
    private final float GRAVITY = 0.4f;
    private final float JUMPSPEED = 10f;

    public Player02() {
        this(100, 100);
    }

    public Player02(int a, int b) {
        renderer = new Renderer("assets/images/players/straight");
        position.set(a, b);
        hitBox = new BoxCollider(this, Settings.PLAYER_WIDTH - 12, Settings.PLAYER_HEIGHT - 8);
        immune = false;
    }

    @Override
    public void run() {
        super.run();
        this.move();
        this.limitPosition();
        this.fire();
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
        moveDown();
        jump();
    }

    private boolean isJumping ;
    private boolean isTouchingGround;
    private boolean isTurnLeft;

    private void freeFall() {
        this.velocity.y += GRAVITY;
    }

    private void moveLeft() {
        if (isLeft) {
            position.x -= 3;
            isTurnLeft = true;
        }
    }

    private void moveRight() {
        if (isRight) {
            position.x += 3;
            isTurnLeft = false;
        }
    }

    private void moveDown() {
        if (isDown && isTouchingGround) {
            freeFall();
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
            if(!(currentIntersects != null && (int) this.hitBox.bot() > (int) currentIntersects.hitBox.top() )) {
                velocity.y = 0;
                isTouchingGround = true;
            }
        }
    }

    int frameCount = 0;

    public void fire() {
        // player fire
        frameCount++;
        int angleChange;
        if (isTurnLeft) {
            angleChange = -90;
        }
        else {
            angleChange = 90;
        }

        if (isFire && frameCount > 10) {
            int numberBullet = 2;

            double startX = position.x - 10;
            double endX = position.x + 10;
            double stepX = (endX - startX) / (numberBullet - 1);

            double startAngle = -90;
            double endAngle = -90;
            double stepAngle = (endAngle - startAngle) / (numberBullet - 1);

            for (int i = 0; i < numberBullet; i++) {
                PlayerBullet2 bullet = new PlayerBullet2();
                bullet.position.set(startX + (stepX * i),position.y);
                bullet.velocity.setAngle(Math.toRadians(startAngle + (stepAngle * i) + angleChange));
            }
            frameCount = 0;
        }
    }

    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;
    public boolean isFire;
    public void keyMove() {
        isUp = KeyEventPress.isUpPress2;
        isDown = KeyEventPress.isDownPress2;
        isLeft = KeyEventPress.isLeftPress2;
        isRight = KeyEventPress.isRightPress2;
        isFire = KeyEventPress.isFirePress2;
    }
}
