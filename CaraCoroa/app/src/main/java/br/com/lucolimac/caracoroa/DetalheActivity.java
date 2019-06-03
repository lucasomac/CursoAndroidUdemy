package br.com.lucolimac.caracoroa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class DetalheActivity extends AppCompatActivity {
    private ImageView botaoVoltar;
    private ImageView moeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        botaoVoltar = findViewById(R.id.botaovoltar);
        moeda = findViewById(R.id.moedaId);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String escolhida = extra.getString("opcao");
            if (Objects.equals(escolhida, "cara")) {
                moeda.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.moeda_cara));
            } else {
                moeda.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.moeda_coroa));
            }
        }
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetalheActivity.this, MainActivity.class));
            }
        });
    }
}
