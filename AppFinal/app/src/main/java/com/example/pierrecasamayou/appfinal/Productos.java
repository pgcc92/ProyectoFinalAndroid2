package com.example.pierrecasamayou.appfinal;

/**
 * Created by pierrecasamayou on 12/07/17.
 */

public class Productos{

    long codigo;
    String nombre, precio;


        public Productos(long codigo, String nombre, String precio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
        }


        public long getCodigo() {
            return codigo;
        }

        public void setCodigo(long codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPrecio() {
            return precio;
        }

        public void setPrecio(String precio) {
            this.precio = precio;
        }
}
