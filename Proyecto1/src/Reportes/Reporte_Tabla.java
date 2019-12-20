/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Andree
 */
public class Reporte_Tabla {

    TablaDeSimbolos tabla;

    FileWriter filewriter = null;
    PrintWriter printw = null;

    public Reporte_Tabla(TablaDeSimbolos tabla) {
        this.tabla = tabla;
    }

    public String reporte_Tabla() throws IOException {
        filewriter = new FileWriter("Tabla_de_Simbolos.html");//declarar el archivo
        printw = new PrintWriter(filewriter);//declarar un impresor

        String html = "<html>\n <head>\n<title>Reporte de Errores</title>\n</head>\n<body>\n<h1 align=\"center\" > Tabla de Simbolos<h1>\n";
        ArrayList<String> lst = crearTabla(tabla);

        for (String item : lst) {
            html += item;
        }
        html += "</body>\n</html>";

        printw.println(html);
        printw.close();
        return html;
    }

    private ArrayList<String> crearTabla(TablaDeSimbolos lst) {
        ArrayList<String> tabla = new ArrayList<>();
        String linea = "";
        tabla.add(" <table border=\"1\" align=\"center\" bordercolor=\"blue\" cellspacing=\"0\">\n");
        linea = "       <tr><td> Tipo </td><td> Subtipo </td><td> Identificador </td><td> Valor </td><td> Tipo Instruccion </td></tr>\n";
        tabla.add(linea);

        for (Simbolo item : lst) {
            linea = "       <tr><td>" + item.getTipo().getTipo().toString() + " </td>" + "<td>" + item.getTipo().getAsignado() + " </td>" + "<td>" + item.getId() + " </td>" + "<td>" + item.getValor() + " </td><td> " + item.getTipo_instruccion().toString() + " </td></tr>\n";
            tabla.add(linea);
        }
        tabla.add(" </table>\n");

        return tabla;
    }

}
