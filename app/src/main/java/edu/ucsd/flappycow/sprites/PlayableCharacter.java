/**
 * The SuperClass of every character that is controlled by the player
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class PlayableCharacter extends Sprite {

    protected boolean isDead = false;

    public PlayableCharacter(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        move();
    }

    /**
     * Calls super.move
     * Moves the character to 1/6 of the horizontal screen
     * Manages the speed changes -> Falling
     */
    @Override
    public void move() {
        this.x = this.view.getWidth() / 6;

        if (speedY < 0) {
            // The character is moving up
            speedY = speedY * 2 / 3 + getSpeedTimeDecrease() / 2;
        } else {
            // the character is moving down
            this.speedY += getSpeedTimeDecrease();
        }

        if (this.speedY > getMaxSpeed()) {
            // speed limit
            this.speedY = getMaxSpeed();
        }

        super.move();
    }

    /**
     * A dead character falls slowly to the ground.
     */
    public void dead() {
        this.isDead = true;
        this.speedY = getMaxSpeed() / 2;
    }

    /**
     * Let the character flap up.
     */
    public void onTap() {
        this.speedY = getTabSpeed();
        this.y += getPosTabIncrease();
    }

    /**
     * Falling speed limit
     *
     * @return
     */
    protected float getMaxSpeed() {
        // 25 @ 720x1280 px
        return view.getHeight() / 51.2f;
    }

    /**
     * Every run cycle the speed towards the ground will increase.
     *
     * @return
     */
    protected float getSpeedTimeDecrease() {
        // 4 @ 720x1280 px
        return view.getHeight() / 320;
    }

    /**
     * The character gets this speed when taped.
     *
     * @return
     */
    protected float getTabSpeed() {
        // -80 @ 720x1280 px
        return -view.getHeight() / 16f;
    }

    /**
     * The character jumps up the pixel height of this value.
     *
     * @return
     */
    protected int getPosTabIncrease() {
        // -12 @ 720x1280 px
        return -view.getHeight() / 100;
    }

    public void revive() {
        this.isDead = false;
        this.row = 0;
    }

    public void upgradeBitmap(int points) {
        // Change bitmap, maybe when a certain amount of point is reached.
    }

    public void wearMask() {
        // Change bitmap to have a mask.
    }

    public boolean isDead() {
        return this.isDead;
    }
}
