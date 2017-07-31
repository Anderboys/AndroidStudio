package com.example.administrador.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import Beans.UsuarioBeans;
import OpenHelper.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab2;
    EditText edtnombre2;
    Button btnbuscar2;
    RecyclerView recyclerview;

    CardView cardview;
    ArrayList<UsuarioBeans> datos = new ArrayList<UsuarioBeans>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtnombre2 = (EditText) findViewById(R.id.edtnombre2);
        btnbuscar2 = (Button) findViewById(R.id.btnbuscar2);
        fab2 = (FloatingActionButton) findViewById(R.id.fab);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistarUsu.class);
                startActivity(intent);
            }
        });

        btnbuscar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtnombre2.getText().toString().isEmpty()) {
                    edtnombre2.setError("Ingrese Nombre para Busqueda");
                    edtnombre2.requestFocus();
                } else {
                    String nombre = edtnombre2.getText().toString();
                    buscar(nombre);
                    edtnombre2.setText("");
                    edtnombre2.requestFocus();
                }
            }
        });

        recyclerview = (RecyclerView) findViewById(R.id.recyclerpersona);
        cardview = (CardView) findViewById(R.id.cardview);


        listar();


    }

    public void listar(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(this,"BD1",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT _ID,nombre,apellido,direccion,telefono,estado,dni,fecha FROM usuarios",null);
        datos.clear();
        UsuarioBeans oUsuarios;

        if (c.moveToFirst()){
            do {
                oUsuarios = new UsuarioBeans(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
                datos.add(oUsuarios);
            }while (c.moveToNext());
        }
        //Listar los datos en el listview
        enviarParametros();
        //   ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,datos);
        //    recyclerview.setAdapter(adaptador);
           }


    public void buscar(String nombre){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(this,"BD1",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM usuarios WHERE nombre like  '%"+nombre+"%'  ",null);
        datos.clear();
        UsuarioBeans oUsuarios;

        if (c.moveToFirst()){
            do {
                oUsuarios = new UsuarioBeans(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
                datos.add(oUsuarios);
            }while (c.moveToNext());
        }

        //Listar los datos en el listview
        enviarParametros();
    }

    //7.
    public void enviarParametros(){

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        AdapterPersonas adapterPersonas = new AdapterPersonas(datos);
        recyclerview.setAdapter(adapterPersonas);

        adapterPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),DetallesUsu.class);

                int idd;
                idd = (datos.get(recyclerview.getChildAdapterPosition(v)).getId());

                String nombre;
                nombre = (datos.get(recyclerview.getChildAdapterPosition(v)).getNom());

                String apellido;
                apellido = (datos.get(recyclerview.getChildAdapterPosition(v)).getApe());

                String direc;
                direc = (datos.get(recyclerview.getChildAdapterPosition(v)).getDireccion());

                String fono;
                fono = (datos.get(recyclerview.getChildAdapterPosition(v)).getTelefono());

                String estado;
                estado = (datos.get(recyclerview.getChildAdapterPosition(v)).getEstado());

                String dni;
                dni = (datos.get(recyclerview.getChildAdapterPosition(v)).getDni());

                String fecha;
                fecha = (datos.get(recyclerview.getChildAdapterPosition(v)).getFecharegistro());

                Bundle b = new Bundle();
                b.putInt("ID",idd);
                b.putString("NOMBRE",nombre);
                b.putString("APELLIDO",apellido);
                b.putString("DISTRITO",direc);
                b.putString("TELEFONO",fono);
                b.putString("ESTADO",estado);
                b.putString("DNI",dni);
                b.putString("FECHAREGISTRO",fecha);
                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }

    // 1.  ------------ this is For ACTION BAR -------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {

            listar();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------







}
