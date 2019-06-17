package br.com.lucolimac.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            Log.i("Logou: ", "Está logado " + firebaseAuth.getCurrentUser().getEmail());
        }else{
            Log.i("Logou: ", "Não logado");
        }
//        firebaseAuth.signInWithEmailAndPassword("lucasomac@outlook.com", "123456")
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.i("Login: ", "Realizado com sucesso");
//                        } else {
//                            Log.i("Login:", "Não realizado + " + task.getException());
//                        }
//                    }
//                });
//        firebaseAuth.createUserWithEmailAndPassword("lucasomac@outlook.com", "123456").addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Log.i("Cadastro:", "Realizado com sucesso");
//                } else {
//                    Log.i("Cadastro:", "Não realizado");
//                }
//            }
//        });
    }
}
