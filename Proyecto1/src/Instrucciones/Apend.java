/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Apend implements Instruccion {

    String ruta;
    int line, column;

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    public Apend(String ruta, int line, int column) {
        this.ruta = ruta;
        this.line = line;
        this.column = column;
    }
    

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
        if (Principal.escribiendo == false) {
            File archivo = new File(Principal.ruta_main + ruta);
            if (archivo.exists()) {
                try {
                    Principal.buffer = new BufferedWriter(new FileWriter(archivo, true));
                    Principal.escribiendo = true;
                } catch (IOException ex) {
                    Principal.add_error("Error al abrir archivo. ", "Semantico", line, column);
                }
            } else {
                Principal.add_error("El archivo no existe ", "Semantico", line, column);
            }

        } else {
            Principal.add_error("Ya esta abierto un archivo. ", "Semantico", line, column);
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.APEND;
    }

}
