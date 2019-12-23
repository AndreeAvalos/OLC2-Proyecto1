/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import Tabla_Simbolos.Tipo_Retorno;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Crear_Evento implements Instruccion {

    public String id;//nombre de variable;
    LinkedList<Instruccion> contenido;
    public boolean Llamada = false;
    int line, column;
    

    public Crear_Evento(String id, LinkedList<Instruccion> contenido, int line, int column) {
        this.id = id;
        this.contenido = contenido;
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
            local.setPadre(ts);

            for (Instruccion item : contenido) {
                if (item.getType() == Tipo.RETURN) {
                    //aqui ponemos el valor en la funcion
                    Principal.add_error("No puede retornar un valor a un evento", "Semantico", line, column);
                    return null;
                } else {
                    Object result = item.Ejecutar(local);
                    if (result != null) {
                        try {
                            Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                            if (etiqueta.getEtiqueta() == Tipo.ETIQUETA_RETURN) {
                                //error porque no se puede hacer el casteo explicito
                                Principal.add_error("El valor" + etiqueta.getResultado() + " no puede ser retornado en un evento", "Semantico", line, column);
                                return null;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        //aun no se como hacer esto.
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo("EVENTO_" + id + "_CLICK")) {
                ts.add(new Simbolo(new TipoSimbolo(Tipo.EVENTO, ""), "EVENTO_" + id + "_CLICK", this, Tipo.EVENTO));
            } else {
                Principal.add_error("Ya se ha creado el evento para " + id, "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.EVENTO_CLICK;
    }

}
