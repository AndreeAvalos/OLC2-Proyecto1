/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.ArrayList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Declaracion_Arreglo implements Instruccion {

    Tipo tipo_dato;
    String id;
    ArrayList<Integer> num_dimensiones;
    int line, column;

    public Declaracion_Arreglo(Tipo tipo_dato, String id, ArrayList<Integer> num_dimensiones, int line, int column) {
        this.tipo_dato = tipo_dato;
        this.id = id;
        this.num_dimensiones = num_dimensiones;
        this.line = line;
        this.column = column;
    }
    

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_dato, "arreglo"), id, Tipo.ARREGLO));
                Arbol arbol_declaracion = new Arbol();
                arbol_declaracion.crearArbol(num_dimensiones);
                ts.setValor(id, arbol_declaracion);
            } else {
                Principal.add_error("La variable \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION_ARREGLO;
    }

}
