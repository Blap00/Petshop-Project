package com.littlepetshop.mvc.models;
/** abers si se puede demolaaa*/
public class Catalogo {

        public Catalogo(String nombre_producto, String descripcion, int valoraciones) {
            this.nombre_producto = nombre_producto;
            this.descripcion = descripcion;
            this.valoraciones = valoraciones;
        }

        private String nombre_producto;
        private String descripcion;
        private int valoraciones;

        public String getNombre_producto() {
            return nombre_producto;
        }

        public void setNombre_producto(String nombre_producto) {
            this.nombre_producto = nombre_producto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getValoraciones() {
            return valoraciones;
        }

        public void setValoraciones(int valoraciones) {
            this.valoraciones = valoraciones;
        }

    }

