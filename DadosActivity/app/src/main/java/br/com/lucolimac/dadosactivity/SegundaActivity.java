package br.com.lucolimac.dadosactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SegundaActivity extends Activity {
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        texto = findViewById(R.id.text_id);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String textoPassado = extra.getString("nome");
            texto.setText(textoPassado);
        }
    }
}
