package com.example.administrador.myapplication;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import Beans.UsuarioBeans;
import OpenHelper.SQLiteOpenHelper;

public class DetallesUsu extends ActionBarActivity {

    TextView tvid,tvnombre,tvapellido,tvdireccion,tvfono,tvestado,tvdni,tvdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_usu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvid=(TextView)findViewById(R.id.tvid);
        tvnombre=(TextView)findViewById(R.id.tvnombre);
        tvapellido=(TextView)findViewById(R.id.tvapellido);
        tvdireccion=(TextView)findViewById(R.id.tvdireccion);
        tvfono=(TextView)findViewById(R.id.tvfono);
        tvestado=(TextView)findViewById(R.id.tvestado);
        tvdni=(TextView)findViewById(R.id.tvdni);
        tvdate=(TextView)findViewById(R.id.tvdate);


        Bundle recibe = this.getIntent().getExtras();

        int idd = recibe.getInt("ID");
        tvid.setText(idd+"");

        String nombre = recibe.getString("NOMBRE");
        tvnombre.setText(nombre);

        String apellido = recibe.getString("APELLIDO");
        tvapellido.setText(apellido);

        String direccion = recibe.getString("DISTRITO");
        tvdireccion.setText(direccion);

        String telefono = recibe.getString("TELEFONO");
        tvfono.setText(telefono);

        String estado = recibe.getString("ESTADO");
        tvestado.setText(estado);

        String dni = recibe.getString("DNI");
        tvdni.setText(dni);

        String fecha = recibe.getString("FECHAREGISTRO");
        tvdate.setText(fecha);
    }


    // 1.  ------------ this is For ACTION BAR -------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.editt) {

            Intent intent = new Intent(getApplicationContext(),EditarUsu.class);

            Integer id2 = Integer.parseInt(tvid.getText().toString()) ;
            String nombre2 = tvnombre.getText().toString();
            String apellido2 = tvapellido.getText().toString();
            String direccion2 = tvdireccion.getText().toString();

            String telefono2 = tvfono.getText().toString();
            String estado2 = tvestado.getText().toString();
            String dni2 = tvdni.getText().toString();
            String date2 = tvdate.getText().toString();


            Bundle b = new Bundle();
            b.putInt("ID2",id2);
            b.putString("NOMBRE2",nombre2);
            b.putString("APELLIDO2",apellido2);
            b.putString("DISTRITO2",direccion2);

            b.putString("TELEFONO2",telefono2);
            b.putString("ESTADO2",estado2);
            b.putString("DNI2",dni2);
            b.putString("DATE2",date2);
            intent.putExtras(b);

            startActivity(intent);

            return true;
        }

        if (id == R.id.delete) {

            String apellido = tvapellido.getText().toString();

            AlertDialog.Builder alert = new AlertDialog.Builder(DetallesUsu.this);
            alert.setTitle("Alerta!");
            alert.setMessage("Desea Eliminar a ? " +apellido);
            alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Integer idusu = Integer.parseInt(tvid.getText().toString());
                    eliminar(idusu);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }
            }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.create();
            alert.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------


//-------------------   METODO ELIMINAR USUARIO CON SQLite --------------

    public String eliminar(Integer idusu){
        final SQLiteOpenHelper admin=new SQLiteOpenHelper(this,"BD1",null,1);
        String mensaje ="";
        SQLiteDatabase database = admin.getWritableDatabase();
        int cantidad =database.delete("usuarios", "_ID='" + idusu + "'", null);
        if (cantidad !=0){
            mensaje="Eliminado Correctamente";
        }
        else{
            mensaje = "No existe";
        }
        database.close();
        return mensaje;
    }

    //..............................................................


}
