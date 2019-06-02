package com.proyecto.matriculas.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.proyecto.matriculas.App;
import com.proyecto.matriculas.model.MatriculaLocal;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "prueba.db";
    public static final String DBLOCATION = "/data/data/com.proyecto.matriculas/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public List<MatriculaLocal> getListProduct() {
        MatriculaLocal matriculaLocal = null;
        List<MatriculaLocal> matriculaList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM datos", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            matriculaLocal = new MatriculaLocal(cursor.getString(0), cursor.getString(1));
            matriculaList.add(matriculaLocal);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return matriculaList;
    }

    public String[] buscar_reg(String buscar){
        String[] datos= new String[3];
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM datos WHERE N_Matricula ='"+buscar+"'";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            for(int i = 0 ; i<2;i++){
                datos[i]= registros.getString(i);

            }

            datos[2]="Encontrado";

        }else{

            datos[2]="Esa matrícula no se encuentra en la base de datos";
        }
        database.close();
        return datos;
    }
    public void infraccion(String buscar)
    {
        String infraccion="";
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT Infraccion FROM datos WHERE N_Matricula ='"+buscar+"'";
        Cursor c = database.rawQuery(q, null);
        if(c.moveToFirst())
        {

            do {
                infraccion = c.getString(0);
            } while(c.moveToNext());

        }
        database.close();
        App.infraccion=infraccion;

    }
}