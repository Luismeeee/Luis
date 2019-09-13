package game.playerBullet;

import bases.BoxCollider;
import bases.GameObject;
import bases.Renderer;
import bases.Settings;
import game.player.Player02;

public class PlayerBullet extends GameObject {
    public PlayerBullet() {
        renderer = new Renderer("assets/images/player-bullets/a", 1);
        velocity.set(0,-5);
        hitBox = new BoxCollider(this, Settings.PLAYER_BULLET_WIDTH - 8, Settings.PLAYER_BULLET_HEIGHT - 8);
    }

    @Override
    public void run() {
        super.run();
        this.checkPlayer();
    }

    private void checkPlayer() {
        Player02 player02 = GameObject.findIntersects(Player02.class, this);
        if (player02 != null && player2InBound(player02)) {
            this.deactive();
            player02.deactive();
        }
    }

    public boolean player2InBound(Player02 others) {
        return others.position.x > -Settings.PLAYER_WIDTH * others.anchor.x
                && others.position.y > -Settings.PLATFORM_HEIGHT * others.anchor.y;
    }
}
