package com.proyecto.matriculas;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.proyecto.matriculas.model.Matricula;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GsonMatriculaParser {
    public List leerFlujoJson(InputStream in) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Matricula> matriculas = new ArrayList<>();

        reader.beginArray();

        while (reader.hasNext()) {
            Matricula matricula = (Matricula) gson.fromJson(reader, Matricula.class);
            matriculas.add(matricula);
        }


        reader.endArray();
        reader.close();
        return matriculas;
    }
}
