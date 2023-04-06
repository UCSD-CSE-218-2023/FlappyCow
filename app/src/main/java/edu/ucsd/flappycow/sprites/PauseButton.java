/**
 * The pauseButton
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.R;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.Util;

public class PauseButton extends Sprite {
    public PauseButton(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        this.bitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.pause_button);
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    /**
     * Sets the button in the right upper corner.
     */
    @Override
    public void move() {
        this.x = this.view.getWidth() - this.width;
        this.y = 0;
    }
}