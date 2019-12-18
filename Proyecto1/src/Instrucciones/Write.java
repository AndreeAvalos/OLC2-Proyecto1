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
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Write implements Instruccion {

    String ruta;
    int line, column;

    public Write(String ruta, int line, int column) {
        this.ruta = ruta;
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
        if (Principal.escribiendo == false) {
            String carpetas[] = ruta.split("/");
            String rutas_carpetas = Principal.ruta_main;

            for (int i = 0; i < carpetas.length - 1; i++) {
                rutas_carpetas += carpetas[i]+"/";
            }
            
            File carpeta = new File(rutas_carpetas);
            carpeta.mkdirs();
            
            File archivo = new File(Principal.ruta_main + ruta);

            try {
                Principal.buffer = new BufferedWriter(new FileWriter(archivo));
                Principal.escribiendo = true;
            } catch (IOException ex) {
                Principal.add_error("No se puede abrir el archivo", "Semantico", line, column);
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
        return Tipo.WRITE;
    }

}
