/**
 * An obstacle: spider + logHead
 * 
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import com.quchen.flappycow.R;

import android.graphics.Canvas;

public class Obstacle extends Sprite{
    private Spider spider;
    private WoodLog log;
    
    private static int collideSound = -1;
    private static int passSound = -1;
    
    /** Necessary so the onPass method is just called once */
    public boolean isAlreadyPassed = false;

    public Obstacle(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        spider = new Spider(view, gameActivity);
        log = new WoodLog(view, gameActivity);
        
        if(collideSound == -1){
            collideSound = GameActivity.soundPool.load(gameActivity, R.raw.crash, 1);
        }
        if(passSound == -1){
            passSound = GameActivity.soundPool.load(gameActivity, R.raw.pass, 1);
        }
        
        initPos();
    }
    
    /**
     * Creates a spider and a wooden log at the right of the screen.
     * With a certain gap between them.
     * The vertical position is in a certain area random.
     */
    private void initPos(){
        int height = gameActivity.getResources().getDisplayMetrics().heightPixels;
        int gab = height / 4 - view.getSpeedX();
        if(gab < height / 5){
            gab = height / 5;
        }
        int random = (int) (Math.random() * height * 2 / 5);
        int y1 = (height / 10) + random - spider.height;
        int y2 = (height / 10) + random + gab;
        
        spider.init(gameActivity.getResources().getDisplayMetrics().widthPixels, y1);
        log.init(gameActivity.getResources().getDisplayMetrics().widthPixels, y2);
    }

    /**
     * Draws spider and log.
     */
    @Override
    public void draw(Canvas canvas) {
        spider.draw(canvas);
        log.draw(canvas);
    }

    /**
     * Checks whether both, spider and log, are out of range.
     */
    @Override
    public boolean isOutOfRange() {
        return spider.isOutOfRange() && log.isOutOfRange();
    }

    /**
     * Checks whether the spider or the log is colliding with the sprite.
     */
    @Override
    public boolean isColliding(Sprite sprite) {
        return spider.isColliding(sprite) || log.isColliding(sprite);
    }

    /**
     * Moves both, spider and log.
     */
    @Override
    public void move() {
        spider.move();
        log.move();
    }

    /**
     * Sets the speed of the spider and the log.
     */
    @Override
    public void setSpeedX(float speedX) {
        spider.setSpeedX(speedX);
        log.setSpeedX(speedX);
    }
    
    /**
     * Checks whether the spider and the log are passed.
     */
    @Override
    public boolean isPassed(){
        return spider.isPassed() && log.isPassed();
    }

    private static final int SOUND_VOLUME_DIVIDER = 3;
    
    /**
     * Will call obstaclePassed of the game, if this is the first pass of this obstacle.
     */
    public void onPass(){
        if(!isAlreadyPassed){
            isAlreadyPassed = true;
            view.getGameActivity().increasePoints();
            GameActivity.soundPool.play(passSound, MainActivity.volume/SOUND_VOLUME_DIVIDER, MainActivity.volume/SOUND_VOLUME_DIVIDER, 0, 0, 1);
        }
    }

    @Override
    public void onCollision() {
        super.onCollision();
        GameActivity.soundPool.play(collideSound, MainActivity.volume/SOUND_VOLUME_DIVIDER, MainActivity.volume/SOUND_VOLUME_DIVIDER, 0, 0, 1);
    }

}
