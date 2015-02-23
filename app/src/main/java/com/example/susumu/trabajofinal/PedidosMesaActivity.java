package com.example.susumu.trabajofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susumu.trabajofinal.R;

/**
 * Created by Susumu on 2/20/2015.
 */
public class PedidosMesaActivity extends ActionBarActivity {

    private TextView tvMesa;
    private Button   btEntradas, btPFuertes, btPostres, btCalcular, btCancelar, btFacturar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidosmesa);

        tvMesa     = (TextView)findViewById(R.id.tvTOMesa);
        btEntradas = (Button)findViewById(R.id.btTOEntrada);
        btPFuertes = (Button)findViewById(R.id.btTOPlatoFuerte);
        btPostres  = (Button)findViewById(R.id.btTOPostre);
        btCalcular = (Button)findViewById(R.id.btTOCalcular);
        btCancelar = (Button)findViewById(R.id.btTOCancelar);
        btFacturar = (Button)findViewById(R.id.btTOFacturar);

        tvMesa.setText("Pedidos para la mesa : " + getIntent().getExtras().getString("mesa").toString());

        btEntradas.setOnClickListener(btEntradasOnClick);
        btPFuertes.setOnClickListener(btPFuertesOnClick);
        btPostres.setOnClickListener(btPostresOnClick);
        btCalcular.setOnClickListener(btCalcularOnClick);
        btCancelar.setOnClickListener(btCancelarOnClick);
        btFacturar.setOnClickListener(btFacturarOnClick);
    }

    View.OnClickListener btEntradasOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarMenu("1", getIntent().getExtras().getString("mesa").toString());
        }
    };

    View.OnClickListener btPFuertesOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarMenu("2", getIntent().getExtras().getString("mesa").toString());
        }
    };

    View.OnClickListener btPostresOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarMenu("3", getIntent().getExtras().getString("mesa").toString());
        }
    };

    View.OnClickListener btCalcularOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btCancelarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btFacturarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void mostrarMenu(String tipo, String mesa) {
        Intent intent = new Intent(PedidosMesaActivity.this, MostrarMenuActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("mesa", mesa);
        startActivity(intent);
    }
}
