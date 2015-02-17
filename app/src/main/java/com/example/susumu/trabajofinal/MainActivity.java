package com.example.susumu.trabajofinal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private EditText etNombre, etContrasena;
    private Button   btIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre     = (EditText)findViewById(R.id.etLINombre);
        etContrasena = (EditText)findViewById(R.id.etLIContrasena);
        btIngresar   = (Button)findViewById(R.id.btLIIngresar);

        btIngresar.setOnClickListener(btIngresarOnClick);
    }

    View.OnClickListener btIngresarOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    }


}
