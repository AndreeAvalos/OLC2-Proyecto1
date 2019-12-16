/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.Tipo_Retorno;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class For implements Instruccion {

    Instruccion declaracion;//variable al principio que declara
    Operacion expresion;// por ejemplo i<8, i>3, i<=2, etc.
    Instruccion operador;// incremento o decremento de varaible
    LinkedList<Instruccion> contenido;
    int line, column;

    public For(Instruccion declaracion, Operacion expresion, Instruccion operador, LinkedList<Instruccion> contenido, int line, int column) {
        this.declaracion = declaracion;
        this.expresion = expresion;
        this.operador = operador;
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
        TablaDeSimbolos tabla_local = new TablaDeSimbolos();
        tabla_local.setPadre(ts);

        declaracion.Ejecutar(tabla_local);
        boolean siguiente = false;

        while ((boolean) expresion.Ejecutar(tabla_local)) {

            for (Instruccion item : contenido) {

                switch (item.getType()) {
                    case BREAK:
                        return null;
                    case SEGUIR:
                        return new Tipo_Retorno(Tipo.ETIQUETA_SIGUE, null);
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
                                    siguiente = true;
                                    break;
                                } else {
                                    return null;
                                }
                            } catch (Exception e) {
                            }
                        }
                        break;
                }
                if (siguiente) {
                    return null;
                }
            }
            operador.Ejecutar(tabla_local);
        }

        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.FOR;
    }

}
