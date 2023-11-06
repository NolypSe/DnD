package com.example.dnd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    SQLiteDatabase bd;
    ArrayList<Usuario>lista = new ArrayList<Usuario>();
    Usuario c;
    Context ct;
    String nombreBD= "BDDnd";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, password text)";


    public daoUsuario(Context c){

        this.ct=c;
        bd=c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Usuario c){

        ContentValues contenedor = new ContentValues();
        contenedor.put("usuario", c.getUsuario());
        contenedor.put("password", c.getPassword());
        return (bd.insert("usuario", null, contenedor))>0;
    }

    public boolean login(String usuario, String password) {
        String[] columns = {"id"};
        String selection = "usuario = ? AND password = ?";
        String[] selectionArgs = {usuario, password};

        Cursor cursor = bd.query("usuario", columns, selection, selectionArgs, null, null, null);

        boolean loginSuccessful = cursor.moveToFirst();
        cursor.close();

        return loginSuccessful;
    }

    public boolean eliminar(int id){

        return (bd.delete("usuario","id="+id,null ))>0;
    }

    public boolean editar(Usuario c){

        ContentValues contenedor = new ContentValues();
        contenedor.put("usuario", c.getUsuario());
        contenedor.put("password", c.getPassword());
        return (bd.update("usuario", contenedor, "id="+c.getId(),null))>0;
    }

    public ArrayList<Usuario>verTodo(){

        lista.clear();
        Cursor cursor = bd.rawQuery("select * from usuario", null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Usuario(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public Usuario verUno(int posicion){

        Cursor cursor = bd.rawQuery("select * from usuario", null);
        cursor.moveToPosition(posicion);
        c=new Usuario(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2));
        return c;
    }

    public int getIdDeUsuario(String usuario) {
        String[] columns = {"id"};
        String selection = "usuario = ?";
        String[] selectionArgs = {usuario};

        Cursor cursor = bd.query("usuario", columns, selection, selectionArgs, null, null, null);

        int usuarioId = -1;

        if (cursor.moveToFirst()) {
            usuarioId = cursor.getInt(0);
        }

        cursor.close();

        return usuarioId;
    }
}

