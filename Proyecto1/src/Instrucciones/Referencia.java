/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Instrucciones.Operacion.TipoOperacion;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.ArrayList;

/**
 *
 * @author Andree
 */
public class Referencia implements Instruccion {

    Tipo tipo_simbolo;
    String origen, destino;
    int line, column;

    public Referencia(Tipo tipo_simbolo, String origen, String destino, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.origen = origen;
        this.destino = destino;
        this.line = line;
        this.column = column;
    }

    public Referencia(String origen, String destino, int line, int column) {
        this.origen = origen;
        this.destino = destino;
        this.line = line;
        this.column = column;
        this.tipo_simbolo = null;
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

        if (tipo_simbolo != null) {
            if (ts.getPadre() != null) {
                if (!ts.existeSimbolo(origen)) {
                    ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, ""), origen, Tipo.VARIABLE));
                } else {
                    System.out.println("La varaible \'" + origen + "\' ya esta declarada");
                    //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                }
            }
            if (ts.compararTipos(origen, destino)) {
                String result = new Operacion(destino, TipoOperacion.IDENTIFICADOR, line, column).Ejecutar(ts).toString();
                ts.setValor(origen, result);
                

                ts.setReferencia(origen, destino);
                ts.setReferencia(destino, origen);
                
            } else {
                System.out.println("No puede referenciar a otro tipo de dato");
            }

        } else {
            if (ts.compararTipos(origen, destino)) {
                String result = new Operacion(destino, TipoOperacion.IDENTIFICADOR, line, column).Ejecutar(ts).toString();
                ts.setValor(origen, result);

                ts.removeReferencias(origen);
                
                ts.setReferencia(origen, destino);
                ts.setReferencia(destino, origen);

            } else {
                System.out.println("No puede referenciar a otro tipo de dato");
            }

        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (tipo_simbolo != null) {
            if (ts.getPadre() == null) {
                if (!ts.existeSimbolo(origen)) {
                    ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, ""), origen, Tipo.VARIABLE));
                } else {
                    System.out.println("La varaible \'" + origen + "\' ya esta declarada");
                    //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                    return;
                }
            }
            if (ts.compararTipos(origen, destino)) {
                String result = new Operacion(destino, TipoOperacion.IDENTIFICADOR, line, column).Ejecutar(ts).toString();
                ts.setValor(origen, result);

                ts.setValorReferencias(origen, ts);

                ts.setReferencia(origen, destino);
                ts.setReferencia(destino, origen);

            } else {
                System.out.println("No puede referenciar a otro tipo de dato");
            }

        } else {

        }

    }

    @Override
    public Tipo getType() {
        return Tipo.REFERENCIA;
    }

}
