package br.com.lucolimac.minhaagenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.lucolimac.minhaagenda.R;
import br.com.lucolimac.minhaagenda.data.model.Contato;

public class ListaAdapter extends BaseAdapter {
    private ArrayList<Contato> contatos;
    private Context context;

    public ListaAdapter(Context context, ArrayList<Contato> contatos) {
        this.contatos = contatos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lista_contatos, viewGroup, false);
        }
        Contato contato = getItem(i);
        TextView viewNome = view.findViewById(R.id.item_lista_contato_nome);
        TextView viewEmail = view.findViewById(R.id.item_lista_contato_email);
        ImageView viewImage = view.findViewById(R.id.item_lista_contato_imagem);

        viewNome.setText(contato.getNome());
        viewEmail.setText(contato.getEmail());
        return view;
    }
}
