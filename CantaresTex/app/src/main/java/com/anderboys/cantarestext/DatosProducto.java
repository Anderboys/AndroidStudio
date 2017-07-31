package com.anderboys.cantarestext;

import android.content.Context;
import android.content.Intent;
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

public class DatosProducto extends AppCompatActivity {

    private ListView LVproducto;
    private Producto[] datos = new Producto[]{

            new Producto("camisa slim fit negro","son camisas entalladas,con cuello italiano,Color:Negro, para el hombre atento a la moda,  100% calidad. CantaresTex",35,38,45,R.drawable.p8),
            new Producto("camisa slim fit Azul","son camisas entalladas,con  cuello francés,Color:Azul, para el hombre atento a la moda,  100% calidad. CantaresTex",32,39,48,R.drawable.p9),
            new Producto("camisa slim fit Blanco","son camisas entalladas,con cuello Coreano,Color:Blanco, para el hombre atento a la moda,  100% calidad. CantaresTex",30,35,43,R.drawable.p10),
            new Producto("pantalon slim fit negro","Pantalon entallado,Color:Negro, para el hombre atento a la moda,  100% calidad. CantaresTex",20,25,35,R.drawable.p11),
            new Producto("saco slim fit negro","Saco entallado,con cuello Coreano,Color:Negro, para el hombre atento a la moda,  100% calidad. CantaresTex",70,75,80,R.drawable.p12),
            new Producto("saco slim fit plata","Saco entallado,con cuello Coreano,Color:Plata, para el hombre atento a la moda,  100% calidad. CantaresTex",15,20,25,R.drawable.p13),
            new Producto("pantalon slim fit plata","Pantalon entallado,Color:Plata, para el hombre atento a la moda,  100% calidad. CantaresTex",85,90,95,R.drawable.p14),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_producto);

        LVproducto = (ListView)findViewById(R.id.listview);

        AdaptadorPizza adaptadorPizza = new AdaptadorPizza(this,datos);
        LVproducto.setAdapter(adaptadorPizza);

        LVproducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),DetalleProducto.class);

              String nombre;
              nombre =((Producto)parent.getItemAtPosition(position)).getNombre();

              String descrip;
                descrip = ((Producto)parent.getItemAtPosition(position)).getDescripcion();

                Integer precio1;
                precio1 = ((Producto)parent.getItemAtPosition(position)).getP1();

                Integer precio2;
                precio2 = ((Producto)parent.getItemAtPosition(position)).getP2();

                Integer precio3;
                precio3 = ((Producto)parent.getItemAtPosition(position)).getP3();

                Integer img;
                img = ((Producto)parent.getItemAtPosition(position)).getImg();


                Bundle b = new Bundle();

                b.putString("NOMBRE",nombre);
                b.putString("DESCRIPCION",descrip);
                b.putInt("PRECIO1",precio1);
                b.putInt("PRECIO2",precio2);
                b.putInt("PRECIO3",precio3);
                b.putInt("IMAGEN",img);

                i.putExtras(b);
                startActivity(i);
            }
        });
    }


    class AdaptadorPizza extends ArrayAdapter<Producto> {
        public AdaptadorPizza(Context context, Producto[] datos) {
            super(context,R.layout.listitemproducto,datos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View item = inflater.inflate(R.layout.listitemproducto,null);
            //Enlazando los datos
            TextView tvNombre = (TextView)item.findViewById(R.id.nombreproduct);
            tvNombre.setText(datos[position].getNombre());


            ImageView img = (ImageView)item.findViewById(R.id.imgproduct);
            img.setImageResource(datos[position].getImg());

            return (item);
        }
    }
}
