/*
 * Main Activity / Splashscreen with buttons.
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package com.quchen.flappycow;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

    /**
     * Name of the SharedPreference that saves the medals
     */
    public static final String MEDAL_SAVE = "medal_save";

    /**
     * Key that saves the medal
     */
    public static final String MEDAL_KEY = "medal_key";

    public static final float DEFAULT_VOLUME = 0.3f;

    /**
     * Volume for sound and music
     */
    public static float volume = DEFAULT_VOLUME;

    private StartScreenView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new StartScreenView(this);
        setContentView(view);
        setSocket();
    }

    public void muteToggle() {
        if (volume != 0) {
            volume = 0;
            view.setSpeaker(false);
        } else {
            volume = DEFAULT_VOLUME;
            view.setSpeaker(true);
        }
        view.invalidate();
    }

    /**
     * Fills the socket with the medals that have already been collected.
     */
    private void setSocket() {
        SharedPreferences saves = this.getSharedPreferences(MEDAL_SAVE, 0);
        view.setSocket(saves.getInt(MEDAL_KEY, 0));
        view.invalidate();
    }

    /**
     * Updates the socket for the medals.
     */
    @Override
    protected void onResume() {
        super.onResume();
        setSocket();
    }
}
