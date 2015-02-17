package com.example.susumu.trabajofinal.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.administrator.ejercicio1.entities.Alumno;

/**
 * Created by Administrator on 14/02/2015.
 */
public class AlumnoDAO {

    public Alumno getAlumnoById(int IdAlumno) {
        Cursor cursor = null;
        Alumno alumno = null;

        try{
            cursor = DataBaseHelper.myDataBase.query("Alumno", null, "IdAlumno = ?", new String[]{String.valueOf(IdAlumno)}, null, null, null);

            if(cursor.moveToFirst()) {
                do {
                    alumno = new Alumno();
                    alumno.setIdAlumno(IdAlumno);
                    alumno.setNombre(cursor.isNull(cursor.getColumnIndex("Nombre"))?"":cursor.getString(cursor.getColumnIndex("Nombre")));
                    alumno.setApellido(cursor.isNull(cursor.getColumnIndex("Apellido"))?"":cursor.getString(cursor.getColumnIndex("Apellido")));
                    alumno.setEdad(cursor.isNull(cursor.getColumnIndex("Edad"))?0:cursor.getInt(cursor.getColumnIndex("Edad")));
                    alumno.setDNI(cursor.isNull(cursor.getColumnIndex("DNI"))?"":cursor.getString(cursor.getColumnIndex("DNI")));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           if (cursor != null)
               cursor.close();
        }

        return alumno;
    }

    public void insertAlumno(Alumno alumno) {

        Log.d("Nombre : ", alumno.getNombre().toString());
        Log.d("Apellido : ", alumno.getApellido().toString());
        Log.d("Edad : ", String.valueOf(alumno.getEdad()));
        Log.d("DNI : ", alumno.getDNI().toString());

        try {
            ContentValues cv = new ContentValues();
            cv.put("Nombre", alumno.getNombre());
            cv.put("Apellido", alumno.getApellido());
            cv.put("Edad", alumno.getEdad());
            cv.put("DNI", alumno.getDNI());
            DataBaseHelper.myDataBase.insert("Alumno", null, cv);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateAlumno(Alumno alumno) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("Nombre", alumno.getNombre());
            cv.put("Apellido", alumno.getApellido());
            cv.put("Edad", alumno.getEdad());
            cv.put("DNI", alumno.getDNI());
            DataBaseHelper.myDataBase.update("Alumno", cv, "IdAlumno =?", new String[] {String.valueOf(alumno.getIdAlumno())});

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAlumno(Alumno alumno) {

        try {
            ContentValues cv = new ContentValues();
//            cv.put("Nombre", alumno.getNombre());
//            cv.put("Apellido", alumno.getApellido());
//            cv.put("Edad", alumno.getEdad());
//            cv.put("DNI", alumno.getDNI());
            DataBaseHelper.myDataBase.delete("Alumno", "IdAlumno =?", new String[]{String.valueOf(alumno.getIdAlumno())});

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
