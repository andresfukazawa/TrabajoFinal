package com.example.susumu.trabajofinal.entities;

/**
 * Created by Administrator on 14/02/2015.
 */
public class Alumno {

    private int IdAlumno;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String DNI;

    public Alumno() {}

    public Alumno(int idAlumno, String nombre, String apellido, int edad, String DNI) {
        IdAlumno = idAlumno;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        this.DNI = DNI;
    }

    public Alumno(String nombre, String apellido, int edad, String DNI) {
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        this.DNI = DNI;
    }

    public int getIdAlumno() {


        return IdAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        IdAlumno = idAlumno;
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

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
}
