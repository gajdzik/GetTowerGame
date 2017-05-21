package com.mygdx.gettower.screens;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Preferences;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Button;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.utils.viewport.StretchViewport;

        import com.mygdx.gettower.GetTowerGameClass;

public abstract class AbstractScreen implements Screen
{
    protected static final String PREF_GAME = "GAJDZINSKI.GAME";
    protected static final String PREF_AVATAR = "GAJDZINSKI.DESKA";

    protected GetTowerGameClass game;
    protected Stage stage;
    private OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected Skin skin;
    protected Preferences prefs;

    public AbstractScreen(GetTowerGameClass game)
    {
        this.game = game;
        skin = new Skin(Gdx.files.internal("comic/comic-ui.json"));
        createCamera();
        stage = new Stage(new StretchViewport(GetTowerGameClass.WIDTH,GetTowerGameClass.HEIGHT,getCamera()));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        prefs = Gdx.app.getPreferences(PREF_GAME);
        init();
    }

    protected abstract void init();

    private void createCamera() {
        setCamera(new OrthographicCamera());
        getCamera().setToOrtho(false, GetTowerGameClass.WIDTH, GetTowerGameClass.HEIGHT);
        getCamera().update();
    }

    @Override
    public void render(float delta)
    {
        clearScreen();
        getCamera().update();
        spriteBatch.setProjectionMatrix(getCamera().combined);
    }

    private void clearScreen()
    {
        Gdx.gl.glClearColor(0.5f, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void setButton(Button button, int w, int h, int x, int y)
    {
        button.setWidth(w);
        button.setHeight(h);
        button.setX(x);
        button.setY(y);
        stage.addActor(button);
    }

    @Override
    public void show() {}

    @Override
    public void resume()
    {
        game.setPaused(false);
    }

    @Override
    public void pause()
    {
        game.setPaused(true);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose()
    {
        game.dispose();
    }

    @Override
    public void resize(int width, int height) {}

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
