package com.mygdx.gettower.screens;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Preferences;
        import com.badlogic.gdx.audio.Music;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.math.MathUtils;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.ui.Button;
        import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.badlogic.gdx.utils.Array;

        import com.mygdx.gettower.entities.Platform;
        import com.mygdx.gettower.entities.Player;
        import com.mygdx.gettower.GetTowerGameClass;
        import com.mygdx.gettower.tables.Highscore;

public class GameplayScreen extends AbstractScreen
{
    private static final String PREF_GAME = "GAJDZINSKI.GAME";
    private static final String PREF_BEST_SCORE = "GAJDZINSKI.BEST_SCORE";

    private Player player;
    private Button leftButton;
    private Button rightButton;
    private Button jumpButton;
    private Platform start_platform;
    private Array<Platform> platform_array;
    private Music music;
    private float camera_stay;
    private int platform_counter;
    private int score_height;
    private int score;
    private int best_score;
    private BitmapFont name_score;
    private BitmapFont name_best_score;
    private BitmapFont name_left;
    private BitmapFont name_jump;
    private BitmapFont name_right;
    private double acceleration;
    private boolean acceleration_flag;
    private Preferences prefs;
    private Highscore highscore;

    public GameplayScreen(GetTowerGameClass game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        initPlatforms();
        initButtons();
        initPlayer();
        camera_stay = player.getY();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
        music.play();
        score_height = 210;
        score = 0;
        name_score = new BitmapFont();
        name_best_score = new BitmapFont();
        name_left = new BitmapFont();
        name_jump = new BitmapFont();
        name_right = new BitmapFont();
        acceleration = 0.5;
        acceleration_flag = true;
        prefs = Gdx.app.getPreferences(PREF_GAME);
        best_score = prefs.getInteger(PREF_BEST_SCORE);
    }

    private void initPlatforms()
    {
        platform_counter = 1;
        start_platform = new Platform("start_platform.png",480,30);
        start_platform.setPosition(0, 30);
        stage.addActor(start_platform);

        platform_array = new Array<Platform>();

        for (; platform_counter<10; platform_counter++)
        {
            int x = MathUtils.random(0, 280);
            int y = 150 * platform_counter + 30;
            Platform platform = new Platform("platform.png",200,30);
            platform.setPosition(x, y);
            platform_array.add(platform);
            stage.addActor(platform_array.get(platform_counter-1));
        }
    }

    private void initButtons()
    {
        leftButton = new Button(new ButtonStyle());
        setButton(leftButton, 160, 120, 0, -90);
        leftButton.setDebug(true);
        stage.addActor(leftButton);
        leftButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                player.goLeft();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        rightButton = new Button(new ButtonStyle());
        setButton(rightButton, 160, 120, 322, -90);
        stage.addActor(rightButton);
        rightButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                player.goRight();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        jumpButton = new Button(new ButtonStyle());
        setButton(jumpButton, 160, 120, 161, -90);
        stage.addActor(jumpButton);
        jumpButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                player.goUp();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void setButton(Button button, int w, int h, int x, int y)
    {
        button.setWidth(w);
        button.setHeight(h);
        button.setX(x);
        button.setY(y);
    }

    private void initPlayer()
    {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
        spriteBatch.begin();
        name_score.draw(spriteBatch, "Your score: " + String.valueOf(score),350,camera_stay+420);
        name_best_score.draw(spriteBatch, "Best score: " + String.valueOf(best_score),350,camera_stay+450);
        name_left.draw(spriteBatch, "LEFT",50,camera_stay-100);
        name_jump.draw(spriteBatch, "JUMP",220,camera_stay-100);
        name_right.draw(spriteBatch, "RIGHT",380,camera_stay-100);

        spriteBatch.end();
        if(isEndGame())
        {
            this.dispose();
            game.setScreen(new EndGameScreen(game,isBeatBestScore(), score));
        }
    }

    private void update()
    {
        fall();
        isMusicStillPlaying();
        acceleration_camera();
        score_counter();
        updateButtons();
        updatePlatforms();
        updateCamera();
        stage.act();
    }

    private boolean isEndGame()
    {
        if (player.getY() < camera_stay-200)
        {
            if (isBeatBestScore())
            {
                prefs.putInteger(PREF_BEST_SCORE, score);
                prefs.flush();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isBeatBestScore()
    {
        if (score > best_score)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void acceleration_camera()
    {
        if (score % 10 == 0 && acceleration_flag == true)
        {
            acceleration += 0.5;
            acceleration_flag = false;
        }
        else if (score % 10 != 0)
        {
            acceleration_flag = true;
        }
        camera_stay += acceleration;
    }

    private void score_counter()
    {
        if (score_height == player.getY() && player.canJump == true)
        {
            score++;
            score_height = score_height + 150;
        }
    }

    private void updatePlatforms()
    {
        for(Platform p : platform_array)
        {
            if (p.getY() < player.getY() - 300)
            {
                p.setPosition(MathUtils.random(0, 280), 150 * platform_counter + 30);
                platform_counter++;
            }
        }
    }

    private void updateCamera()
    {
        if (player.getY() > camera_stay)
        {
            camera_stay = player.getY();
        }
        stage.getCamera().position.set(240,camera_stay+200,0);
    }

    private void updateButtons()
    {
        setButton(leftButton, 160, 120, 0, Math.round(camera_stay-150));
        setButton(rightButton, 160, 120, 322, Math.round(camera_stay-150));
        setButton(jumpButton, 160, 120, 161, Math.round(camera_stay-150));
    }

    public void fall()
    {
        if (player.jumpDelay >= 0 && player.canJump == false)
        {
            player.setPosition(player.getX(), player.getY()+300 * Gdx.graphics.getDeltaTime());
        }
        else if (player.jumpDelay < 0 && !isPlayerOnPlatform())
        {
            player.setPosition(player.getX(), player.getY()-300 * Gdx.graphics.getDeltaTime());
            player.canJump = false;
        }
        player.jumpDelay -= 300 * Gdx.graphics.getDeltaTime();
    }

    private boolean isPlayerOnPlatform()
    {
        if (player.getX()+player.getWidth() >= start_platform.getX() && player.getX() <= start_platform.getX() + start_platform.getWidth() && player.getY() >= start_platform.getY() && player.getY() <= start_platform.getY() +start_platform.getHeight())
        {
            player.canJump = true;
            player.setPosition(player.getX(), start_platform.getY() + start_platform.getHeight());
            return true;
        }
        for(Platform p : platform_array)
        {
            if (player.getX()+player.getWidth() >= p.getX() && player.getX() <= p.getX() + p.getWidth() && player.getY() >= p.getY() && player.getY() <= p.getY()+p.getHeight())
            {
                player.canJump = true;
                player.setPosition(player.getX(), p.getY() + p.getHeight());
                return true;
            }
        }
        return false;
    }

    private void isMusicStillPlaying()
    {
        if (!music.isPlaying())
        {
            music.play();
        }
    }

    @Override
    public void dispose()
    {
        music.dispose();
        name_score.dispose();
        name_best_score.dispose();
    }

}
