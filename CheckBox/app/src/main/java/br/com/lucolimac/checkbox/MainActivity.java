package br.com.lucolimac.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private CheckBox checkBoxCao, checkBoxGato, checkBoxPapagaio;
    private Button mostrar;
    private TextView textExibicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxCao = findViewById(R.id.caoId);
        checkBoxGato = findViewById(R.id.gatoId);
        checkBoxPapagaio = findViewById(R.id.papagaioId);
        mostrar = findViewById(R.id.mostraId);
        textExibicao = findViewById(R.id.textoMostrar);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itensSelecionados = "";
                itensSelecionados += "Item:" + checkBoxCao.getText() + "--Status: " + checkBoxCao.isChecked() + "\n";
                itensSelecionados += "Item:" + checkBoxGato.getText() + "Status: " + checkBoxGato.isChecked() + "\n";
                itensSelecionados += "Item:" + checkBoxPapagaio.getText() + "Status: " + checkBoxPapagaio.isChecked() + "\n";
                textExibicao.setText(itensSelecionados);
            }
        });
    }
}
