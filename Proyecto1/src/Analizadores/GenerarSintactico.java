/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andree
 */
public class GenerarSintactico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            String ruta = "src/Analizadores/";
                        
            String opcCup[] = {"-destdir", ruta, "-parser", "Sintactico", ruta + "Sintactico.cup"};
            generar(opcCup);
            
            String ruta2 = "src/Configuracion/";
            String opcCup2[] = {"-destdir", ruta2, "-parser", "Sintactico_Configuracion", ruta + "Sintactico_Configuracion.cup"};
            generar(opcCup2);


        } catch (Exception ex) {
            Logger.getLogger(GenerarSintactico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generar(String[] ruta) throws IOException, Exception {
        java_cup.Main.main(ruta);
    }

}
