package OpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
/**
 * Created by Administrador on 31/10/2016.
 */
public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table usuarios(_ID integer primary key autoincrement," +
                " nombre text, apellido text, direccion text, telefono text, estado text, dni text, fecha text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }





    /*
    public ArrayList buscarUsu(String nombre){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM usuarios WHERE nombre like  '%"+nombre+"%'  ";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add("Codigo: "+registros.getString(0)+ "\n" +
                        "Codigo: "+registros.getString(1)+ "\n" +
                        "Nombre: "+ registros.getString(2)+ "\n" +
                        "Apellido:"+ registros.getString(3));

            }while(registros.moveToNext());
        }
        return lista;

    }

*/
}
