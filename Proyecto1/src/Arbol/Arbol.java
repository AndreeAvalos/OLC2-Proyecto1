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
public class Arbol {

    private NodoArbol raiz;
    private int size;
    private int niveles;
    private String salida = "";

    public int getNiveles() {
        return this.niveles;
    }

    public int getSize() {
        return size;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public void crearArbol(ArrayList<ArrayList<Celda>> indices) {
        raiz = new NodoArbol(-1, -1, null, -1);
        this.size = 0;
        this.niveles = indices.size();
        crear(raiz, indices, 0);
    }

    private void crear(NodoArbol padre, ArrayList<ArrayList<Celda>> indices, int nivel) {

        if (indices.size() > nivel) {
            for (int i = 0; i < indices.get(nivel).size(); i++) {
                NodoArbol nuevo = new NodoArbol(i, indices.get(nivel).get(i).getDato(), padre, nivel);
                padre.hijos.add(nuevo);
                this.size++;
                crear(nuevo, indices, nivel + 1);
            }
        }
    }

    public Object getDato(ArrayList<Integer> indices) {
        return getDato(indices, raiz, 0);
    }

    private Object getDato(ArrayList<Integer> indices, NodoArbol padre, int nivel) {
        for (NodoArbol item : padre.getHijos()) {
            if (item.getIndex() == indices.get(nivel)) {
                if ((nivel + 1) < indices.size()) {
                    return getDato(indices, padre, nivel + 1);
                } else {
                    return item.getDato();
                }
            }
        }
        System.out.println("Indice fuera del rango");
        return null;
    }

    public int numCeldas() {
        return numCeldas(0, raiz);
    }

    private int numCeldas(int numero, NodoArbol padre) {

        for (NodoArbol item : padre.hijos) {
            numero++;
            numero = numCeldas(numero, item);
        }
        return numero;
    }

    public void print() {
        print(raiz);
    }

    private void print(NodoArbol padre) {
        if (padre.getNivel() != -1) {
            if (padre.getDato() != null) {
                salida += padre.getDato().toString();
            }
        }
        padre.hijos.forEach((hijo) -> {
            print(hijo);
        });

    }

}
