/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Proyecto implements TreeSelectionListener {

    String ruta;
    String ruta2;
    String nombre;
    String correr;
    DefaultTreeModel modelo;
    ArrayList<Contenido> hijos;
    DefaultMutableTreeNode raiz;
    public JTree arbol;

    public Proyecto(ArrayList<Contenido> hijos) {
        this.hijos = hijos;
    }

    public ArrayList<Contenido> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Contenido> hijos) {
        this.hijos = hijos;
    }

    public void crearArbol() {
        ArrayList<Contenido> configuraciones = new ArrayList<>();
        for (Contenido item : hijos) {
            if (null != item.getTipo()) {
                switch (item.getTipo()) {
                    case RUTA:
                        ruta = item.getContenido().toString();
                        break;
                    case NOMBRE:
                        nombre = item.getContenido().toString();
                        break;
                    case CORRER:
                        correr = item.getContenido().toString();
                        break;
                    case CONFIGURACION:
                        configuraciones = (ArrayList<Contenido>) item.getContenido();
                        break;
                    default:
                        break;
                }
            }
        }
        raiz = new DefaultMutableTreeNode(nombre);
        modelo = new DefaultTreeModel(raiz);
        Principal.ruta_estatica += ruta;
        ruta2 = "";
        crearArbol(raiz, configuraciones);
        arbol = new JTree(modelo);
        Principal.ruta_main += ruta + nombre + "/" + ruta2+ "/" ;
        System.out.println(Principal.ruta_main);
        arbol.getSelectionModel().addTreeSelectionListener(this);

    }

    public void crearArbol(DefaultMutableTreeNode padre, ArrayList<Contenido> hijo) {
        int contador = 0;

        for (Contenido item : hijo) {
            if (item.getTipo() == Etiqueta.ARCHIVO) {
                DefaultMutableTreeNode archivo;
                for (Contenido item2 : (ArrayList<Contenido>) item.getContenido()) {
                    if (item2.getTipo() == Etiqueta.NOMBRE) {
                        archivo = new DefaultMutableTreeNode(item2.getContenido().toString());
                        modelo.insertNodeInto(archivo, padre, contador);
                        if (item2.getContenido().toString().contains(".r")) {
                            ruta2 += padre.getUserObject().toString();
                        }
                    }
                }

            } else if (item.getTipo() == Etiqueta.CARPETA) {
                DefaultMutableTreeNode carpeta;
                String name = "";
                ArrayList<Contenido> lst = new ArrayList<>();
                for (Contenido item2 : (ArrayList<Contenido>) item.getContenido()) {
                    if (item2.getTipo() == Etiqueta.NOMBRE) {
                        name = item2.getContenido().toString();
                        break;
                    }
                }
                carpeta = new DefaultMutableTreeNode(name);
                for (Contenido item2 : (ArrayList<Contenido>) item.getContenido()) {
                    if (item2.getTipo() != Etiqueta.NOMBRE) {
                        modelo.insertNodeInto(carpeta, padre, contador);
                        crearCarpeta(carpeta, item2);
                    }
                }
            }
            contador++;
        }
    }

    private void crearCarpeta(DefaultMutableTreeNode padre, Contenido item) {
        int contador = 0;
        String name = "";

        if (item.getTipo() == Etiqueta.ARCHIVO) {
            DefaultMutableTreeNode archivo;
            for (Contenido item2 : (ArrayList<Contenido>) item.getContenido()) {
                if (item2.getTipo() == Etiqueta.NOMBRE) {
                    archivo = new DefaultMutableTreeNode(item2.getContenido().toString());
                    modelo.insertNodeInto(archivo, padre, contador);
                    if (item2.getContenido().toString().contains(".r")) {
                        ruta2 += padre.getUserObject().toString();
                    }
                }
            }
        } else if (item.getTipo() == Etiqueta.CARPETA) {
            DefaultMutableTreeNode carpeta;

            for (Contenido item2 : (ArrayList<Contenido>) item.getContenido()) {
                if (item2.getTipo() == Etiqueta.NOMBRE) {
                    name = item2.getContenido().toString();
                    break;
                }
            }
            carpeta = new DefaultMutableTreeNode(name);
            for (Contenido item2 : (ArrayList<Contenido>) item.getContenido()) {
                if (item2.getTipo() != Etiqueta.NOMBRE) {
                    modelo.insertNodeInto(carpeta, padre, contador);
                    crearCarpeta(carpeta, item2);
                }
            }
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
        TreePath rutaSeleccionada = e.getPath();
        Object[] nodos = rutaSeleccionada.getPath();
        String salida = "";
        for (int i = 0; i < nodos.length - 1; i++) {
            Object nodo = nodos[i];

            salida += nodo.toString() + "/";
        }
        salida += nodos[nodos.length - 1];
        salida = Principal.ruta_estatica + salida;
        System.out.println(salida);

    }

}
