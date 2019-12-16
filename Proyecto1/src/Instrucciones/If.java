/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.Tipo_Retorno;
import Tipos_Importantes.Tipo_IF;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class If implements Instruccion {

    Tipo_IF instruccion_if;
    int line, column;

    public If(Tipo_IF instruccion_if, int line, int column) {
        this.instruccion_if = instruccion_if;
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
        Operacion expresion = instruccion_if.getExpresion();
        LinkedList<Sentencia_IF> else_if = instruccion_if.getElse_if();
        LinkedList<Instruccion> sentencias_if = instruccion_if.getSentencias_if();
        Sentencia_IF sentencia_else = new Sentencia_IF();
        for (Instruccion item : else_if) {
            Sentencia_IF objeto = (Sentencia_IF) item;
            if (objeto.tipo_sentencia == Sentencia_IF.TipoIf.TIPO_ELSE) {
                sentencia_else = objeto;
                break;
            }
        }

        try {
            if ((boolean) expresion.Ejecutar(ts)) {
                for (Instruccion item : sentencias_if) {
                    switch (item.getType()) {
                        case BREAK:
                            return new Tipo_Retorno(Tipo.ETIQUETA_RETURN, null);
                        case SEGUIR:
                            return new Tipo_Retorno(Tipo.ETIQUETA_SIGUE, null);
                        case RETURN:
                            return item.Ejecutar(tabla_local);
                        default:
                            Object result = item.Ejecutar(tabla_local);
                            if (result != null) {
                                try {
                                    Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                                    return etiqueta;
                                } catch (Exception e) {
                                }
                            }
                            break;
                    }
                }
            } else {
                for (Sentencia_IF item2 : else_if) {
                    if (item2.tipo_sentencia != Sentencia_IF.TipoIf.TIPO_ELSE) {
                        if ((boolean) item2.expresion.Ejecutar(ts)) {
                            for (Instruccion item : item2.sentencias) {
                                switch (item.getType()) {
                                    case BREAK:
                                        return new Tipo_Retorno(Tipo.ETIQUETA_RETURN, null);
                                    case SEGUIR:
                                        return new Tipo_Retorno(Tipo.ETIQUETA_SIGUE, null);
                                    case RETURN:
                                        return item.Ejecutar(tabla_local);
                                    default:
                                        Object result = item.Ejecutar(tabla_local);
                                        if (result != null) {
                                            try {
                                                Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                                                return etiqueta;
                                            } catch (Exception e) {
                                            }
                                        }
                                        break;
                                }
                            }
                            return null;
                        }
                    }
                }
                if (sentencia_else.sentencias == null) {
                    return null;
                }
                //de lo contrario si encontro un else

                for (Instruccion item : sentencia_else.sentencias) {
                    switch (item.getType()) {
                        case BREAK:
                            return new Tipo_Retorno(Tipo.ETIQUETA_RETURN, null);
                        case SEGUIR:
                            return new Tipo_Retorno(Tipo.ETIQUETA_SIGUE, null);
                        case RETURN:
                            return item.Ejecutar(tabla_local);
                        default:
                            Object result = item.Ejecutar(tabla_local);
                            if (result != null) {
                                try {
                                    Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                                    return etiqueta;
                                } catch (Exception e) {
                                }
                            }
                            break;
                    }
                    return null;
                }

            }
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts
    ) {
    }

    @Override
    public Tipo getType() {
        return Tipo.IF;
    }

}
