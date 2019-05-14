package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceInvaders;

public class GameWinScreen extends SpaceInvadersScreen  {
        BitmapFont bitmapFont;
        SpriteBatch batch;

    public GameWinScreen(SpaceInvaders si) {
        super(si);
    }

    @Override
        public void show() {
            bitmapFont = new BitmapFont();
            batch = new SpriteBatch();
        }

        @Override
        public void render(float delta) {
        batch.begin();
        bitmapFont.draw(batch, "YOU WIN", 270, 280);
        batch.end();

    }
}
