/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.HashSet;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Definir implements Instruccion {

    String id;//nombre de variable;
    Operacion valor;
    int line, column;// linea y columna de token 

    public Definir(String id, Operacion valor, int line, int column) {
        this.id = id;
        this.valor = valor;
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
        boolean declarada = false;
        Tipo tipo_simbolo = ts.getTipo(valor);

        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, ""), id, valor.Ejecutar(ts), Tipo.CONSTANTE));
            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico",line,column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
        String resultado = valor.Ejecutar(ts).toString();
    }

    @Override
    public Tipo getType() {
        return Tipo.DEFINIR;
    }

}
