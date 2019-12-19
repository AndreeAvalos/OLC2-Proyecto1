/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Analizadores.Lexico;
import Analizadores.Sintactico;
import Tabla_Simbolos.TablaDeSimbolos;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Importar implements Instruccion {

    String ruta_relativa;

    int line, column;

    public Importar(String ruta_relativa, int line, int column) {
        this.ruta_relativa = ruta_relativa;
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
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        String rutas[] = ruta_relativa.split("/");
        String ruta_actual = Principal.ruta_main;
        String ruta_principal = ruta_actual;
        //System.out.println(Principal.ruta_main + ruta_relativa);
        for (int i = 0; i < rutas.length - 1; i++) {
            ruta_actual += rutas[i] + "/";
        }
        ruta_actual += rutas[rutas.length - 1];

        String nombre = rutas[rutas.length - 1];

        String archivo[] = nombre.split("\\.");

        Principal.clase_actual = archivo[0];
        Principal.ruta_main = ruta_actual;

        try {
            Scanner input = new Scanner(new File(Principal.ruta_main));
            String clase = "";
            while (input.hasNextLine()) {
                clase += input.nextLine();
            }

            Sintactico parser = new Sintactico(new Lexico(new BufferedReader(new StringReader(clase))));
            parser.parse();
            LinkedList<Instruccion> AST = parser.AST;
            AST.forEach((item) -> {
                item.Recolectar(ts);
            });
            input.close();
        } catch (Exception ex) {
            Principal.add_error("No existe archivo en la ruta", "Semantico", line, column);
        }
        Principal.ruta_main = ruta_principal;
        Principal.clase_actual = "principal";
       
    }

    @Override
    public Tipo getType() {
        return Tipo.IMPORTAR;
    }

}
