package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.mygdx.gettower.GetTowerGameClass;
import com.mygdx.gettower.tables.HighscoreArray;

public class RankingMenuScreen extends AbstractScreen {

    // TODO: ADD buttons and "top_screens"

    private BitmapFont top_scores;
    private BitmapFont top_platforms;
    private BitmapFont top_times;
    private BitmapFont name_return;
    private Button return_button;
    private Button top_scores_button;
  //  private Button top_platforms_button;
   // private Button top_times_button;
    private FileHandle file_handle;
    private Json json;
    private HighscoreArray highscore_array;

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
        name_return = new BitmapFont();
        name_return.getData().setScale(2, 2);

        file_handle = Gdx.files.local("highscores.json");
        json = new Json();
        highscore_array = new HighscoreArray();
        if (file_handle.length() > 0)
            highscore_array = json.fromJson(highscore_array.getClass(), file_handle.readString());


        return_button = new Button(new Button.ButtonStyle());
        return_button.setWidth(160);
        return_button.setHeight(60);
        return_button.setX(150);
        return_button.setY(50);
        return_button.setDebug(true);
        stage.addActor(return_button);
        return_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new MenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        top_scores_button = new Button(new Button.ButtonStyle());
        top_scores_button.setWidth(160);
        top_scores_button.setHeight(60);
        top_scores_button.setX(150);
        top_scores_button.setY(450);
        top_scores_button.setDebug(true);
        stage.addActor(top_scores_button);
        top_scores_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new TopScoresScreen(game,highscore_array));
                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        top_scores.draw(spriteBatch,"TOP SCORES", 150, 500 );
        top_platforms.draw(spriteBatch,"TOP PLATFORMS", 150, 400 );
        top_times.draw(spriteBatch,"TOP TIMES", 150, 300 );
        name_return.draw(spriteBatch, "Return",150,80);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        top_scores.dispose();
        top_platforms.dispose();
        top_times.dispose();
        name_return.dispose();
    }
}
