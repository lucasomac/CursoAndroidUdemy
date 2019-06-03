package br.com.lucolimac.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    private TextView mensagem;
    private Button button;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editNome);
        mensagem = findViewById(R.id.mensagem);
        button = findViewById(R.id.buttonSalvar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (nome.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Por favor preencha o nome!!", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("nome", nome.getText().toString());
                    editor.apply();
                    mensagem.setText("Olá " + nome.getText().toString());
                }
            }
        });
        //Recupera dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (sharedPreferences.contains("nome")) {
            String nomeUsuario = sharedPreferences.getString("nome", "usuário não definido");
            mensagem.setText("Olá " + (nomeUsuario));
        } else {
            mensagem.setText("Olá, usuário não definido");
        }
    }
}
