package com.anderboys.cantarestext;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NostrosInfo extends AppCompatActivity {

    Button btncontactenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nostros_info);

        btncontactenos = (Button)findViewById(R.id.btncontactenos);

        btncontactenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:928483855"));
                startActivity(intent);


            }
        });
    }
}
