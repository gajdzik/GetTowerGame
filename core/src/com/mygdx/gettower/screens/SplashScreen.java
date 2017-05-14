package com.mygdx.gettower.screens;

        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.utils.Timer;
        import com.badlogic.gdx.utils.Timer.Task;
        import com.mygdx.gettower.GetTowerGameClass;

public class SplashScreen extends AbstractScreen
{
    private BitmapFont game_name;
    private BitmapFont made_by;
    private BitmapFont creator_name;

    public SplashScreen(final GetTowerGameClass game)
    {
        super(game);
        Timer.schedule(new Task()
                       {
                           @Override
                           public void run()
                           {
                               game.setScreen(new MenuScreen(game));
                           }

                       }
                , 3);
    }

    @Override
    protected void init()
    {
        game_name = skin.getFont("title");
        game_name.getData().setScale(0.8f, 0.8f);
        made_by = skin.getFont("title");
        creator_name = skin.getFont("title");
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        game_name.draw(spriteBatch,"Get Tower", 160, 500 );
        made_by.draw(spriteBatch,"made by", 175, 400 );
        creator_name.draw(spriteBatch,"Przemyslaw  Gajdzinski", 50, 300 );
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        game_name.dispose();
        made_by.dispose();
        creator_name.dispose();
    }
}
