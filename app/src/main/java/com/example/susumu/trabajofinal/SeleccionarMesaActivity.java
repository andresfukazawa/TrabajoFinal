package com.example.susumu.trabajofinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.susumu.trabajofinal.dao.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Susumu on 2/18/2015.
 */
public class SeleccionarMesaActivity extends ActionBarActivity {

    private Spinner spMesa;
    private Button  btTomarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selmesa);

        spMesa        = (Spinner)findViewById(R.id.spMesas);
        btTomarPedido = (Button)findViewById(R.id.btIOTomarPedido);

//        try {
//            DataBaseHelper dataBaseHelper = new DataBaseHelper(SeleccionarMesaActivity.this);
//            dataBaseHelper.createDataBase();
//            dataBaseHelper.openDataBase();
//        } catch (Exception ex) {
//            Toast.makeText(SeleccionarMesaActivity.this, "No se pudo copiar la BD", Toast.LENGTH_SHORT).show();
//        }

        btTomarPedido.setOnClickListener(btTomarPedidoOnClick);
        populateSpinner();

    }

    View.OnClickListener btTomarPedidoOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    private void populateSpinner() {

        List<String> list = new ArrayList<String>();
        Cursor cursor = null;

        try{
            Toast.makeText(SeleccionarMesaActivity.this, "point 0", Toast.LENGTH_SHORT).show();
            cursor = DataBaseHelper.myDataBase.query("mesas", new String[]{"idx"}, null, null, null, null, "idx");
            Toast.makeText(SeleccionarMesaActivity.this, "point 1", Toast.LENGTH_SHORT).show();
            if(cursor.moveToFirst()) {
                Toast.makeText(SeleccionarMesaActivity.this, "point 2", Toast.LENGTH_SHORT).show();
                do {
                    Toast.makeText(SeleccionarMesaActivity.this, "point 3", Toast.LENGTH_SHORT).show();
                    list.add("Mesa " + Integer.valueOf(cursor.getInt(cursor.getColumnIndex("idx"))));
                    Toast.makeText(SeleccionarMesaActivity.this, Integer.valueOf(cursor.getInt(cursor.getColumnIndex("idx"))), Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
                Toast.makeText(SeleccionarMesaActivity.this, "point 4", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(SeleccionarMesaActivity.this, "point 5", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(SeleccionarMesaActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
            Log.d("Error", ex.toString());
        } finally {
            if (cursor != null)
                cursor.close();
        }

        Toast.makeText(SeleccionarMesaActivity.this, "Finish", Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMesa.setAdapter(dataAdapter);
    }
}
