/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.ArrayList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Asignacion_Fusion implements Instruccion {

    String id;
    ArrayList<String> accesos;
    Operacion valor;
    String tipo;
    int line, column;
    boolean reservar_memoria = false;

    public Asignacion_Fusion(String id, ArrayList<String> accesos, Operacion valor, int line, int column) {
        this.id = id;
        this.accesos = accesos;
        this.valor = valor;
        this.line = line;
        this.column = column;
    }

    public Asignacion_Fusion(String id, String tipo, int line, int column) {
        this.id = id;
        this.tipo = tipo;
        this.line = line;
        this.column = column;
        this.reservar_memoria = true;
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
        if (reservar_memoria) {
            Ejecutar_Memoria(ts);
        } else {
            Ejecutar_Accesos(ts);
        }
        return null;

    }

    private void Ejecutar_Accesos(TablaDeSimbolos ts) {

        if (ts.existeAcceso(id, accesos)) {
            //vamos y le damos el valor al objeto

//            Object val = valor.Ejecutar(ts);
            TablaDeSimbolos local = ts.getTable(id, accesos);
            System.out.println("AMBIENTE: " + local.nombre + " AMBIENTE DESEADO: " + id);
            if (local != null) {
                Asignacion aux = new Asignacion(accesos.get(accesos.size() - 1), valor, line, column);
                local.setPadre(ts);
                aux.Ejecutar(local);
            }
            ts.lst.forEach((item) -> {
                Principal.add_error(item, "Semantico", line, column);
            });
//            System.out.println("Valor: " + accesos.get(accesos.size() - 1) + " valor: " + val);
//            ts.setValorAccesos(id, accesos, val);
//            System.out.println("Valor: " + accesos.get(accesos.size() - 1) + " valor: " + val);
//            System.out.println("----");

        } else {
            ts.lst.forEach((item) -> {
                Principal.add_error(item, "Semantico", line, column);
            });
        }

    }

    private void Ejecutar_Memoria(TablaDeSimbolos ts) {

        Simbolo sim = ts.getSimbolo(id);
        String tipo_struct = sim.getTipo().getAsignado();
        if (ts.existeSimbolo(tipo_struct)) {
            if (ts.existeSimbolo(id)) {
                TablaDeSimbolos local = ts.get_struct(tipo_struct);
                ts.setValor(id, local);
            } else {
                Principal.add_error("La varaible \'" + id + "\' no esta declarada", "Semantico", line, column);
            }
        } else {
            Principal.add_error("No existe el tipo: " + tipo, "Semantico", line, column);
        }

    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.ASIGNACION_FUSION;
    }

}
