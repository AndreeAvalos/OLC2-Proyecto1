/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import java.util.logging.Level;
import java.util.logging.Logger;
import jflex.SilentExit;

/**
 *
 * @author Andree
 */
public class GenerarLexico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String ruta = "src/Analizadores/";
        String ruta2 = "src/Configuracion/";
        String opcFlex[] = {ruta + "Lexico.jflex", "-d", ruta};
        generar(opcFlex);
        String opcFlex2[] = {ruta + "Lexico_Configuracion.jflex", "-d", ruta2};
        generar(opcFlex2);
    }

    private static void generar(String[] ruta) {
        try {
            jflex.Main.generate(ruta);
        } catch (SilentExit ex) {
            Logger.getLogger(GenerarLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
