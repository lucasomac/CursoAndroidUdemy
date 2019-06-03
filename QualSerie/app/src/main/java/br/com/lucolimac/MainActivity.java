package br.com.lucolimac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seek;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seek = findViewById(R.id.seekBarId);
        image = findViewById(R.id.imageExibicao);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress == 1)
                    image.setImageResource(R.drawable.pouco);
                else if (progress == 2)
                    image.setImageResource(R.drawable.medio);
                else if (progress == 3)
                    image.setImageResource(R.drawable.muito);
                else
                    image.setImageResource(R.drawable.susto);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
