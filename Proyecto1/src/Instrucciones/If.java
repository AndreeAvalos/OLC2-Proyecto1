/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import Tipos_Importantes.Tipo_IF;

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

        if ((boolean) instruccion_if.getExpresion().Ejecutar(ts)) {

            for (Instruccion item : instruccion_if.getInstrucciones_if()) {
                switch (item.getType()) {
                    case BREAK:
                        return null;
                    case RETURN:
                        return item.Ejecutar(tabla_local);
                    default:
                        item.Ejecutar(tabla_local);
                }
            }
        } else {
            for (Tipo_IF item2 : instruccion_if.getElse_if()) {
                if ((boolean) item2.getExpresion().Ejecutar(ts)) {
                    for (Instruccion item : item2.getInstrucciones_if()) {
                        switch (item.getType()) {
                            case BREAK:
                                return null;
                            case RETURN:
                                return item.Ejecutar(tabla_local);
                            default:
                                item.Ejecutar(tabla_local);
                        }
                    }
                    return null;
                }
            }
            for (Instruccion item : instruccion_if.getInstrucciones_else()) {
                switch (item.getType()) {
                    case BREAK:
                        return null;
                    case RETURN:
                        return item.Ejecutar(tabla_local);
                    default:
                        item.Ejecutar(tabla_local);
                }
            }
            return null;
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
