package br.com.lucolimac.sombichos;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView cao;
    private ImageView gato;
    private ImageView leao;
    private ImageView macaco;
    private ImageView ovelha;
    private ImageView vaca;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cao = findViewById(R.id.caoId);
        gato = findViewById(R.id.gatoId);
        leao = findViewById(R.id.leaoId);
        macaco = findViewById(R.id.macacoId);
        ovelha = findViewById(R.id.ovelhaId);
        vaca = findViewById(R.id.vacaId);
        cao.setOnClickListener(this);
        gato.setOnClickListener(this);
        leao.setOnClickListener(this);
        macaco.setOnClickListener(this);
        ovelha.setOnClickListener(this);
        vaca.setOnClickListener(this);
    }

    private void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.caoId:
                mediaPlayer = MediaPlayer.create(this, R.raw.cao);
                startMusic();
                break;
            case R.id.gatoId:
                mediaPlayer = MediaPlayer.create(this, R.raw.gato);
                startMusic();
                break;
            case R.id.leaoId:
                mediaPlayer = MediaPlayer.create(this, R.raw.leao);
                startMusic();
                break;
            case R.id.macacoId:
                mediaPlayer = MediaPlayer.create(this, R.raw.macaco);
                startMusic();
                break;
            case R.id.ovelhaId:
                mediaPlayer = MediaPlayer.create(this, R.raw.ovelha);
                startMusic();
                break;
            case R.id.vacaId:
                mediaPlayer = MediaPlayer.create(this, R.raw.vaca);
                startMusic();
                break;
        }
    }
}
