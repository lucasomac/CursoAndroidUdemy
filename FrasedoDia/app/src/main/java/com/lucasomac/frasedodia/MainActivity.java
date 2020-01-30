package com.lucasomac.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textoNovaFrase;
    private Button botaoNovaFrase;
    private String[] frases = {"O importante não é vencer todos os dias, mas lutar sempre.",
            "Maior que a tristeza de não haver vencido é a vergonha de não ter lutado!",
            "É melhor conquistar a si mesmo do que vencer mil batalhas.",
            "Enquanto houver vontade de lutar haverá esperança de vencer.",
            "Difícil é ganhar um amigo em uma hora; fácil é ofendê-lo em um minuto.",
            "O medo de perder tira a vontade de ganhar."
            , "Aquele que não tem confiança nos outros, não lhes pode ganhar a confiança.",
            "Arriscamo-nos a perder quando queremos ganhar demais."

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomico = new Random();
                int rand = randomico.nextInt(frases.length);
                textoNovaFrase.setText(frases[rand]);
            }
        });
    }
}
