package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.gettower.entities.Platform;
import com.mygdx.gettower.entities.Player;
import com.mygdx.gettower.GetTowerGameClass;
import com.mygdx.gettower.tables.Highscore;
import com.mygdx.gettower.tables.HighscoreArray;

import java.util.Random;

public class GameplayScreen extends AbstractScreen
{
    private Player player;
   // private Button leftButton;
   // private Button rightButton;
    private Button jumpButton;
    private Platform start_platform;
    private Array<Platform> platform_array;
    private Music music;
    private float camera_stay;
    private int platform_counter;
    private int score_height;
    private int score;
    private int best_score;
    private int time;
    private Label name_time;
    private Label name_score;
    private Label name_best_score;
    private double acceleration;
    private boolean acceleration_flag;
    private Highscore highscore;
    private FileHandle file_handle;
    private Json json;
    private HighscoreArray highscore_array;
    private float acce;
    private float speed_level;

    public GameplayScreen(GetTowerGameClass game)
    {
        super(game);
        if ( prefs.getString(PREF_LEVEL) == "Easy" )
            speed_level = 0.4f;
        else if ( prefs.getString(PREF_LEVEL) == "Medium" )
            speed_level = 0.5f;
        else
        {
            speed_level = 0.6f;
            prefs.putString(PREF_LEVEL,"Hard");
            prefs.flush();
        }
        acceleration = speed_level;
    }

    @Override
    protected void init()
    {
        initPlatforms();
        initButtons();
        initPlayer();
        initLabels();
        camera_stay = player.getY();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
        music.play();
        score_height = 210;
        score = 0;
        best_score = 0;
        time = 0;
        acceleration_flag = true;
        highscore = new Highscore(0,0,0);
        file_handle = Gdx.files.local("highscores.json");
        json = new Json();
        highscore_array = new HighscoreArray();
        if (file_handle.length() > 0)
        {
            highscore_array = json.fromJson(highscore_array.getClass(), file_handle.readString());
            for (Highscore hs : highscore_array.getArray_highscore())
            {
                if (hs.getScore() > best_score)
                    best_score = hs.getScore();

                // JUST FOR TESTS
                System.out.print("platform = "+hs.getPlatform()+" score = "+hs.getScore()+" time = "+ hs.getTime()+"\n");
            }
        }
        initLabels();

        start_timer();
        acce = 0;

    }

    private void initLabels()
    {
        name_time = new Label(String.valueOf(time),skin.get("title",LabelStyle.class));
        name_time.setPosition(-100,-100);
        name_time.setFontScale(0.5f);
        stage.addActor(name_time);
        name_score = new Label(String.valueOf(score),skin.get("title",LabelStyle.class));
        name_score.setPosition(-100,-100);
        name_score.setFontScale(0.5f);
        stage.addActor(name_score);
        name_best_score = new Label(String.valueOf(best_score),skin.get("title",LabelStyle.class));
        name_best_score.setPosition(-100,-100);
        name_best_score.setFontScale(0.5f);
        stage.addActor(name_best_score);
    }

    private void initPlatforms()
    {
        platform_counter = 1;
        start_platform = new Platform("start_platform.png",480,30);
        start_platform.setPosition(0, 30);
        stage.addActor(start_platform);

        platform_array = new Array<Platform>();
        Random generator = new Random();

        for (; platform_counter<10; platform_counter++)
        {
            int x = MathUtils.random(0, 280);
            int y = 150 * platform_counter + 30;
            Platform platform = new Platform("platform.png",200,30);
            platform.setSize(generator.nextInt(50)+100,30);
            platform.setPosition(x, y);
            platform_array.add(platform);
            stage.addActor(platform_array.get(platform_counter-1));
        }
    }

    private void initButtons()
    {
        /*
        leftButton = new Button(new ButtonStyle());
        setButton(leftButton, 160, 120, 0, -90);
        leftButton.setDebug(true);
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
        rightButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                player.goRight();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
*/
        jumpButton = new Button(new ButtonStyle());
        setButton(jumpButton, 160, 120, 161, -90);
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

    private void initPlayer()
    {
        try
        {
            prefs.getString(PREF_AVATAR);
        }
        catch ( NullPointerException e)
        {
            prefs.putString(PREF_AVATAR, "player2.png");
            prefs.flush();
        }

        player = new Player(prefs.getString(PREF_AVATAR));
        stage.addActor(player);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);

        if  (acce + 1 < Gdx.input.getAccelerometerX() )
        {
            player.goLeft();
            acce = Gdx.input.getAccelerometerX();
        }
        else if ( acce - 1 > Gdx.input.getAccelerometerX() )
        {
            player.goRight();
            acce = Gdx.input.getAccelerometerX();
        }

        update();
        stage.draw();

        if(isEndGame())
        {
            highscore.setPlatform(score);
            highscore.setScore(score);
            highscore.setTime(time);
            highscore_array.getArray_highscore().add(highscore);
            file_handle.writeString(json.toJson(highscore_array), false);
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
        updateLabels();

        stage.act();
    }

    private void updateLabels()
    {
        name_time.setText("Your time: "+time);
        name_time.setPosition(320,camera_stay+390);

        name_score.setText("Your score: "+score);
        name_score.setPosition(320,camera_stay+420);

        name_best_score.setText("Best score: "+best_score);
        name_best_score.setPosition(320,camera_stay+450);
    }

    private boolean isEndGame()
    {
        if (player.getY() < camera_stay-200)
            return true;
        else
            return false;
    }

    private boolean isBeatBestScore()
    {
        if (score > best_score)
            return true;
        else
            return false;
    }

    private void acceleration_camera()
    {
        if (score % 10 == 0 && acceleration_flag)
        {
            acceleration += speed_level;
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
        if (score_height == player.getY() && player.canJump)
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
      //  setButton(leftButton, 160, 120, 0, Math.round(camera_stay-150));
      //  setButton(rightButton, 160, 120, 322, Math.round(camera_stay-150));
      //  setButton(jumpButton, 160, 120, 161, Math.round(camera_stay-150));
        setButton(jumpButton, 480, 700, 0, Math.round(camera_stay-150));

    }

    private void fall()
    {
        if (player.jumpDelay >= 0 && !player.canJump)
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

    private void start_timer()
    {
        Timer.schedule(new Task(){
                           @Override
                           public void run() {
                               time++;
                           }
                       }
                , 0        //    (delay)
                , 1     //    (seconds)
        );
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
    }

}
