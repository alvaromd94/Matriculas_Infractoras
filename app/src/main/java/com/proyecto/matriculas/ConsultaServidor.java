package com.proyecto.matriculas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.proyecto.matriculas.model.Matricula;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConsultaServidor extends AppCompatActivity {

    /*
    Variables globales
     */
    ListView lista;

    ArrayAdapter adaptador;
    HttpURLConnection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista= (ListView) findViewById(R.id.listaMatriculas);
        /*
        Comprobar la disponibilidad de la Red
         */
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().
                        execute(
                                new URL("http://proyectomatriculas.com/proyecto/busqueda.php?id=" + "'" + App.matricula + "'"));
            } else {
                Toast.makeText(this, getResources().getString(R.string.errorConexion), Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
        startActivity(new Intent(getApplicationContext(), MatriculasActivity.class));
        return false;
    }
    public class JsonTask extends AsyncTask<URL, Void, List<Matricula>> {

        @Override
        protected List<Matricula> doInBackground(URL... urls) {
            List<Matricula> matriculas = null;

            try {

                // Establecer la conexión
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    matriculas = new ArrayList<>();
                    matriculas.add(new Matricula(null,null,null, null, null, null, null, null, null, null));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    GsonMatriculaParser parser = new GsonMatriculaParser();

                    matriculas = parser.leerFlujoJson(in);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return matriculas;
        }

        @Override
        protected void onPostExecute(List<Matricula> matriculas) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(matriculas!=null) {
                adaptador = new AdaptadorDeMatriculas(getBaseContext(), matriculas);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        getResources().getString(R.string.errorServidor),
                        Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }
}
