package com.proyecto.matriculas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MatriculasActivity extends AppCompatActivity {

    TextView matricula, infraccion;
    CamaraActivity ca = new CamaraActivity();
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriculas);
        matricula = findViewById(R.id.matricula);
        infraccion = findViewById(R.id.infraccion);
        cardView = findViewById(R.id.cardView);


        matricula.setText(getResources().getString(R.string.matricula) + ": " + App.matricula);
        infraccion.setText(getResources().getString(R.string.infraccion) + ": " + App.infraccion);




        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ConsultaServidor.class);
                startActivityForResult(intent, 0);
            }


        });


    }


    public void clickVolver(View view) {
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
        startActivity(new Intent(getApplicationContext(), CamaraActivity.class));
        return false;
    }


}
