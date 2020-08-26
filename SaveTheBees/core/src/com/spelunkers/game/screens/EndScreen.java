package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Background;


public class EndScreen extends ScreenAdapter {
    private BeesGame game;
    private Background background;
    private Stage stage;
    private Music music;


    public EndScreen(BeesGame game) {
        this.game = game;
        background = new Background();
        music = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - The Cannery (Background Gaming Music).mp3"));
        music.setVolume((float) 0.2);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera(BeesGame.WIDTH, BeesGame.HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // So much work for a button...
        Button playBtn = new TextButton("Play Again", skin, "small");
        playBtn.setSize(100, 50);
        playBtn.setPosition((BeesGame.WIDTH / 2) - 70, (BeesGame.HEIGHT / 2) - 200);
        playBtn.setTransform(true);
        playBtn.scaleBy(0.5f);
        playBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
            }
        });

        // Here goes our logo
        Texture logo = new Texture("SaveTheBees-Logo.png");
        Image logoImage = new Image();
        logoImage.setSize(400, 400);
        logoImage.setPosition((BeesGame.WIDTH / 2) - (logoImage.getWidth() / 2),(BeesGame.HEIGHT / 2) - (logoImage.getHeight() / 3));
        logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(logo)));


        //Honey picture
        Texture honey = new Texture("honey.png");
        Image honeyImage = new Image();
        honeyImage.setSize(800, 300);
        honeyImage.setPosition((0),(BeesGame.HEIGHT / 2) + (logoImage.getHeight() / 3));
        honeyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honey)));

        //HoneyHive picture
        Texture honeyHive = new Texture("hiveBees.png");
        Image hiveBeesImage = new Image();
        hiveBeesImage.setSize(300, 300);
        hiveBeesImage.setPosition((0),(BeesGame.HEIGHT / 2) + (hiveBeesImage.getHeight() / 3));
        hiveBeesImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honeyHive)));


        //credits
        String text = "\n" +
                "    Kristin Jue\n" +
                "    Erica Chong\n" +
                "    Obaid Sidiqi\n" +
                "    Lily Aguirre\n" +
                "    Monica King\n" +
                "    Jardi Martinez\n";
        Label credits = new Label(text, skin);
        credits.setPosition((BeesGame.WIDTH / 4) * 3 , 10);

        stage.addActor(background);
        stage.addActor(playBtn);
        stage.addActor(logoImage);
        stage.addActor(credits);
        stage.addActor(honeyImage);
        stage.addActor(hiveBeesImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        //background.dispose();
        //playBtn.dispose();
        game.dispose();
        stage.dispose();
        music.dispose();
    }
}