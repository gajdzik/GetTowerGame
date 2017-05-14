package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.gettower.GetTowerGameClass;

public class MenuScreen extends AbstractScreen
{
    private TextButton change_lvl_button;
    private TextButton change_avatar_button;
    private TextButton ranking_button;
    private TextButton exit_button;
    private TextButton start_button;

    public MenuScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        start_button = new TextButton("START",skin);
        setButton(start_button, 300, 60, 100, 550);
        start_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new GameplayScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        change_lvl_button = new TextButton("CHANGE LEVEL",skin);
        setButton(change_lvl_button, 300, 60, 100, 430);
        change_lvl_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                //game.setScreen(new RankingMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        change_avatar_button = new TextButton("CHANGE AVATAR",skin);
        setButton(change_avatar_button, 300, 60, 100, 310);
        change_avatar_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                //game.setScreen(new RankingMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        ranking_button = new TextButton("RANKING",skin);
        setButton(ranking_button, 300, 60, 100, 190);
        ranking_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new RankingMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exit_button = new TextButton("EXIT",skin);
        setButton(exit_button, 300, 60, 100, 70);
        exit_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                System.exit(0);
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
