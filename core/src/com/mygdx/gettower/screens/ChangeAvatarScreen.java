package com.mygdx.gettower.screens;

//      TODO: ADD ANDROID AND ICYTOWER AVATARS

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.gettower.GetTowerGameClass;

public class ChangeAvatarScreen extends AbstractScreen {

    private SelectBox select_box;

    public ChangeAvatarScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init() {
        select_box = new SelectBox(skin.get("big", SelectBox.SelectBoxStyle.class));
        stage.addActor(select_box);
        select_box.setPosition(100,300);
        select_box.setSize(300,50);
        select_box.setItems("Deska", "Android", "Icy Tower");
        select_box.setSelected("Deska");
        select_box.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                //select_box.showList();

                // TODO: CHANGE AVATAR IF PICKED

                //prefs.putString(PREF_AVATAR, "player2.png");
                //prefs.flush();

                System.out.println(select_box.getSelected());
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
