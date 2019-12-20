/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.util.ArrayList;
import Tipos_Importantes.Error;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Andree
 */
public class Reporte_Errores {

    ArrayList<Error> lexicos;
    ArrayList<Error> sintacticos;
    ArrayList<Error> semanticos;

    FileWriter filewriter = null;
    PrintWriter printw = null;

    public Reporte_Errores(ArrayList<Error> lexicos, ArrayList<Error> sintacticos, ArrayList<Error> semanticos) {
        this.lexicos = lexicos;
        this.sintacticos = sintacticos;
        this.semanticos = semanticos;
    }

    public String crear_Reporte() throws IOException {
        filewriter = new FileWriter("reporte_errores.html");//declarar el archivo
        printw = new PrintWriter(filewriter);//declarar un impresor

        String html = "<html>\n <head>\n<title>Reporte de Errores</title>\n</head>\n<body>\n<h1 align=\"center\" >Errores Lexicos <h1>\n";
        ArrayList<String> lst = crearTabla(lexicos);

        for (String item : lst) {
            html += item;
        }
        html += "<h1 align=\"center\" >Errores Sintacticos <h1>\n";
        lst = crearTabla(sintacticos);

        for (String item : lst) {
            html += item;
        }
        html += "<h1 align=\"center\" >Errores Semanticos <h1>\n";
        lst = crearTabla(semanticos);

        for (String item : lst) {
            html += item;
        }

        html += "</body>\n</html>";

        printw.println(html);
        printw.close();

        return html;
    }

    private ArrayList<String> crearTabla(ArrayList<Error> lst) {
        ArrayList<String> tabla = new ArrayList<>();
        String linea = "";
        tabla.add(" <table border=\"1\" align=\"center\" bordercolor=\"blue\" cellspacing=\"0\">\n");
        linea = "       <tr><td> Descripcion </td><td> Tipo </td><td> Linea </td><td> Columna </td><td> Archivo </td></tr>\n";
        tabla.add(linea);

        for (Error item : lst) {
            linea = "       <tr><td>" + item.getMensaje() + " </td>" + "<td>" + item.getTipo() + " </td>" + "<td>" + item.getLine() + " </td>" + "<td>" + item.getLine() + " </td><td> " + item.getArchivo() + " </td></tr>\n";
            tabla.add(linea);
        }
        tabla.add(" </table>\n");

        return tabla;
    }

}
