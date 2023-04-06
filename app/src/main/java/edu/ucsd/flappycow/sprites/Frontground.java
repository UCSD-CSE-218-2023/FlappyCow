/**
 * Manages the bitmap at the front
 * 
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import com.quchen.flappycow.R;
import edu.ucsd.flappycow.Util;

import android.graphics.Bitmap;

public class Frontground extends Background {
    /**
     * Height of the ground relative to the height of the bitmap
     */
    public static final float GROUND_HEIGHT = (1f * /*45*/ 35) / 720;

    /**
     * Static bitmap to reduce memory usage.
     */
    public static Bitmap globalBitmap;
    
    public Frontground(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if(globalBitmap == null){
            globalBitmap = Util.getDownScaledBitmapAlpha8(gameActivity, R.drawable.fg);
        }
        this.bitmap = globalBitmap;
    }
    
}
