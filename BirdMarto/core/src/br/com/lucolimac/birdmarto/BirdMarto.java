package br.com.lucolimac.birdmarto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class BirdMarto extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Texture gameOver;
    private Random random;

    //Atributos de configuraçao
    private float larguraTela;
    private float alturaTela;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomico;
    private int estadoJogo = 0;

    private BitmapFont fonte;
    private BitmapFont mensagem;
    private int pontuacao = 0;
    private Boolean marcouPonto = false;


    private Circle passaroCircle;
    private Rectangle canoBaixoRec;
    private Rectangle canoAltoRec;
        private ShapeRenderer shape;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final float VIRTUAL_WIDTH = 1024;
    private final float VIRTUAL_HEIGHT = 1366;

    @Override
    public void create() {
        batch = new SpriteBatch();
        random = new Random();
        passaros = new Texture[3];


        passaroCircle = new Circle();
        canoAltoRec = new Rectangle();
        canoBaixoRec = new Rectangle();
        shape = new ShapeRenderer();

        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);
        mensagem = new BitmapFont();
        mensagem.setColor(Color.WHITE);
        mensagem.getData().setScale(3);
        passaros[0] = new Texture("passaro.png");
        passaros[1] = new Texture("marto2.png");
        passaros[2] = new Texture("marto2.png");

        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");
        gameOver = new Texture("game_over.png");

//        ********************************************************************************************
//        Configuração da camêra
//        ********************************************************************************************
        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        larguraTela = VIRTUAL_WIDTH;
//        larguraTela = Gdx.graphics.getWidth();
//        alturaTela = Gdx.graphics.getHeight();
        alturaTela = VIRTUAL_HEIGHT;
        posicaoInicialVertical = alturaTela / 2;
        posicaoMovimentoCanoHorizontal = larguraTela;
        espacoEntreCanos = 500;


    }

    int movimento = 0;

    @Override
    public void render() {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BITS);
        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 10;
        if ((variacao > 2)) {
            variacao = 0;
        }
        if (estadoJogo == 0) {
            if (Gdx.input.justTouched()) {
                estadoJogo = 1;
            }
        } else {
            velocidadeQueda++;
            if ((posicaoInicialVertical > 0) || (velocidadeQueda < 0)) {
                posicaoInicialVertical -= velocidadeQueda;
            }
            if (1 == estadoJogo) {
                posicaoMovimentoCanoHorizontal -= deltaTime * 200;
                if (Gdx.input.justTouched()) {
                    velocidadeQueda = -15;
                }
                //Verifica se o cano saiu inteiramente da tela
                if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
                    posicaoMovimentoCanoHorizontal = larguraTela;
                    alturaEntreCanosRandomico = random.nextInt(500) - 250;
                    marcouPonto = false;
                }
                //Pontuação
                if (posicaoMovimentoCanoHorizontal < 120) {
                    if (!marcouPonto) {
                        marcouPonto = true;
                        pontuacao++;
                    }
                }
            } else {
                if (Gdx.input.justTouched()) {
                    estadoJogo = 0;
                    pontuacao = 0;
                    velocidadeQueda = 0;
                    posicaoInicialVertical = alturaTela / 2;
                    posicaoMovimentoCanoHorizontal = larguraTela;
                }
            }
        }
//        ******************************************************
//        Configurar dados de profeção da camera
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(fundo, 0, 0, larguraTela, alturaTela);
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaTela / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomico);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaTela / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomico);
//        batch.draw(passaros[(int) variacao], 120, posicaoInicialVertical);
        batch.draw(passaros[0], 120, posicaoInicialVertical);
        fonte.draw(batch, String.valueOf(pontuacao), larguraTela / 2, alturaTela - 50);
        if (estadoJogo == 2) {
            batch.draw(gameOver, larguraTela / 2 - gameOver.getWidth() / 2, alturaTela / 2);
            mensagem.draw(batch, "Toque na tela para reiniciar", larguraTela / 2 - 300, alturaTela / 2 - gameOver.getHeight() / 2);
        }
        batch.end();

        passaroCircle.set((120 + passaros[0].getWidth() / 2), (posicaoInicialVertical + passaros[0].getHeight() / 2), passaros[0].getHeight() / 2);
        canoBaixoRec = new Rectangle(posicaoMovimentoCanoHorizontal, alturaTela / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomico, canoBaixo.getWidth(), canoBaixo.getHeight());
        canoAltoRec = new Rectangle(posicaoMovimentoCanoHorizontal, alturaTela / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomico, canoTopo.getWidth(), canoTopo.getHeight());
//        shape.begin(ShapeRenderer.ShapeType.Filled);
//        shape.circle(passaroCircle.x, passaroCircle.y, passaroCircle.radius);
//        shape.rect(canoBaixoRec.x, canoBaixoRec.y, canoBaixoRec.width, canoBaixoRec.height);
//        shape.rect(canoAltoRec.x, canoAltoRec.y, canoAltoRec.width, canoAltoRec.height);
//        shape.setColor(Color.RED);
//        shape.end();
        if (Intersector.overlaps(passaroCircle, canoAltoRec) || Intersector.overlaps(passaroCircle, canoBaixoRec) || posicaoInicialVertical <= 0 || posicaoInicialVertical >= alturaTela) {
            estadoJogo = 2;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}