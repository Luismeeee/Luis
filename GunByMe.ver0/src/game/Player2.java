package game;

import bases.KeyEventPress;

public class Player2 extends Player {
    @Override
    public void keyMove() {
        isUp = KeyEventPress.isUpPress2;
        isDown = KeyEventPress.isDownPress2;
        isLeft = KeyEventPress.isLeftPress2;
        isRight = KeyEventPress.isRightPress2;
    }
}

