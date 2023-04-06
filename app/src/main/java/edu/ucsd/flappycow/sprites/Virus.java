package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import com.quchen.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Virus extends PowerUp {
    public static Bitmap globalBitmap;

    public Virus(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if(globalBitmap == null){
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.virus);
        }
        this.bitmap = globalBitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    /**
     * When eaten the player will become infected.
     */
    @Override
    public void onCollision() {
        super.onCollision();
        gameActivity.decreasePoints();
        view.changeToSick();
    }
}
