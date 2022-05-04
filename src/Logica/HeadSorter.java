/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Presentacion.Grafica;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class HeadSorter extends Sorter {

    public HeadSorter(RandomAccessFile raf, Grafica g) {
        super(raf, g);
    }

    @Override
    public void ordenar() {
        int N = obtenerCantidadRegistros();
        for (int nodo = N / 2; nodo >= 0; nodo--) {
            hacerMonticulo(nodo, N - 1);
        }
        for (int nodo = N - 1; nodo >= 0; nodo--) {
            try {
                g.repaint();
                Thread.sleep(MILISEGUNDOS);
                intercambiarValores(0, nodo);
                hacerMonticulo(0, nodo - 1);
            } catch (InterruptedException ex) {
                System.out.println("Error durmiendo hilo en head sort");
            }
        }
    }

    public void hacerMonticulo(int nodo, int fin) {
        int izq = 2 * nodo + 1;
        int der = izq + 1;
        int may;
        if (izq > fin) {
            return;
        }
        if (der > fin) {
            may = izq;
        } else {
            may = obtenerValor(izq) > obtenerValor(der) ? izq : der;
        }
        if (obtenerValor(nodo) < obtenerValor(may)) {
            try {
                g.repaint();
                Thread.sleep(MILISEGUNDOS);
                intercambiarValores(nodo, may);
                hacerMonticulo(may, fin);

            } catch (InterruptedException ex) {
                System.out.println("Error durmiendo hilo en head sort");
            }

        }
    }

    public String toString() {
        return "HeadSort";
    }
}
