package com.example.administrador.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import Beans.UsuarioBeans;
import OpenHelper.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    EditText edtnombre;
    Button btnbuscar;
    ListView lstUsuarios;
    ArrayList<UsuarioBeans> datos = new ArrayList<UsuarioBeans>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lstUsuarios=(ListView)findViewById(R.id.lstUsuarios);
        edtnombre = (EditText)findViewById(R.id.edtapellido);
        btnbuscar = (Button)findViewById(R.id.btnbuscar);
        fab=(FloatingActionButton)findViewById(R.id.fab);

        listar();

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtnombre.getText().toString().isEmpty()){
                    edtnombre.setError("Ingrese Nombre para Busqueda");
                    edtnombre.requestFocus();
                }else {
                    String nombre = edtnombre.getText().toString();
                    buscar(nombre);
                    edtnombre.setText("");
                    edtnombre.requestFocus();
                }

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistarUsu.class);
                startActivity(intent);
            }
        });

    lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         //   UsuarioBeans usu=(UsuarioBeans)lstUsuarios.getItemAtPosition(position);

            Intent intent = new Intent(getApplicationContext(),DetallesUsu.class);

            int idd;
            idd = ((UsuarioBeans)parent.getItemAtPosition(position)).getId();

            String nombre;
            nombre = ((UsuarioBeans)parent.getItemAtPosition(position)).getNom();

            String apellido;
            apellido = ((UsuarioBeans)parent.getItemAtPosition(position)).getApe();

            String direc;
            direc = ((UsuarioBeans)parent.getItemAtPosition(position)).getDireccion();

            String fono;
            fono = ((UsuarioBeans)parent.getItemAtPosition(position)).getTelefono();

            String estado;
            estado = ((UsuarioBeans)parent.getItemAtPosition(position)).getEstado();

            String dni;
            dni = ((UsuarioBeans)parent.getItemAtPosition(position)).getDni();

            String fecha;
            fecha = ((UsuarioBeans)parent.getItemAtPosition(position)).getFecharegistro();

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
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,datos);
        lstUsuarios.setAdapter(adaptador);
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
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,datos);
        lstUsuarios.setAdapter(adaptador);
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
