/**
 * A spider with web
 * 
 * BTW Spiders have 8 eyes.
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

public class Spider extends Sprite {
    
    /**
     * Static bitmap to reduce memory usage.
     */
    public static Bitmap globalBitmap;

    public Spider(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if(globalBitmap == null){
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.spider_full);
        }
        this.bitmap = globalBitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }
    
    /**
     * Sets the position
     * @param x
     * @param y
     */
    public void init(int x, int y){
        this.x = x;
        this.y = y;
    }

}
