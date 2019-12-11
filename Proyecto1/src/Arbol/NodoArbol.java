/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.util.ArrayList;

/**
 *
 * @author Andree
 */
public class NodoArbol {

    private int index;
    private Object dato;
    private NodoArbol padre;
    public ArrayList<NodoArbol> hijos;
    private int nivel;

    public NodoArbol(int index, Object dato, NodoArbol padre, int nivel) {
        this.index = index;
        this.dato = dato;
        this.hijos = new ArrayList<>();
        this.nivel = nivel;
        this.padre = padre;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public ArrayList<NodoArbol> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<NodoArbol> hijos) {
        this.hijos = hijos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }
    
    
}
