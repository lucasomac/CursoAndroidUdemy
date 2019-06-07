package br.com.lucolimac.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText textoTarefa;
    private Button botaoAdd;
    private ListView listaTarefas;
    private SQLiteDatabase banco;
    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //Recupera componentes
            textoTarefa = findViewById(R.id.editText);
            botaoAdd = findViewById(R.id.buttonAdd);


            //Banco de Dados
            banco = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //Tabela tarefas
            banco.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");
            botaoAdd.setOnClickListener(this);

            //Recupera tarefas
            reuperaTarefa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonAdd) {
            try {
                String textoDigitado = textoTarefa.getText().toString();
                salvaTarefa(textoDigitado);
//                banco.execSQL("INSERT INTO tarefas (tarefa) VALUES ('" + textoDigitado + "')");
                textoTarefa.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void salvaTarefa(String tarefa) {
        try {
            if ("".equals(tarefa)) {
                Toast.makeText(this, "Tarefa Vazia", Toast.LENGTH_SHORT).show();
            } else {
                banco.execSQL("INSERT INTO tarefas (tarefa) VALUES ('" + tarefa + "')");
                Toast.makeText(this, "Tarefa Salva com Sucesso!", Toast.LENGTH_SHORT).show();
                reuperaTarefa();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reuperaTarefa() {
        //Recupera tarefas
        try {
            @SuppressLint("Recycle")
            Cursor cursor = banco.rawQuery("SELECT * FROM tarefas ORDER BY id DESC", null);
            int indiceColId = cursor.getColumnIndex("id");
            int indiceColTarefa = cursor.getColumnIndex("tarefa");
            //Cria adaptador
            listaTarefas = findViewById(R.id.recycleView);
            itens = new ArrayList<>();
            itensAdaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_2, android.R.id.text1, itens);
            listaTarefas.setAdapter(itensAdaptador);
            //Lista tarefa
            cursor.moveToFirst();
            while (cursor != null) {
//                Log.i("Resultado - ", "Tarefa: " + cursor.getString(indiceColId));
//                Log.i("Resultado - ", "Tarefa: " + cursor.getString(indiceColId));
                itens.add(cursor.getString(indiceColTarefa));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
