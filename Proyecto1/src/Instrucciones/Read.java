/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.io.File;
import java.util.Scanner;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Read implements Instruccion {

    String ruta;
    String id;
    int line, column;

    public Read(String ruta, String id, int line, int column) {
        this.ruta = ruta;
        this.id = id;
        this.line = line;
        this.column = column;
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
        if (ts.existeSimbolo(id)) {
            Simbolo simbolo = ts.getSimbolo(id);
            if (simbolo.getTipo().getTipo() == Tipo.String) {
                try {
                    String line = "";
                    try (Scanner input = new Scanner(new File(Principal.ruta_main + ruta))) {
                        while (input.hasNextLine()) {
                            line += input.nextLine();
                        }
                    }
                    ts.setValor(id, line);
                    return null;

                } catch (Exception ex) {
                    Principal.add_error("No se puede leer el archivo", "Semantico", line, column);
                }
            }
            Principal.add_error("No es una variable Rstring", "Semantico", line, column);
            return null;
        }
        Principal.add_error("No existe la variable", "Semantico", line, column);
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.READ;
    }

}
