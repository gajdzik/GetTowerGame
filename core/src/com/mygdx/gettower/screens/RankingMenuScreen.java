package com.mygdx.gettower.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.gettower.GetTowerGameClass;

public class RankingMenuScreen extends AbstractScreen {

    // TODO: ADD buttons and "top_screens"

    private BitmapFont top_scores;
    private BitmapFont top_platforms;
    private BitmapFont top_times;

    public RankingMenuScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init() {
        top_scores = new BitmapFont();
        top_scores.getData().setScale(2, 2);

        top_platforms = new BitmapFont();
        top_platforms.getData().setScale(2, 2);

        top_times = new BitmapFont();
        top_times.getData().setScale(2, 2);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        top_scores.draw(spriteBatch,"TOP SCORES", 150, 500 );
        top_platforms.draw(spriteBatch,"TOP PLATFORMS", 150, 400 );
        top_times.draw(spriteBatch,"TOP TIMES", 150, 300 );
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        top_scores.dispose();
        top_platforms.dispose();
        top_times.dispose();
    }
}
