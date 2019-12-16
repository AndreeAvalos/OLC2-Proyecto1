/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.Tipo_Retorno;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class While implements Instruccion {

    Operacion expresion;
    LinkedList<Instruccion> contenido;
    int line, column;

    public While(Operacion expresion, LinkedList<Instruccion> contenido, int line, int column) {
        this.expresion = expresion;
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

        int maximo_iteraciones = 0;
        boolean siguiente = false;
        while ((boolean) expresion.Ejecutar(ts)) {
            if (maximo_iteraciones == 5000) {
                Principal.add_error("Stack over flow", "Semantico", line, column);
                return null;
            }
            TablaDeSimbolos tabla_local = new TablaDeSimbolos();
            tabla_local.setPadre(ts);
            for (Instruccion item : contenido) {

                switch (item.getType()) {
                    case BREAK:
                        return null;
                    case SEGUIR:
                        break;
                    case RETURN:
                        return item.Ejecutar(tabla_local);
                    default:
                        Object result = item.Ejecutar(tabla_local);
                        if (result != null) {
                            try {
                                Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                                if (etiqueta.getEtiqueta() == Tipo.ETIQUETA_RETURN) {
                                    return etiqueta;
                                }
                                if (etiqueta.getEtiqueta() == Tipo.ETIQUETA_SIGUE) {

                                    break;
                                } else {
                                    return null;
                                }
                            } catch (Exception e) {
                            }
                        }
                        break;
                }
            }
            maximo_iteraciones++;
        }

        return null;

    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.WHILE;
    }
}
