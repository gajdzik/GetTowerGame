package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.mygdx.gettower.GetTowerGameClass;
import com.mygdx.gettower.tables.HighscoreArray;

public class RankingMenuScreen extends AbstractScreen {

    private TextButton return_button;
    private TextButton top_scores_button;
    private TextButton top_platforms_button;
    private TextButton top_times_button;
    private FileHandle file_handle;
    private Json json;
    private HighscoreArray highscore_array;

    public RankingMenuScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init() {
        file_handle = Gdx.files.local("highscores.json");
        json = new Json();
        highscore_array = new HighscoreArray();
        if (file_handle.length() > 0)
            highscore_array = json.fromJson(highscore_array.getClass(), file_handle.readString());


        return_button = new TextButton("RETURN",skin);
        setButton(return_button, 160, 60, 150, 50);
        return_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new MenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        top_scores_button = new TextButton("TOP SCORES",skin);
        setButton(top_scores_button, 160, 60, 150, 450);
        top_scores_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new TopScoresScreen(game,highscore_array, "score"));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        top_platforms_button = new TextButton("TOP PLATFORMS",skin);
        setButton(top_platforms_button, 160, 60, 150, 350);
        top_platforms_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new TopScoresScreen(game,highscore_array, "platform"));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        top_times_button = new TextButton("TOP TIMES",skin);
        setButton(top_times_button, 160, 60, 150, 250);
        top_times_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new TopScoresScreen(game,highscore_array, "times"));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
    }
}
