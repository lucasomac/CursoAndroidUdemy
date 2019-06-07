package br.com.lucolimac.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase banco = openOrCreateDatabase("app", MODE_PRIVATE, null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");
//            banco.execSQL("DROP TABLE pessoas");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Lucas', 26)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Luan', 24)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Mateus', 24)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Marlison', 22)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Lucian', 15)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Raimunda', 60)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Geise', 72)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Selma', 85)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Victor', 36)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Maicon', 56)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Shenna', 25)");
//            banco.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Rafael', 34)");

            banco.execSQL("UPDATE pessoas SET nome = 'Luciano' WHERE id=5");
//            banco.execSQL("DELETE FROM pessoas WHERE nome='Lucian'");
            @SuppressLint("Recycle")
//            Cursor cursor = banco.rawQuery("SELECT nome, idade FROM pessoas WHERE idade > 30 AND nome = 'Raimunda'", null);
                    Cursor cursor = banco.rawQuery("SELECT * FROM pessoas WHERE nome LIKE '%l%'", null);
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("RESULTADO - id: ", cursor.getString(indiceId));
                Log.i("RESULTADO - nome: ", cursor.getString(indiceNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
