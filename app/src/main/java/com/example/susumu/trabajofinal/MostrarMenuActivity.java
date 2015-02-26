package com.example.susumu.trabajofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
            cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, "PRODTIP=?", new String[]{tipo}, null, null, "PRODNOM");
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
            Log.d("watcher", s.toString());
            mLVMenuAdapter.getFilter().filter(s.toString());
            mLVMenuAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        menu.findItem(R.id.ic_action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_add:
                Log.d("susumu", String.valueOf(mLVMenuAdapter.getCount()));
                //lvMenu.getCount()
                if (lvMenu.getCount() == 1) {
                //if (mLVMenuAdapter.getCount() == 1) {
                    ContentValues cv = new ContentValues();
                    cv.put("mesa", Integer.valueOf(getIntent().getExtras().getString("mesa")));
                    cv.put("prodnom", mLVMenuAdapter.getItem(0).toString());
                    long temp = DataBaseHelper.myDataBase.insert("pedidos_pendientes", null, cv);
                    Log.d("susumu", mLVMenuAdapter.getItem(0).toString());
                    Log.d("susumu", String.valueOf(temp));
                    //Toast.makeText(MostrarMenuActivity.this, "Mesa " + getIntent().getExtras().getString("mesa") + " ordenó : " + mLVMenuAdapter.getItem(0).toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(MostrarMenuActivity.this, "insert : " + String.valueOf(temp), Toast.LENGTH_LONG).show();

                    // temporary
                    Cursor cursor = null;
                    cursor = DataBaseHelper.myDataBase.query("pedidos_pendientes", null, "idx=?", new String[]{String.valueOf(temp - 1)}, null, null, null);
                    cursor.moveToFirst();
                    Toast.makeText(MostrarMenuActivity.this, "anterior : " + cursor.getString(cursor.getColumnIndex("prodnom")), Toast.LENGTH_LONG).show();
                    //cursor.isNull(cursor.getColumnIndex("prodnom")) ? "" : cursor.getString(cursor.getColumnIndex("prodnom"))
                    if (cursor != null) cursor.close();

                    Intent intent = new Intent();
//                intent.putExtra("TextA", etSecondA.getText().toString());
//                intent.putExtra("TextB", etSecondB.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
