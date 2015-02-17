package com.example.susumu.trabajofinal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.susumu.trabajofinal.dao.DataBaseHelper;
import com.example.susumu.trabajofinal.dao.UsuarioDAO;
import com.example.susumu.trabajofinal.entities.Usuario;

import java.security.MessageDigest;


public class MainActivity extends ActionBarActivity {

    private EditText etNombre, etContrasena;
    private Button   btIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNombre     = (EditText)findViewById(R.id.etLINombre);
        etContrasena = (EditText)findViewById(R.id.etLIContrasena);
        btIngresar   = (Button)findViewById(R.id.btLIIngresar);

        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "No se pudo copiar la BD", Toast.LENGTH_SHORT).show();
        }

        btIngresar.setOnClickListener(btIngresarOnClick);
    }

    View.OnClickListener btIngresarOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Usuario usuario = new Usuario();
            String  msg     = "";

            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(etContrasena.getText().toString().getBytes());
                byte byteData[] = messageDigest.digest();

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }

                String pwdhash = sb.toString();

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuario = usuarioDAO.getUsuarioByUserId(etNombre.getText().toString(), pwdhash);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (usuario == null) {
                msg = "Usuario no encontrado";
            } else {
                msg = usuario.getNombre() + " " + usuario.getApellido();
            }

            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

        }
    };


}
