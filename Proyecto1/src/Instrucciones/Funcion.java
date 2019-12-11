/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Funcion implements Instruccion {

    Tipo tipo_simbolo;//numero,cadena,identificador etc
    String id;//nombre de variable;
    LinkedList<Instruccion> parametros, contenido;
    public LinkedList<Operacion> valores_parametros = new LinkedList<>();

    ;
    int line, column;
    boolean Llamada = false;

    public Funcion(Tipo tipo_simbolo, String id, LinkedList<Instruccion> parametros, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.id = id;
        this.parametros = parametros;
        this.contenido = contenido;
        this.line = line;
        this.column = column;
    }

    public Funcion(Tipo tipo_simbolo, String id, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.id = id;
        this.contenido = contenido;
        this.parametros = new LinkedList<>();
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {

        if (Llamada == true) {
            TablaDeSimbolos local = new TablaDeSimbolos();
            local.setPadre(new TablaDeSimbolos());
            parametros.forEach((item) -> {
                item.Ejecutar(local);
            });
            local.setPadre(ts);
            if (local.size() == valores_parametros.size()) {
                for (int i = 0; i < valores_parametros.size(); i++) {
                    if (local.asignValorByIndex(i, valores_parametros.get(i).Ejecutar(local))) {
                        local.setValorByIndex(i, valores_parametros.get(i).Ejecutar(local));
                    } else {
                        System.out.println("No coincide el parametro con el tipo enviado");
                        return null;
                    }
                }
            } else {
                System.out.println("El numero de parametros no coincide.");
                return null;
            }
            for (Instruccion item : contenido) {
                if (item.getType() == Tipo.RETURN) {
                    //aqui ponemos el valor en la funcion
                    Object resultado = item.Ejecutar(local);
                    if (local.asignValor(id, resultado)) {
                        local.setValor(id, resultado);
                    } else {
                        //error porque no se puede hacer el casteo explicito
                        System.out.println("No es posible hacer el casteo ");
                    }
                    return null;
                } else {
                    item.Ejecutar(local);
                }
            }
            System.out.println("No se encontro la instruccion de retorno ");
        }
        //aun no se como hacer esto.
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, tipo_simbolo), id, this, Tipo.FUNCION));
            } else {
                System.out.println("La funcion \'" + id + "\' ya esta declarada");
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.FUNCION;
    }

}
