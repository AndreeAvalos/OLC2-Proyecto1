/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Llamada implements Instruccion {
    
    String id;
    LinkedList<Operacion> valores_parametros = new LinkedList<>();
    int line, column;
    
    public Llamada(String id, int line, int column) {
        this.id = id;
        this.line = line;
        this.column = column;
    }
    
    public Llamada(String id, LinkedList<Operacion> valores_parametros, int line, int column) {
        this.id = id;
        this.valores_parametros = valores_parametros;
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
        TablaDeSimbolos local = new TablaDeSimbolos();
        local.setPadre(ts);
        if (ts.existeMetodo_Funcion(id)) {
            Instruccion aux = ts.getContenido(id);
            if (aux.getType() == Tipo.METODO) {
                Metodo aux2 = (Metodo) aux;
                aux2.Llamada = true;
                aux2.valores_parametros = valores_parametros;
                aux2.Ejecutar(local);
            } else if (aux.getType() == Tipo.FUNCION) {
                Funcion aux2 = (Funcion) aux;
                aux2.Llamada = true;
                aux2.valores_parametros = valores_parametros;
                aux2.Ejecutar(local);
            } else {
                Principal.add_error("No es metodo ni funcion", "Semantico", line,column);
                return null;
            }
        } else {
            Principal.add_error("No existe metodo ni funcion con ese identificador", "Semantico",line,column);
        }
        return false;
    }
    
    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }
    
    @Override
    public Tipo getType() {
        return Tipo.LLAMADA;
    }
    
}
