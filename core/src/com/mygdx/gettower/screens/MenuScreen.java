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
    private Skin skin;

    public MenuScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        skin = new Skin(Gdx.files.internal("comic/comic-ui.json"));

        start_button = new TextButton("START",skin);
        start_button.setWidth(300);
        start_button.setHeight(60);
        start_button.setX(100);
        start_button.setY(550);
        stage.addActor(start_button);
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
        change_lvl_button.setWidth(300);
        change_lvl_button.setHeight(60);
        change_lvl_button.setX(100);
        change_lvl_button.setY(430);
        stage.addActor(change_lvl_button);
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
        change_avatar_button.setWidth(300);
        change_avatar_button.setHeight(60);
        change_avatar_button.setX(100);
        change_avatar_button.setY(310);
        stage.addActor(change_avatar_button);
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
        ranking_button.setWidth(300);
        ranking_button.setHeight(60);
        ranking_button.setX(100);
        ranking_button.setY(190);
        stage.addActor(ranking_button);
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
        exit_button.setWidth(300);
        exit_button.setHeight(60);
        exit_button.setX(100);
        exit_button.setY(70);
        stage.addActor(exit_button);
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
