package com.example.susumu.trabajofinal.entities;

/**
 * Created by Susumu on 2/16/2015.
 */
public class Usuario {

    private String Usuario;
    private String Contrasena;
    private String Nombre;
    private String Apellido;

    public Usuario() {}

    public Usuario(String usuario, String contrasena, String nombre, String apellido) {
        Usuario = usuario;
        Contrasena = contrasena;
        Nombre = nombre;
        Apellido = apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}
