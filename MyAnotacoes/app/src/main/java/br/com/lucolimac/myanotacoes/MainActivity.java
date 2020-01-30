package br.com.lucolimac.myanotacoes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText texto;
    private ImageView botaoSalvar;
    private static final String NOME_ARQUIVO = "anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = findViewById(R.id.textId);
        botaoSalvar = findViewById(R.id.botao_salvar);
        lerArquivo();
        if (lerArquivo() != null) {
            texto.setText(lerArquivo());
        }
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoDigitado = texto.getText().toString();
                gravaNoArquivo(textoDigitado);
                Toast.makeText(MainActivity.this, "Arquivo gravado com sucesso", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void gravaNoArquivo(String text) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo() {
        String resultado = "";
        try {
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if (arquivo != null) {
                //ler arquivo
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                //Gerar buffer do arquivo lido
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //Recuperar textos no arquivo
                String linha = "";
                while (bufferedReader.readLine() != null) {
                    resultado += linha;
                }
                arquivo.close();
            }
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
        return resultado;
    }
}
