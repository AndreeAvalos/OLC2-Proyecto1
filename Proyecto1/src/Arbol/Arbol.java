/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Instrucciones.Operacion;
import Tabla_Simbolos.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Arbol {

    private NodoArbol raiz;

    private int size;
    private int niveles;
    private String salida = "";
    public boolean valido = true;
    // nivel, numero de celdas por nivel;
    public HashMap<Integer, Integer> mapa = new HashMap<>();

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public int getNiveles() {
        return this.niveles;
    }

    public void setNivel(int nivel) {
        this.niveles = nivel;
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
        mapa.put(0, size);
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
                    return item.hijos.get(0).getDato();
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

    //-------------------------------------------------------
    private String id = "";

    public Object get(ArrayList<Integer> indices) {
        return get(indices, 0, raiz);
    }

    private Object get(ArrayList<Integer> indices, int nivel, NodoArbol padre) {
        for (NodoArbol item : padre.hijos) {
            if (item.getIndex() == indices.get(nivel)) {
                if ((nivel + 1) < indices.size()) {
                    return get(indices, nivel + 1, item);
                } else {
                    return item.getDato();

                }
            }
        }
        return null;
    }

    public boolean existeIndice(ArrayList<Integer> indices) {
        try {
            return existeIndice(indices, 0, raiz);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean existeIndice(ArrayList<Integer> indices, int nivel, NodoArbol padre) {
        for (NodoArbol item : padre.hijos) {
            if (item.getIndex() == indices.get(nivel)) {
                if ((nivel + 1) < indices.size()) {
                    return existeIndice(indices, nivel + 1, item);
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public NodoArbol recorrerArbol(String id, LinkedList<Object> valores, TablaDeSimbolos ts) {
        this.id = id;
        raiz = new NodoArbol(-1, -1, null, -1);
        this.valido = true;
        return recorrerArbol(raiz, valores, 0, ts, 0);
    }

    private NodoArbol recorrerArbol(NodoArbol padre, Object valores, int indice, TablaDeSimbolos ts, int nivel) {
        try {
            //si se puede castear es porque es una lista
            int index = 0;

            LinkedList<Object> hijos = (LinkedList<Object>) valores;

            if (mapa.containsKey(nivel)) {
                mapa.replace(nivel, mapa.get(nivel) + hijos.size());
            } else {
                mapa.put(nivel, hijos.size());
            }
            indice = 0;
            for (Object item : hijos) {

                NodoArbol nuevo = new NodoArbol(index, null, padre, nivel);
                padre.hijos.add(recorrerArbol(nuevo, item, indice, ts, nivel + 1));
                index++;
                indice++;
            }
            return padre;

        } catch (Exception e) {
            //de lo contrario es un hijo
            Operacion operacion = (Operacion) valores;
            Object resultado = operacion.Ejecutar(ts);
            try {
                String val = ts.castearValor(id, resultado).toString();
                size++;
                return new NodoArbol(indice, val, padre, nivel);
            } catch (Exception e2) {
                this.valido = false;
                return null;
            }

        }
    }

}
