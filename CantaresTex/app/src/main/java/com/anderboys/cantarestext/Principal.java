package com.anderboys.cantarestext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    Button btnonostros,btnpersonal,btnproductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        btnpersonal = (Button)findViewById(R.id.btnpersonal);
        btnonostros = (Button)findViewById(R.id.btnnostros);
        btnproductos = (Button)findViewById(R.id.btnproductos);

        btnpersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),DatosPersona.class);
                startActivity(i);

            }
        });

        btnonostros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),NostrosInfo.class);
                startActivity(i);
            }
        });

        btnproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),DatosProducto.class);
                startActivity(i);

            }
        });



    }
}
