package game.playerBullet;

import bases.BoxCollider;
import bases.GameObject;
import bases.Renderer;
import bases.Settings;
import game.player.Player01;

public class PlayerBullet2 extends GameObject {
    public PlayerBullet2() {
        renderer = new Renderer("assets/images/player-bullets/a", 1);
        velocity.set(0,-10);
        hitBox = new BoxCollider(this, Settings.PLAYER_BULLET_WIDTH - 8, Settings.PLAYER_BULLET_HEIGHT - 8);
    }

    @Override
    public void run() {
        super.run();
        this.checkPlayer();
    }

    private void checkPlayer() {
        Player01 player01 = GameObject.findIntersects(Player01.class, this);
        if (player01 != null && player2InBound(player01)) {
            this.deactive();
            player01.deactive();
        }
    }

    public boolean player2InBound(Player01 others) {
        return others.position.x > -Settings.PLAYER_WIDTH * others.anchor.x
                && others.position.y > -Settings.PLATFORM_HEIGHT * others.anchor.y;
    }
}
