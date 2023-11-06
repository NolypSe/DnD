package com.example.dnd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoPersonaje {
    SQLiteDatabase bd;
    ArrayList<Personaje>lista = new ArrayList<Personaje>();
    Personaje c;
    Context ct;
    String nombreBD= "BDDnd";
    String tabla = "create table if not exists personaje(id integer primary key autoincrement, nombre text, raza text, clase text, trasfondo text, genero text, usuarioId INTEGER, FOREIGN KEY (usuarioId) REFERENCES usuario(id))";


    public daoPersonaje(Context c){

        this.ct=c;
        bd=c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Personaje c, int usuarioActualId){

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", c.getNombre());
        contenedor.put("raza", c.getRaza());
        contenedor.put("clase", c.getClase());
        contenedor.put("trasfondo", c.getTrasfondo());
        contenedor.put("genero", c.getGenero());
        contenedor.put("usuarioId", usuarioActualId);
        return (bd.insert("personaje", null, contenedor))>0;
    }

    public boolean eliminar(int id){

        return (bd.delete("personaje","id="+id,null ))>0;
    }

    public boolean editar(Personaje c, int usuarioActualId){

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", c.getNombre());
        contenedor.put("raza", c.getRaza());
        contenedor.put("clase", c.getClase());
        contenedor.put("trasfondo", c.getTrasfondo());
        contenedor.put("genero", c.getGenero());
        contenedor.put("usuarioId", usuarioActualId);
        return (bd.update("personaje", contenedor, "id="+c.getId(),null))>0;
    }
    public ArrayList<Personaje> verPersonajesUsuario(int usuarioId) {
        lista.clear();
        String[] columns = {"id", "nombre", "raza", "clase", "trasfondo", "genero"};
        String selection = "usuarioId=?";
        String[] selectionArgs = {String.valueOf(usuarioId)};

        Cursor cursor = bd.query("personaje", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new Personaje(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        usuarioId
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}

