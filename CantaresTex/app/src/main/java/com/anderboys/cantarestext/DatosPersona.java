package com.anderboys.cantarestext;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DatosPersona extends AppCompatActivity {

     ListView lvPersona;
     Persona[] datos = new Persona[]{

            new Persona("Giovana","Suyo",R.drawable.p2),
            new Persona("Ander","Calcina",R.drawable.p1),
            new Persona("Gabriela","Suyo",R.drawable.p3),
            new Persona("Samuel","Yesquen Rodriguez",R.drawable.p4),
            new Persona("Aaron","Villalva (hehehe)",R.drawable.p5),
            new Persona("Carlos","Flores",R.drawable.p6),
            new Persona("Luchito","Suyo RicoPelo",R.drawable.p7),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_persona);

        lvPersona = (ListView)findViewById(R.id.lvPersona);
        AdaptadorPersona adaptadorPersona = new AdaptadorPersona(this,datos);
        lvPersona.setAdapter(adaptadorPersona);


        lvPersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccion;
                seleccion = ((Persona)parent.getItemAtPosition(position)).getNombre();
                Toast.makeText(getApplicationContext(),seleccion,Toast.LENGTH_SHORT).show();
            }
        });
    }

    class AdaptadorPersona extends ArrayAdapter<Persona> {
        public AdaptadorPersona(Context context, Persona[] datos) {
            super(context,R.layout.listitempersona,datos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitempersona,null);
            //Enlazando los datos
            TextView tvNombre = (TextView)item.findViewById(R.id.textView);
            tvNombre.setText(datos[position].getNombre());

            TextView tvApellido = (TextView)item.findViewById(R.id.textView2);
            tvApellido.setText(datos[position].getApellido());

            ImageView img = (ImageView)item.findViewById(R.id.imageView);
            img.setImageResource(datos[position].getImg());

            return (item);
        }
    }
}
