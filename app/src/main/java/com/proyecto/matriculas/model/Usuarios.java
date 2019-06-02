package com.proyecto.matriculas.model;

public class Usuarios {

    private Integer IDUsuario;
    private String Usuario;
    private String Contrasena;

    public Usuarios() {
        this.IDUsuario = null;
        this.Usuario = "";
        this.Contrasena = "";

    }

    public Usuarios(Integer IDUsuario, String Usuario, String Contrasena) {
        this.IDUsuario = IDUsuario;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;

    }

    public Integer getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(Integer IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }


    @Override
    public String toString() {
        return "Usuarios {" +
                "IDUsuario=" + IDUsuario +
                ", Usuario='" + Usuario + '\'' +
                ", Contrasena='" + Contrasena + '\'' +
                '}';
    }
}
