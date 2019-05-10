package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;

public class Ship {

    enum State {
        IDLE, LEFT, RIGHT, SHOOT, DYING, DEAD
    }

    Vector2 position;

    State state;
    float stateTime;
    float speed = 5;
    int lives = 4;

    TextureRegion frame, heart, over;
    Weapon weapon;

    Ship(int initialPosition){
        position = new Vector2(initialPosition, 10);
        state = State.IDLE;
        stateTime = 0;

        weapon = new Weapon();
    }


    void setFrame(Assets assets){

        switch (state){
            case IDLE:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
            case DYING:
                frame = assets.navedamaged.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                frame = assets.naveleft.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                frame = assets.naveright.getKeyFrame(stateTime, true);
                break;
            case SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            default:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
        }

        switch (lives){
            case 4:
                heart = assets.heartcontainer.getKeyFrame(0, true);
                break;
            case 3:
                heart = assets.heartcontainer.getKeyFrame(1, true);
                break;
            case 2:

                heart = assets.heartcontainer.getKeyFrame(2, true);
                break;
            case 1:
                heart = assets.heartcontainer.getKeyFrame(3, true);
                break;
            case 0:
                heart = assets.heartcontainer.getKeyFrame(4, true);
                break;
        }

    }

    void render(SpriteBatch batch){
        batch.draw(frame, position.x, position.y);

        weapon.render(batch);

        batch.draw(heart, 25, 220);
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;

        System.out.println(state);
        switch (state){
            case IDLE:
            case RIGHT:
            case LEFT:
            case SHOOT:
                if (Controls.isLeftPressed()) {
                    setState(State.LEFT);
                    moveLeft();
                } else if (Controls.isRightPressed()) {
                    setState(State.RIGHT);
                    moveRight();
                } else {
                    setState(State.IDLE);
                }

                if (Controls.isShootPressed()) {
                    setState(State.SHOOT);
                    shoot();
                    assets.shootSound.play();
                }
                if (lives == 0) {
                    setState(State.DYING);
                }
                break;
            case DYING:
                if(assets.navedamaged.isAnimationFinished(stateTime)){
                    setState(State.DEAD);
                }
                break;
        }
        setFrame(assets);
        weapon.update(delta, assets);
    }

    private void setState(State state) {
        this.state = state;
        stateTime = 0;
    }


    void moveLeft(){
        position.x -= speed;
    }

    void moveRight(){
        position.x += speed;
    }

    void shoot(){
        weapon.shoot(position.x +16);
    }

    void damage() {
        lives -= 1;
    }
}
