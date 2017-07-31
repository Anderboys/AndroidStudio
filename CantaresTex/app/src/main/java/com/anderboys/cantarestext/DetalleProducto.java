package com.anderboys.cantarestext;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleProducto extends AppCompatActivity {

    TextView TVnombre,TVdescrip,TVprecio,TVcant;
    ImageView imgproducto;
    Spinner spdistrito,sptalla;
    Button btnmas,btnmenos,btncomprar;
    Integer precio=0,cantidad=1,s=+1,r=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_producto);

        TVnombre = (TextView)findViewById(R.id.TVnombre);
        TVdescrip = (TextView)findViewById(R.id.TVdescrip);
        TVprecio = (TextView)findViewById(R.id.TVprecio);
        TVcant = (TextView)findViewById(R.id.TVcant);
        imgproducto = (ImageView)findViewById(R.id.imgproducto);
        spdistrito = (Spinner)findViewById(R.id.spdistrito);
        sptalla = (Spinner)findViewById(R.id.sptalla);
        btnmas = (Button)findViewById(R.id.btnmas);
        btnmenos = (Button)findViewById(R.id.btnmenos);
        btncomprar = (Button)findViewById(R.id.btncomprar);

        // OBTENIENDO EXTRAS
        Bundle b = this.getIntent().getExtras();

        String nombre = b.getString("NOMBRE");
        TVnombre.setText(nombre);

        String descripcion = b.getString("DESCRIPCION");
        TVdescrip.setText(descripcion);

        final int precio1 = b.getInt("PRECIO1");
        final int precio2 = b.getInt("PRECIO2");
        final int precio3 = b.getInt("PRECIO3");

        int img = b.getInt("IMAGEN");
        imgproducto.setImageResource(img);

        //------------------------------------------
        // CARGANDO DATOS EN LOS SPINERS

        String[] distrito = {"Cercado de Lima","Ate","Barranco","Breña","Comas","Chorrillos","El Agustino","Jesús María","La Molina","La Victoria","Lince","Magdalena del Mar",
                "Miraflores","Pueblo Libre","Puente Piedra","Rimac","San Isidro","Independencia","San Juan de Miraflores","San Luis","San Martin de Porres","San Miguel",
                "Santiago de Surco","Surquillo","Villa María del Triunfo","San Juan de Lurigancho","Santa Rosa","Los Olivos","San Borja","Villa El Savador","Santa Anita"};

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,distrito);
        spdistrito.setAdapter(adapter);

        String[] tallas = {"small","medium","large"};
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,tallas);
        sptalla.setAdapter(adapter1);



        TVcant.setText(cantidad+"");

        sptalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        precio = precio1;
                        TVprecio.setText("Precio: /S. " + precio + "");
                        break;
                    case 1:
                        precio = precio2;
                        TVprecio.setText("Precio: /S. " + precio + "");
                        break;
                    case 2:
                        precio = precio3;
                        TVprecio.setText("Precio: /S. " + precio + "");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cantidad = cantidad+r;
                TVcant.setText(cantidad+"");

                if (cantidad==0) {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(DetalleProducto.this);
                    alert.setTitle("Alerta!");
                    alert.setMessage("Usted ha seleccionado: " +cantidad);
                    alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Evento a llamar
                        }
                    }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.create();
                    alert.show();
                }
            }
        });

        btnmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cantidad = cantidad+s;
                TVcant.setText(cantidad+"");

            }
        });

        btncomprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seleccion = sptalla.getSelectedItem().toString();

                if(seleccion=="small"){
                    AlertDialog.Builder alert = new AlertDialog.Builder(DetalleProducto.this);
                    alert.setTitle("Cuenta!");
                    alert.setMessage("El monto a pagar es: /S." +precio1*cantidad);
                    alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Su pedido ha sido enviado"  ,Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.create();
                    alert.show();

                }else if (seleccion=="medium"){
                    final AlertDialog.Builder alert = new AlertDialog.Builder(DetalleProducto.this);
                    alert.setTitle("Cuenta!");
                    alert.setMessage("El monto es: /S." +precio2*cantidad);
                    alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Su pedido ha sido enviado"  ,Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.create();
                    alert.show();
                }else {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(DetalleProducto.this);
                    alert.setTitle("Cuenta!");
                    alert.setMessage("El monto es: /S." +precio3*cantidad);
                    alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Su pedido ha sido enviado"  ,Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.create();
                    alert.show();
                }



            }
        });






    }


}
