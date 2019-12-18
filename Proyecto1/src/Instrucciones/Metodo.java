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
public class Metodo implements Instruccion {

    public String id;//nombre de variable;
    LinkedList<Instruccion> parametros, contenido;
    public LinkedList<Operacion> valores_parametros = new LinkedList<>();
    public boolean Llamada = false;
    int line, column;
    TablaDeSimbolos local = new TablaDeSimbolos();

    public Metodo(String id, LinkedList<Instruccion> parametros, LinkedList<Instruccion> contenido, int line, int column) {
        this.id = id;
        this.parametros = parametros;
        this.contenido = contenido;
        this.line = line;
        this.column = column;
    }

    public Metodo(String id, LinkedList<Instruccion> contenido, int line, int column) {
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
            local.setPadre(ts);
            if (local.size() == valores_parametros.size()) {
                for (int i = 0; i < valores_parametros.size(); i++) {
                    if (local.asignValorByIndex(i, valores_parametros.get(i).Ejecutar(ts))) {
                        local.setValorByIndex(i, valores_parametros.get(i).Ejecutar(ts));
                    } else {
                        System.out.println("No coincide el parametro con el tipo enviado");
                        return null;
                    }
                }
            } else {
                System.out.println("El numero de parametros no coincide1.");
                return null;
            }

            for (Instruccion item : contenido) {
                if (item.getType() == Tipo.RETURN) {
                    //aqui ponemos el valor en la funcion
                    System.out.println("No puede retornar un valor a un metodo.");
                    return null;
                } else {
                    Object result = item.Ejecutar(local);
                    if (result != null) {
                        try {
                            Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                            if (etiqueta.getEtiqueta() == Tipo.ETIQUETA_RETURN) {
                                //error porque no se puede hacer el casteo explicito
                                Principal.add_error("El valor" + etiqueta.getResultado() + " no puede ser retornado en un metodo", "Semantico", line, column);

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
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(Tipo.METODO, ""), id, this, Tipo.METODO));
            } else {
                System.out.println("La funcion \'" + id + "\' ya esta declarada");
                return;
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
            parametros.forEach((item) -> {
                item.Recolectar(local);
            });
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.METODO;
    }

}
