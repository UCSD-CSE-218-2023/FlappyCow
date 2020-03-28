/**
 * The dialog shown when the game is over
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package com.quchen.flappycow;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverDialog extends Dialog {
    public static final int REVIVE_PRICE = 5;

    /**
     * Name of the SharedPreference that saves the score
     */
    public static final String score_save_name = "score_save";

    /**
     * Key that saves the score
     */
    public static final String best_score_key = "score";

    /**
     * The game that invokes this dialog
     */
    private GameActivity gameActivity;

    private TextView tvCurrentScoreVal;
    private TextView tvBestScoreVal;

    public GameOverDialog(GameActivity gameActivity) {
        super(gameActivity);
        this.gameActivity = gameActivity;
        this.setContentView(R.layout.gameover);
        this.setCancelable(false);

        tvCurrentScoreVal = (TextView) findViewById(R.id.tv_current_score_value);
        tvBestScoreVal = (TextView) findViewById(R.id.tv_best_score_value);
    }

    public void init() {
        Button okButton = (Button) findViewById(R.id.b_ok);
        okButton.setOnClickListener(view -> {
            saveCoins();
            if (gameActivity.numberOfRevive <= 1) {
                gameActivity.accomplishmentBox.save(gameActivity);
            }

            dismiss();
            gameActivity.finish();
        });

        Button reviveButton = (Button) findViewById(R.id.b_revive);
        reviveButton.setText(gameActivity.getResources().getString(R.string.revive_button)
                + " " + REVIVE_PRICE * gameActivity.numberOfRevive + " "
                + gameActivity.getResources().getString(R.string.coins));
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                gameActivity.coins -= REVIVE_PRICE * gameActivity.numberOfRevive;
                saveCoins();
                gameActivity.view.revive();
            }
        });
        if (gameActivity.coins < REVIVE_PRICE * gameActivity.numberOfRevive) {
            reviveButton.setClickable(false);
        } else {
            reviveButton.setClickable(true);
        }

        manageScore();
        manageMedals();
    }

    private void manageScore() {
        SharedPreferences saves = gameActivity.getSharedPreferences(score_save_name, 0);
        int oldPoints = saves.getInt(best_score_key, 0);
        if (gameActivity.accomplishmentBox.points > oldPoints) {
            // Save new highscore
            SharedPreferences.Editor editor = saves.edit();
            editor.putInt(best_score_key, gameActivity.accomplishmentBox.points);
            tvBestScoreVal.setTextColor(Color.RED);
            editor.apply();
        }
        tvCurrentScoreVal.setText("" + gameActivity.accomplishmentBox.points);
        tvBestScoreVal.setText("" + oldPoints);
    }

    private void manageMedals() {
        SharedPreferences MEDAL_SAVE = gameActivity.getSharedPreferences(MainActivity.MEDAL_SAVE, 0);
        int medal = MEDAL_SAVE.getInt(MainActivity.MEDAL_KEY, 0);

        SharedPreferences.Editor editor = MEDAL_SAVE.edit();

        if (gameActivity.accomplishmentBox.achievement_gold) {
            ((ImageView) findViewById(R.id.medal)).setImageBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.gold));
            if (medal < 3) {
                editor.putInt(MainActivity.MEDAL_KEY, 3);
            }
        } else if (gameActivity.accomplishmentBox.achievement_silver) {
            ((ImageView) findViewById(R.id.medal)).setImageBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.silver));
            if (medal < 2) {
                editor.putInt(MainActivity.MEDAL_KEY, 2);
            }
        } else if (gameActivity.accomplishmentBox.achievement_bronze) {
            ((ImageView) findViewById(R.id.medal)).setImageBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.bronce));
            if (medal < 1) {
                editor.putInt(MainActivity.MEDAL_KEY, 1);
            }
        } else {
            ((ImageView) findViewById(R.id.medal)).setVisibility(View.INVISIBLE);
        }
        editor.apply();
    }

    private void saveCoins() {
        SharedPreferences coin_save = gameActivity.getSharedPreferences(GameActivity.coin_save, 0);
        coin_save.getInt(GameActivity.coin_key, 0);
        SharedPreferences.Editor editor = coin_save.edit();
        editor.putInt(GameActivity.coin_key, gameActivity.coins);
        editor.apply();
    }

}
