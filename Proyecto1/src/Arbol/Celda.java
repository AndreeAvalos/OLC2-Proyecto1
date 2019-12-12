/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

/**
 *
 * @author Andree
 */
public class Celda {

    private int indice;
    private Object dato;

    public Celda(int indice, Object dato) {
        this.indice = indice;
        this.dato = dato;
    }

    public Celda(int indice) {
        this.indice = indice;
        this.dato = null;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

}
