package br.com.lucolimac.minhaagenda.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

import br.com.lucolimac.minhaagenda.BuildConfig;
import br.com.lucolimac.minhaagenda.R;
import br.com.lucolimac.minhaagenda.Utils.ImagemUtils;
import br.com.lucolimac.minhaagenda.Utils.MascarasUtil;
import br.com.lucolimac.minhaagenda.data.dao.ContatoDao;
import br.com.lucolimac.minhaagenda.data.model.Contato;

public class CadastroActivity extends AppCompatActivity {
    public static final String PARAMETRO_CONTATO = "PARAMETRO_CONTATO";
    public static final int CAMERA_REQUEST_CODE = 825;
    private Contato contato;
    private EditText viewNome;
    private EditText viewEmail;
    private EditText viewTelefone;
    private ImageView viewImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        contato = new Contato();
        viewNome = findViewById(R.id.cadastra_nome);
        viewEmail = findViewById(R.id.cadastra_email);
        viewTelefone = findViewById(R.id.cadastra_telefone);
        viewImagem = findViewById(R.id.cadastra_imagem);

        Intent intent = getIntent();
        contato = new Contato();
        if (intent.hasExtra(PARAMETRO_CONTATO)) {
            contato = (Contato) intent.getSerializableExtra(PARAMETRO_CONTATO);
            populartela();
        }
        viewImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarCamera();
            }
        });
        MascarasUtil.colocaMascara(viewTelefone, "(NN) NNNNN-NNNN");
    }

    private void chamarCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String caminhoImagem = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        contato.setImagem(caminhoImagem);
        File foto = new File(caminhoImagem);
        intent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", foto));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ImagemUtils.setImagem(viewImagem, contato.getImagem());
        }
    }

    private void populartela() {
        viewNome.setText(contato.getNome());
        viewEmail.setText(contato.getEmail());
        viewTelefone.setText(contato.getTelefone());
        ImagemUtils.setImagem(viewImagem, contato.getImagem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_cadastro_salva) {
            salvarContato();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarContato() {
        pegaValoresTela();
        ContatoDao dao = new ContatoDao(this);
        if (contato.getId() == 0) {
            dao.insere(contato);
        } else {
            dao.edita(contato);
        }
        dao.close();
        finish();
    }

    private void pegaValoresTela() {
        contato.setNome(viewNome.getText().toString());
        contato.setEmail(viewEmail.getText().toString());
        contato.setTelefone(viewTelefone.getText().toString());
    }
}
