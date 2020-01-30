package com.lucasomac.alcoolougasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button verificar;
    private EditText alcool;
    private EditText gasolina;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alcool = (EditText) findViewById(R.id.precoAlcool);
        gasolina = (EditText) findViewById(R.id.precoGasolina);
        verificar = (Button) findViewById(R.id.botaoVerificar);
        resultado = (TextView) findViewById(R.id.resultadoComparacao);
        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String al = alcool.getText().toString();
                String gl = gasolina.getText().toString();

                Double dal = Double.parseDouble(al);
                Double dgl = Double.parseDouble(gl);

                double comp = dal / dgl;
                if (comp>=0.7){
                    resultado.setText("É melhor ultilizar gasolina!");
                }else {
                    resultado.setText("É melhor ultilizar álcool!");
                }

            }
        });
    }
}
