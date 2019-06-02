package com.proyecto.matriculas.model;


import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Matricula {

    private Integer N_Registro;
    private String Infraccion;
    private String Fecha_Infraccion;
    private String N_Matricula;
    private Integer IDPropietariosFK;

    private Integer IDPropietarios;
    private String DNI;
    private String Nombre_Apellidos;
    private String Direccion;
    private Integer Telefono;


    public Matricula(Integer N_Registro,  String Infraccion, String Fecha_Infraccion, String N_Matricula, Integer IDPropietariosFK, Integer IDPropietarios, String DNI, String Nombre_Apellidos, String Direccion, Integer Telefono) {
        this.N_Registro = N_Registro;
        this.Infraccion = Infraccion;
        this.Fecha_Infraccion = Fecha_Infraccion;
        this.N_Matricula = N_Matricula;
        this.IDPropietariosFK = IDPropietariosFK;

        this.IDPropietarios = IDPropietarios;
        this.DNI = DNI;
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Direccion = Direccion;
        this.Telefono = Telefono;

    }

    public Integer getN_Registro() {
        return N_Registro;
    }

    public void setN_Registro(Integer N_Registro) {
        this.N_Registro = N_Registro;
    }

    public String getInfraccion() {
        return Infraccion;
    }

    public void setInfraccion(String Infraccion) {
        this.Infraccion = Infraccion;
    }

    public String getFecha_Infraccion() {
        return Fecha_Infraccion;
    }

    public void setFecha_Infraccion(String Fecha_Infraccion) { this.Fecha_Infraccion = Fecha_Infraccion; }

    public String getN_Matricula() {
        return N_Matricula;
    }

    public void setN_Matricula(String N_Matricula) {
        this.N_Matricula = N_Matricula;
    }

    public Integer getIDPropietariosFK() {
        return IDPropietariosFK;
    }

    public void setIDPropietariosFK(Integer IDPropietariosFK) { this.IDPropietariosFK = IDPropietariosFK; }


    public Integer getIDPropietarios() {
        return IDPropietarios;
    }

    public void setIDPropietarios(Integer IDPropietarios) { this.IDPropietarios = IDPropietarios; }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) { this.DNI = DNI; }

    public String getNombre_Apellidos() {
        return Nombre_Apellidos;
    }

    public void setNombre_Apellidos(String Nombre_Apellidos) { this.Nombre_Apellidos = Nombre_Apellidos; }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) { this.Direccion = Direccion; }

    public Integer getTelefono() {
        return Telefono;
    }

    public void setTelefono(Integer Telefono) { this.Telefono = Telefono; }

}
