package br.com.lucolimac.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference referenciaDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userReference = referenciaDatabase.child("usuarios");
    private DatabaseReference productReference = referenciaDatabase.child("produtos").child("001");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Usuario usuario = new Usuario();
//        usuario.setNome("Mateus");
//        usuario.setIdade(24);
//        usuario.setSexo("M");
//        usuario.setSobrenome("Macedo");
//        Produto produto = new Produto();
//        produto.setCor("Branco");
//        produto.setDescricao("Notebook");
//        produto.setFabricante("Positivo");
//        productReference.child("001").setValue(produto);
//        userReference.child("001").setValue(usuario);
        productReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FireTag ", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
