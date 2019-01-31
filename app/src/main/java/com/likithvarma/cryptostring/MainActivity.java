package com.likithvarma.cryptostring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button encrypt,decrypt;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encrypt = findViewById(R.id.encrypt);
        decrypt = findViewById(R.id.decrypt);
        getSupportActionBar().setTitle(R.string.title);//  setting the app title
        i=new Intent(this,Main2Activity.class);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("Id","enc");
                startActivity(i);
            }
        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("Id","dec");
                startActivity(i);
            }
        });
    }


}
