/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Instrucciones.Case.TipoCase;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.Tipo_Retorno;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Sentencia_Switch implements Instruccion {

    Operacion expresion;
    LinkedList<Case> casos;
    int line, column;

    public Sentencia_Switch(Operacion expresion, LinkedList<Case> casos, int line, int column) {
        this.expresion = expresion;
        this.casos = casos;
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return this.column;
    }

    @Override
    public int getColumn() {
        return this.line;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
//        string opcion = instruccion_switch.Expresion.Ejecutar(ts).ToString();
        Case caso_default = null;

        for (Case item : casos) {
            if (item.getTipo_case() == TipoCase.DEFAULT) {
                caso_default = item;
            }
        }

        TablaDeSimbolos tabla_local = new TablaDeSimbolos();
        String opcion = expresion.Ejecutar(ts).toString();
        tabla_local.setPadre(ts);
        boolean tiene_break = true;
        // corren todos los casos excepto el default
        for (Case case_actual : casos) {
            if (case_actual.getTipo_case() != TipoCase.DEFAULT) {
                Object valor = case_actual.expresion.Ejecutar(ts).toString();
                if (valor.equals(opcion) || !tiene_break) {
                    for (Instruccion item : case_actual.getContenido()) {
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
                                            return etiqueta;
                                        } else {
                                            return null;
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                        }
                    }
                    tiene_break = false;
                }
            }
        }
        //si llega aqui es por que no encontro conicidencia
        if (caso_default != null) {
            for (Instruccion item : caso_default.getContenido()) {
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
                                    return etiqueta;
                                } else {
                                    return null;
                                }
                            } catch (Exception e) {
                            }
                        }
                        break;
                }
            }
        }
        //si llega aqui es por que tampoco encontro default
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.SENTENCIA_SWITCH;
    }

}
