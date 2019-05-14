package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {

    Ship ship;
    AlienArmy alienArmy;
    BitmapFont bitmapFont;

    HUD(Ship ship, AlienArmy alienArmy){
        this.ship = ship;
        this.alienArmy = alienArmy;
        bitmapFont = new BitmapFont();
    }

    void render(SpriteBatch batch){
        bitmapFont.draw(batch, "Score: " + ship.score, 300, 50);
        bitmapFont.draw(batch, "Level: " + alienArmy.levelArmy, 300, 20);

    }
}