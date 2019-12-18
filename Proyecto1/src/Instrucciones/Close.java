/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Close implements Instruccion {

    int line, column;

    public Close(int line, int column) {
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
        if (Principal.escribiendo == true) {
            try {
                Principal.buffer.close();
                Principal.escribiendo = false;
            } catch (IOException ex) {
                Principal.add_error("Problemas al cerrar el archivo", "Semantico", line, column);
            }

        } else {
            Principal.add_error("No se ha abierto ningun archivo", "Semantico", line, column);
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.CLOSE;
    }

}
