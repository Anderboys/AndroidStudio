package com.example.administrador.myapplication;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import OpenHelper.SQLiteOpenHelper;
public class RegistarUsu extends ActionBarActivity{
    Button btngrabar;
    EditText txtNom,txtApe,txtdirec,txtfono,txtdni;
    TextView tvfecha,fecharegistro;
    DatePickerDialog datePickerDialog;
    CheckBox chk1,chk2;
    String estado="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_usu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final SQLiteOpenHelper helper=new SQLiteOpenHelper(this,"BD1",null,1);

        txtNom=(EditText)findViewById(R.id.txtNom);
        txtApe=(EditText)findViewById(R.id.txtApellido);
        txtdirec=(EditText)findViewById(R.id.txtdireccion);
        txtfono=(EditText)findViewById(R.id.txtfono);
      //  txtestado=(EditText)findViewById(R.id.txtestado);
        txtdni=(EditText)findViewById(R.id.txtdni);
        tvfecha = (TextView)findViewById(R.id.tvfecha);
        fecharegistro = (TextView)findViewById(R.id.fecharegistro);
        btngrabar=(Button)findViewById(R.id.btngrabar);

        chk1=(CheckBox)findViewById(R.id.chk1);
        chk2=(CheckBox)findViewById(R.id.chk2);

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    estado="Soltero(a)";
                    chk2.setEnabled(false);
                }else {
                    chk2.setEnabled(true);
                }
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    estado="Casado(a)";
                    chk1.setEnabled(false);
                }else {
                    chk1.setEnabled(true);
                }
            }
        });

        tvfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                datePickerDialog = new DatePickerDialog(RegistarUsu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,monthOfYear,dayOfMonth);
                        fecharegistro.setText(simpleDateFormat.format(newDate.getTime()));
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btngrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtNom.getText().toString().isEmpty()) {
                    txtNom.setError("Nombre Obligatorio");
                    txtNom.requestFocus();
                }else if(txtApe.getText().toString().isEmpty()){
                    txtApe.setError("Apellidos Obligatorio");
                    txtApe.requestFocus();
                }else if(txtdirec.getText().toString().isEmpty()){
                    txtdirec.setError("Direccion Obligatorio");
                    txtdirec.requestFocus();
                }else if(txtfono.getText().toString().isEmpty()){
                    txtfono.setError("Telefono Obligatorio");
                    txtfono.requestFocus();

                }else if(chk1.isChecked()==false && chk2.isChecked()==false ){
                    Toast.makeText(RegistarUsu.this, "Selecionar Estado", Toast.LENGTH_SHORT).show();

                }else if(txtdni.getText().toString().isEmpty()){
                    txtdni.setError("DNI Obligatorio");
                    txtdni.requestFocus();
                }else if(fecharegistro.getText().toString().isEmpty()){
                    tvfecha.setError("fecha Obligatorio");
                }else {
                    helper.abrir();
                    insertarReg(txtNom.getText()+"",txtApe.getText()+"",txtdirec.getText()+"",txtfono.getText()+"",estado,txtdni.getText()+"",fecharegistro.getText()+"");
                    helper.cerrar();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    public void insertarReg(String nom,String ape, String dir,String telefono,String estado, String dni,String fecha){
        final SQLiteOpenHelper admin=new SQLiteOpenHelper(this,"BD1",null,1);
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put("nombre",nom);
        valores.put("apellido",ape);
        valores.put("direccion",dir);
        valores.put("telefono",telefono);
        valores.put("estado",estado);
        valores.put("dni",dni);
        valores.put("fecha",fecha);
        database.insert("usuarios" ,null,valores);
    }

}
