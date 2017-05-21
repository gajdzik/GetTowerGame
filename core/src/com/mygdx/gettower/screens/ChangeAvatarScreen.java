package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.gettower.GetTowerGameClass;

public class ChangeAvatarScreen extends AbstractScreen {

    private SelectBox select_box;
    private TextButton return_button;
    private Image avatar_image;

    public ChangeAvatarScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init() {

        return_button = new TextButton("RETURN",skin);
        setButton(return_button, 300, 60, 100, 50);
        return_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new MenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        select_box = new SelectBox(skin.get("big", SelectBox.SelectBoxStyle.class));
        stage.addActor(select_box);
        select_box.setPosition(100,300);
        select_box.setSize(300,50);
        select_box.setItems("Deska", "Tux", "Icy Tower");

        if ( prefs.getString(PREF_AVATAR) == "player2.png" )
        {
            select_box.setSelected("Deska");
        }
        else if ( prefs.getString(PREF_AVATAR) == "tux.png" )
        {
            select_box.setSelected("Tux");
        }
        else
        {
            select_box.setSelected("Icy Tower");
            prefs.putString(PREF_AVATAR, "icytower.png");
            prefs.flush();
        }

        select_box.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if ( select_box.getSelected() == "Deska" )
                {
                    prefs.putString(PREF_AVATAR, "player2.png");
                }
                else if (select_box.getSelected() == "Tux")
                {
                    prefs.putString(PREF_AVATAR, "tux.png");
                }
                else
                {
                    prefs.putString(PREF_AVATAR, "icytower.png");
                }
                prefs.flush();

                avatar_image.remove();
                avatar_image = new Image(new Texture(prefs.getString(PREF_AVATAR)));
                avatar_image.setPosition(200,400);
                avatar_image.setSize(100,200);
                stage.addActor(avatar_image);
            }
        });

        avatar_image = new Image(new Texture(prefs.getString(PREF_AVATAR)));
        avatar_image.setPosition(200,400);
        avatar_image.setSize(100,200);
        stage.addActor(avatar_image);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose()
    {
    }
}
