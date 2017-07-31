package com.example.administrador.myapplication;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import OpenHelper.SQLiteOpenHelper;


public class EditarUsu extends AppCompatActivity {
    TextView tvidd;

    EditText edtnombre,edtapellido,edtdireccion;

    EditText edttelefono,edtestado,edtdni;
    TextView tvfecha2,fecharegistro2;

    Button btneditar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_usu);


        tvidd = (TextView)findViewById(R.id.tvidd);
        edtnombre = (EditText)findViewById(R.id.edtnombre);
        edtapellido = (EditText)findViewById(R.id.edtapellido);
        edtdireccion = (EditText)findViewById(R.id.edtdireccion);

        edttelefono = (EditText)findViewById(R.id.edttelefono);
        edtestado = (EditText)findViewById(R.id.edtestado);
        edtdni = (EditText)findViewById(R.id.edtdni);

        tvfecha2 = (TextView)findViewById(R.id.tvfecha2);
        fecharegistro2 = (TextView)findViewById(R.id.fecharegistro2);

        btneditar = (Button)findViewById(R.id.btneditar);


        Bundle recibe = this.getIntent().getExtras();

        int idd = recibe.getInt("ID2");
        tvidd.setText(idd+"");

        String nombre = recibe.getString("NOMBRE2");
        edtnombre.setText(nombre);

        String apellido = recibe.getString("APELLIDO2");
        edtapellido.setText(apellido);

        String distrito = recibe.getString("DISTRITO2");
        edtdireccion.setText(distrito);

        String telefono = recibe.getString("TELEFONO2");
        edttelefono.setText(telefono);

        String estado = recibe.getString("ESTADO2");
        edtestado.setText(estado);

        String dni = recibe.getString("DNI2");
        edtdni.setText(dni);

        String fecha = recibe.getString("DATE2");
        fecharegistro2.setText(fecha);


        tvfecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                datePickerDialog = new DatePickerDialog(EditarUsu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,monthOfYear,dayOfMonth);
                        fecharegistro2.setText(simpleDateFormat.format(newDate.getTime()));
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer idusu = Integer.parseInt(tvidd.getText().toString());
                String nombre = edtnombre.getText().toString();
                String apellido = edtapellido.getText().toString();
                String direccion = edtdireccion.getText().toString();

                String telefono = edttelefono.getText().toString();
                String estado = edtestado.getText().toString();
                String dni = edtdni.getText().toString();
                String fecha = fecharegistro2.getText().toString();

                actualizar(idusu,nombre,apellido,direccion,telefono,estado,dni,fecha);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }


    public  String actualizar(Integer idusuario,String Nombre,String Apellido,String Direccion,String Telefono,String Estado,String Dni,String Fecha){

        final SQLiteOpenHelper admin=new SQLiteOpenHelper(this,"BD1",null,1);
        String Mensaje ="";
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("_ID",idusuario);
        contenedor.put("nombre",Nombre);
        contenedor.put("apellido",Apellido);
        contenedor.put("direccion",Direccion);

        contenedor.put("telefono",Telefono);
        contenedor.put("estado",Estado);
        contenedor.put("dni",Dni);
        contenedor.put("fecha",Fecha);

        int cantidad = database.update("usuarios", contenedor, "_ID= '" + idusuario + "' ", null);
        if(cantidad!=0){
            Mensaje="Actualizado Correctamente";
        }else{
            Mensaje="Error al Actualizar";
        }
        database.close();
        return Mensaje;
    }

    // 1.  ------------ this is For ACTION BAR -------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.noGuardar) {
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------
}
