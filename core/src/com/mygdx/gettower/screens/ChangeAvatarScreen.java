package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.gettower.GetTowerGameClass;

public class ChangeAvatarScreen extends AbstractScreen {

    private SelectBox select_box;
    private TextButton return_button;

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
        select_box.setSelected("Deska");
        select_box.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {

                // TODO: Show avatars when picked
                if ( select_box.getSelected() == "Deska" )
                {
                    prefs.putString(PREF_AVATAR, "player2.png");
                    prefs.flush();
                }
                else if (select_box.getSelected() == "Tux")
                {
                    prefs.putString(PREF_AVATAR, "tux.png");
                    prefs.flush();
                }
                else
                {
                    prefs.putString(PREF_AVATAR, "icytower.png");
                    prefs.getString(PREF_AVATAR);
                    prefs.flush();
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });

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
