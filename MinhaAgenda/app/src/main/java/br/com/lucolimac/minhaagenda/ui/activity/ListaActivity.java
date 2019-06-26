package br.com.lucolimac.minhaagenda.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.lucolimac.minhaagenda.R;
import br.com.lucolimac.minhaagenda.data.dao.ContatoDao;
import br.com.lucolimac.minhaagenda.data.model.Contato;
import br.com.lucolimac.minhaagenda.ui.adapter.ListaAdapter;

public class ListaActivity extends AppCompatActivity {
    private ListView listaContatosView;
    private ArrayList<Contato> contatos;
    private static final int REQUEST_CODE_PERMISSOES = 416;
    private String[] permissoes = new String[]{
            Manifest.permission.SEND_SMS, Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validaPermissoes(permissoes);
        listaContatosView = findViewById(R.id.lista_lista_contatos);
        listaContatosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contato contato = contatos.get(i);

                Intent intent = new Intent(ListaActivity.this, CadastroActivity.class);
                intent.putExtra(CadastroActivity.PARAMETRO_CONTATO, contato);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(listaContatosView);
    }

    @Override
    protected void onPostResume() {
        criaAdapter();
        super.onPostResume();
    }

    private void criaAdapter() {
        listaContatosView.setAdapter(new ListaAdapter(this, getContatos()));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Contato contato = (Contato) listaContatosView.getItemAtPosition(info.position);
        MenuItem del = menu.add("Apagar");
        del.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ContatoDao dao = new ContatoDao(ListaActivity.this);
                dao.remove(contato);
                criaAdapter();
                dao.close();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public ArrayList<Contato> getContatos() {
        contatos = new ArrayList<>();
        ContatoDao contatoDao = new ContatoDao(this);
        contatos = contatoDao.buscaContato();
        contatoDao.close();
        return contatos;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int resultado : grantResults) {
            if (resultado == PackageManager.PERMISSION_DENIED) {
                alertaValidaPermissao();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void alertaValidaPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar este app, é necessário aceitar todas as perissões!");
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                validaPermissoes(permissoes);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void validaPermissoes(String[] permissoes) {
        List<String> listaPermissoes = new ArrayList<>();
        for (String permissao : permissoes) {
            boolean validaPermissao = ContextCompat.checkSelfPermission(this, permissao) == PackageManager.PERMISSION_GRANTED;
            if (!validaPermissao) {
                listaPermissoes.add(permissao);
            }
        }
        if (!listaPermissoes.isEmpty()) {
            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);
            ActivityCompat.requestPermissions(this, novasPermissoes, REQUEST_CODE_PERMISSOES);
        }
    }
}
