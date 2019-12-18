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
        System.out.println(Principal.ruta_main + ruta_relativa);
        try {
            Scanner input = new Scanner(new File(Principal.ruta_main + ruta_relativa));
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
            ex.printStackTrace();
        }


    }

    @Override
    public Tipo getType() {
        return Tipo.IMPORTAR;
    }

}
