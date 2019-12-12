/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
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
    int line, column;

    public Asignacion_Fusion(String id, ArrayList<String> accesos, Operacion valor, int line, int column) {
        this.id = id;
        this.accesos = accesos;
        this.line = line;
        this.column = column;
        this.valor = valor;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {

        if (ts.existeAcceso(id, accesos)) {
            TablaDeSimbolos local = ts.getTable(id, accesos);
            if (local != null) {
                Asignacion aux = new Asignacion(accesos.get(accesos.size() - 1), valor, line, column);
                aux.Ejecutar(local);
            }
        }
        ts.lst.forEach((item) -> {
            Principal.add_error(item, "Semantico", line, column);
        });
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.ASIGNACION_FUSION;
    }

}
