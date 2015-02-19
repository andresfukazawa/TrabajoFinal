package com.example.susumu.trabajofinal.dao;

import android.database.Cursor;

import com.example.susumu.trabajofinal.entities.Usuario;

/**
 * Created by Susumu on 2/16/2015.
 */
public class UsuarioDAO {

    public Usuario getUsuarioByUserId(String userId, String password) {

        Cursor cursor   = null;
        Usuario usuario = null;

        try{
            cursor = DataBaseHelper.myDataBase.query("usuarios", null, "userId=? and password=?", new String[]{userId, password}, null, null, null);

            if(cursor.moveToFirst()) {
                do {
                    usuario = new Usuario();
                    usuario.setUsuario(userId);
                    usuario.setNombre(cursor.isNull(cursor.getColumnIndex("nombre")) ? "" : cursor.getString(cursor.getColumnIndex("nombre")));
                    usuario.setApellido(cursor.isNull(cursor.getColumnIndex("apellido")) ? "" : cursor.getString(cursor.getColumnIndex("apellido")));

                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return usuario;

    }
}
