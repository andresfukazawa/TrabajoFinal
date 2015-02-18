package com.example.susumu.trabajofinal;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Susumu on 2/17/2015.
 */
public class MainMenuActivity extends ActionBarActivity {

    private TextView tvBienvenido;
    private Button btTomarPedido, btPendientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        tvBienvenido  = (TextView)findViewById(R.id.tvMMBienvenido);
        btTomarPedido = (Button)findViewById(R.id.btMMTomarOrden);
        btPendientes  = (Button)findViewById(R.id.btMMPendientes);

        tvBienvenido.setText("Bienvenido " + getIntent().getExtras().getString("nombreCompleto"));

        btTomarPedido.setOnClickListener(btTomarPedidoOnClick);
        btPendientes.setOnClickListener(btPendientesOnClick);
    }

    View.OnClickListener btTomarPedidoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btPendientesOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
