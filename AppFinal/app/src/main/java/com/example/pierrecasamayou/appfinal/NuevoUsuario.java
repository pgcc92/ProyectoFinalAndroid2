package com.example.pierrecasamayou.appfinal;

/**
 * Created by pierrecasamayou on 10/07/17.
 */

public class NuevoUsuario {

    String usuario, password,nombres,fecnac,email,celular;


    public NuevoUsuario() {
    }

    public NuevoUsuario(String usuario, String password, String nombres, String fecnac, String email, String celular) {
        this.usuario = usuario;
        this.password = password;
        this.nombres = nombres;
        this.fecnac = fecnac;
        this.email = email;
        this.celular = celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFecnac() {
        return fecnac;
    }

    public void setFecnac(String fecnac) {
        this.fecnac = fecnac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
