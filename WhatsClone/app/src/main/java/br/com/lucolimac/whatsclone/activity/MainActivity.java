package br.com.lucolimac.whatsclone.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.lucolimac.whatsclone.R;

public class MainActivity extends AppCompatActivity {
//    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
