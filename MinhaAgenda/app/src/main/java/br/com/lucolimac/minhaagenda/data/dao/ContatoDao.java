package br.com.lucolimac.minhaagenda.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.lucolimac.minhaagenda.data.model.Contato;

public class ContatoDao extends SQLiteOpenHelper {
    public ContatoDao(Context context) {
        super(context, "agenda_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS contato(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT,email TEXT, telefone TEXT, imagem TEXT, excluido INTEGER DEFAULT 0)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Contato> buscaContato() {
        ArrayList<Contato> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM contato WHERE excluido = 0";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Contato contato = new Contato();
                contato.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                contato.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                contato.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
                contato.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));
                contato.setImagem(cursor.getString(cursor.getColumnIndexOrThrow("imagem")));
                lista.add(contato);
            }
            cursor.close();
        }
        return lista;
    }

    public void insere(Contato contato) {
        ContentValues cv = criaContentValues(contato);

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("contato", null, cv);
        contato.setId((int) id);
    }

    private ContentValues criaContentValues(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nome", contato.getNome());
        cv.put("email", contato.getEmail());
        cv.put("telefone", contato.getTelefone());
        cv.put("imagem", contato.getImagem());
        cv.put("excluido", contato.getExcluido());
        return cv;
    }

    public void edita(Contato contato) {
        ContentValues cv = criaContentValues(contato);
        String sql = "id = " + contato.getId();
        SQLiteDatabase db = getWritableDatabase();
        db.update("contato", cv, sql, null);
    }

    public void remove(Contato contato) {
        contato.setExcluido(1);
        edita(contato);
    }
}
