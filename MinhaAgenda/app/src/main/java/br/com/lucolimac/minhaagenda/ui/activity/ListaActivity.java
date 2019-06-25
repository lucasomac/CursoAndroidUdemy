package br.com.lucolimac.minhaagenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import br.com.lucolimac.minhaagenda.R;
import br.com.lucolimac.minhaagenda.data.model.Contato;
import br.com.lucolimac.minhaagenda.ui.adapter.ListaAdapter;

public class ListaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listaContatosView = findViewById(R.id.lista_lista_contatos);
        listaContatosView.setAdapter(new ListaAdapter(this, getContatos()));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public ArrayList<Contato> getContatos() {
        ArrayList<Contato> contatos = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Contato contato = new Contato();
            contato.setNome("Nome contato: " + i);
            contato.setEmail("Email contato: " + i);
            contatos.add(contato);
        }
        return contatos;
    }
}
