package org.example.ejer12Pizzeria;

public class Pizza {
    private static int contadorId=1;

    private int id;
    private Float precio;

    public Pizza(int id, Float precio) {
        this.id = id;
        this.precio = precio;
    }

    synchronized static int generateId(){
        return contadorId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
