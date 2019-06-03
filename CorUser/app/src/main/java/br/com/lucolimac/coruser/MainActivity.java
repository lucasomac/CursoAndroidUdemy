package br.com.lucolimac.coruser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button botaoSalvar;
    private RadioGroup radioGroup;
    private RadioButton radioSelecionado;
    private static final String ARQUIVO_PREFERENCIA = "ArqPreferencia";
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoSalvar = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radioGroupId);
        constraintLayout = findViewById(R.id.layoutId);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioIdEscolhido = radioGroup.getCheckedRadioButtonId();

                if (radioIdEscolhido > 0) {
                    radioSelecionado = findViewById(radioIdEscolhido);
                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String corEscolhida = radioSelecionado.getText().toString();
                    editor.putString("cor_escolhida", radioSelecionado.getText().toString());
                    editor.apply();
                    setBackgroud(corEscolhida);
                }

            }
        });
        //Recuperar cor salva
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (sharedPreferences.contains("cor_escolhida")) {
            String cor = sharedPreferences.getString("cor_escolhida", "Laranja");
            setBackgroud(cor);
        }

    }

    private void setBackgroud(String cor) {
        switch (cor) {
            case "Azul":
                constraintLayout.setBackgroundColor(Color.BLUE);
                break;
            case "Laranja":
                constraintLayout.setBackgroundColor(Color.YELLOW);
                break;
            case "Verde":
                constraintLayout.setBackgroundColor(Color.GREEN);
                break;
        }
    }
}
