package com.proyecto.matriculas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.ArrayAdapter;
import java.util.List;

public class MenuPrincipalActivity extends AppCompatActivity {
    Spinner spinnerAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);spinnerAlerta = findViewById(R.id.spinnerAlerta);


        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.spinnerAlerta,R.layout.support_simple_spinner_dropdown_item);
        final Spinner spinnerEstadoCivil = findViewById(R.id.spinnerAlerta);
        spinnerEstadoCivil.setAdapter(adaptador);
    }

    public void clickEscanear(View view) {
        startActivity(new Intent(getApplicationContext(), CamaraActivity.class));
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_volver, menu);
        MenuBuilder m = (MenuBuilder) menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        return false;
    }
}