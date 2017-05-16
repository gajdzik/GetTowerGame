package com.mygdx.gettower.screens;

//      TODO: ADD ANDROID AND ICYTOWER AVATARS

import com.mygdx.gettower.GetTowerGameClass;

public class ChangeAvatarScreen extends AbstractScreen {

    public ChangeAvatarScreen(final GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init() {
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
