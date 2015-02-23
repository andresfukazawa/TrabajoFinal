package com.example.susumu.trabajofinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susumu.trabajofinal.adapter.listview.LVMenuAdapter;
import com.example.susumu.trabajofinal.dao.DataBaseHelper;

import java.util.ArrayList;

/**
 * Created by Susumu on 2/22/2015.
 */
public class MostrarMenuActivity extends ActionBarActivity {

    private TextView          tvMesa;
    private EditText          etFiltro;
    private ListView          lvMenu;
    private LVMenuAdapter     mLVMenuAdapter;
    private ArrayList<String> mLstString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarmenu);

        tvMesa = (TextView)findViewById(R.id.tvSMMesa);
        etFiltro = (EditText)findViewById(R.id.etSMFiltro);
        lvMenu = (ListView)findViewById(R.id.lvSMMenu);

        populateList(getIntent().getExtras().getString("tipo"));
        mLVMenuAdapter = new LVMenuAdapter(MostrarMenuActivity.this, 0, mLstString);

        lvMenu.setAdapter(mLVMenuAdapter);
        etFiltro.addTextChangedListener(etMenuTextWatcher);
    }

    private void populateList(String tipo) {
        Cursor cursor = null;
        mLstString = new ArrayList<>();

        try{
            cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, "PRODTIP=?", new String[]{tipo}, null, null, "PRODNOM");            Toast.makeText(MostrarMenuActivity.this, "Point B", Toast.LENGTH_SHORT).show();
            if(cursor.moveToFirst()) {
                do {
                    mLstString.add(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

    }

    TextWatcher etMenuTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            Log.d("watcher", s.toString());
            mLVMenuAdapter.getFilter().filter(s.toString());
            mLVMenuAdapter.notifyDataSetChanged();
        }
    };
}
