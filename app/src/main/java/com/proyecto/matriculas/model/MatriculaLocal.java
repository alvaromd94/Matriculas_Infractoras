package com.proyecto.matriculas.model;

public class MatriculaLocal {
    private String Infraccion;
    private String N_Matricula;




    public MatriculaLocal(String Infraccion,String N_Matricula) {
        this.Infraccion = Infraccion;
        this.N_Matricula = N_Matricula;


    }
    public String getN_Matricula() {
        return N_Matricula;
    }

    public void setN_Matricula(String N_Matricula) {
        this.N_Matricula = N_Matricula;
    }


    public String getInfraccion() {
        return Infraccion;
    }

    public void setInfraccion(String Infraccion) {
        this.Infraccion = Infraccion;
    }



}