package br.com.lucolimac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView exibicao;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button escolher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        escolher = findViewById(R.id.buttonEsolher);
        radioGroup = findViewById(R.id.group);
        exibicao = findViewById(R.id.textoExibicao);
        escolher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioEscolhido = radioGroup.getCheckedRadioButtonId();
                if (radioEscolhido > 0) {
                    radioButton = findViewById(radioEscolhido);
                    exibicao.setText(radioButton.getText());
                }
            }
        });
    }
}
