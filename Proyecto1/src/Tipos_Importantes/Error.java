/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tipos_Importantes;

/**
 *
 * @author Andree
 */
public class Error {

    String mensaje;
    String tipo;
    String archivo;
    int line, column;

    public Error(String mensaje, String tipo, String archivo, int line, int column) {
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.line = line;
        this.column = column;
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Mensje: " + mensaje + " Tipo: " + tipo + " Linea: " + line + " Columna: " + column;
    }

}
