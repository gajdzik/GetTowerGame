package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.mygdx.gettower.GetTowerGameClass;

import java.awt.Font;

public class MenuScreen extends AbstractScreen
{
    // TODO: USE TextButton instead of bitmapfont and button !
    // add fonts and json skins!
    private BitmapFont start_game;
    private BitmapFont change_lvl;
    private BitmapFont change_avatar;
    private BitmapFont ranking;
    private BitmapFont exit;
    private Button startButton;
    private Button rankingButton;
    private Button exitButton;

    public MenuScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        start_game = new BitmapFont();
        start_game.getData().setScale(2, 2);

        change_lvl = new BitmapFont();
        change_lvl.getData().setScale(2, 2);

        change_avatar = new BitmapFont();
        change_avatar.getData().setScale(2, 2);

        ranking = new BitmapFont();
        ranking.getData().setScale(2, 2);

        exit = new BitmapFont();
        exit.getData().setScale(2, 2);

        startButton = new Button(new Button.ButtonStyle());
        startButton.setWidth(160);
        startButton.setHeight(60);
        startButton.setX(150);
        startButton.setY(450);
        startButton.setDebug(true);
        stage.addActor(startButton);
        startButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new GameplayScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        rankingButton = new Button(new Button.ButtonStyle());
        rankingButton.setWidth(160);
        rankingButton.setHeight(60);
        rankingButton.setX(150);
        rankingButton.setY(150);
        rankingButton.setDebug(true);
        stage.addActor(rankingButton);
        rankingButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new RankingMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitButton = new Button(new Button.ButtonStyle());
        exitButton.setWidth(160);
        exitButton.setHeight(60);
        exitButton.setX(150);
        exitButton.setY(50);
        exitButton.setDebug(true);
        stage.addActor(exitButton);
        exitButton.addListener(new ClickListener()
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
        start_game.draw(spriteBatch,"START", 150, 500 );
        change_lvl.draw(spriteBatch,"CHANGE LEVEL", 150, 400 );
        change_avatar.draw(spriteBatch,"CHANGE AVATAR", 150, 300 );
        ranking.draw(spriteBatch,"RANKING", 150, 200 );
        exit.draw(spriteBatch,"EXIT", 150, 100 );
        //    TODO: When adds stage.draw(), exit.draw is missing!? why?
        // stage.draw();
        spriteBatch.end();
    }


    @Override
    public void dispose()
    {
        start_game.dispose();
        change_lvl.dispose();
        change_avatar.dispose();
        ranking.dispose();
        exit.dispose();
    }
}
