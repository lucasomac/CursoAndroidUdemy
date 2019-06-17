package br.com.lucolimac.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference referenciaDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userReference = referenciaDatabase.child("usuarios");
    private DatabaseReference productReference = referenciaDatabase.child("produtos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Usuario usuario = new Usuario();
        usuario.setNome("Mateus");
        usuario.setIdade(24);
        usuario.setSexo("M");
        usuario.setSobrenome("Macedo");
        Produto produto = new Produto();
        produto.setCor("Branco");
        produto.setDescricao("Notebook");
        produto.setFabricante("Positivo");
        productReference.setValue(produto);
        userReference.setValue(usuario);
    }
}
