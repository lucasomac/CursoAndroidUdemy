package br.com.lucolimac.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listaItens;
    private String[] itens = {"Angra dos Reis", "Caldas Novas", "Campos do Jordão",
            "Costa do Sauípe", "Ilhéus", "Porto Seguro", "Rio de Janeiro", "Tiradentes",
            "Praga", "Santiago", "Zurique", "Caribe", "Buenos Aires", "Budapest", "Cancun", "Aruba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaItens = findViewById(R.id.listViewId);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, itens);
        listaItens.setAdapter(adaptador);
        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int codigoPosicao = position;
                String valorClicado = listaItens.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), valorClicado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
