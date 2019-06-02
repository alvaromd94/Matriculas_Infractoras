package com.proyecto.matriculas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.matriculas.database.DatabaseHelper;
import com.proyecto.matriculas.model.Matricula;
import com.proyecto.matriculas.model.Usuarios;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {

    private String APIserver = "https://proyectomatriculas.com/proyecto/";

    EditText editTextUsuario;
    EditText editTextPassword;
    Button btnInicio;
    Intent menuPrincipal;
    private DatabaseHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        menuPrincipal = new Intent(this, MenuPrincipalActivity.class);
        editTextUsuario =  findViewById(R.id.editTextUsuario);
        editTextPassword =  findViewById(R.id.editTextPassword);

        btnInicio = findViewById(R.id.btnInicio);

        mDBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }



        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PruebaJson().execute("login.php?Usuario=" + "'" + editTextUsuario.getText().toString() + "'" + "&Contrasena=" +  "'" + editTextPassword.getText().toString() + "'");

            }
        });

    }


    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("LoginActivity","BD copiada");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

     public void cickLimpiar(View view) {
         editTextUsuario.setText("");
         editTextPassword.setText("");
     }

    public class PruebaJson extends AsyncTask<String, Void, Boolean> {

        private String json;

        protected Boolean doInBackground(String... urls) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(APIserver + urls[0]));
                HttpResponse response = client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                json = sb.toString();
            } catch (Exception e) {
                System.out.println("FALLO: " + e.getMessage());
                return false;
            }
            return true;
        }

        public void onLoginSuccess() {
            btnInicio.setEnabled(true);
            finish();
        }
        public void onLoginFailed() {
            Toast.makeText(getBaseContext(), getResources().getString(R.string.inicioNoOk), Toast.LENGTH_LONG).show();
            btnInicio.setEnabled(true);
        }

        protected void onPostExecute(Boolean inicioCorrecto) {
            try {
                if (inicioCorrecto) {
                    JSONArray response = new JSONArray(json);

                    List<Usuarios> lista = new ArrayList<Usuarios>();
                    for (int i = 0; i < response.length(); i++) {
                        lista.add(new Usuarios(
                                response.getJSONObject(i).getInt("IDUsuario")
                                , response.getJSONObject(i).getString("Usuario")
                                , response.getJSONObject(i).getString("Contrasena")
                        ));
                    }
                    if(!lista.isEmpty())
                    {

                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage(getResources().getString(R.string.comprobar));
                        progressDialog.show();

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        startActivity(menuPrincipal);
                                        finish();
                                        onLoginSuccess();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);

                    }
                    else{
                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage(getResources().getString(R.string.comprobar));
                        progressDialog.show();

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onLoginFailed();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);

                    }
                }
            } catch (JSONException e) {
                System.out.println("FALLO: " + e.getMessage());
            }
        }



    }
}

