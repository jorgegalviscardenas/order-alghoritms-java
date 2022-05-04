/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Presentacion.Grafica;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class RadixSorter extends Sorter {

    public RadixSorter(RandomAccessFile raf, Grafica g) {
        super(raf, g);

    }

    public void sort() {
        int tmpo;
        int longitud = obtenerCantidadRegistros();
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            try {
                int[] tmp = new int[obtenerCantidadRegistros()];
                int j = 0;
                for (int i = 0; i < longitud; i++) {
                    boolean move = obtenerValor(i) << shift >= 0;
                    if (shift == 0 ? !move : move) {
                        tmp[j] = obtenerValor(i);
                        j++;
                    } else {
                        int valor = obtenerValor(i);
                        raf.seek((i - j) * 4);
                        raf.writeInt(valor);

                    }
                }
                if (j >= tmp.length) {
                    break;
                }
                this.raf.seek(0);
                int k = j;
                int lim = tmp.length - (2 * j);
                if (lim > 0) {
                    int indice = tmp.length - lim;
                    for (int i = j; i < j + lim; i++) {
                        tmpo = obtenerValor(i);
                        raf.seek(indice * 4);
                        raf.writeInt(tmpo);
                        indice++;
                        try {
                            g.repaint();
                            Thread.sleep(MILISEGUNDOS);
                        } catch (InterruptedException ex) {
                            System.out.println("Error durmiendo hilo");
                        }
                    }
                }
                for (int i = 0; i < j; i++) {
                    if (k < tmp.length) {
                        tmpo = obtenerValor(i);
                        this.raf.seek(i * 4);
                        this.raf.writeInt(tmp[i]);
                        this.raf.seek(k * 4);
                        this.raf.writeInt(tmpo);
                        k++;
                    } else {
                        this.raf.seek(i * 4);
                        this.raf.writeInt(tmp[i]);
                    }
                    try {
                        g.repaint();
                        Thread.sleep(MILISEGUNDOS);
                    } catch (InterruptedException ex) {
                        System.out.println("Error durmiendo hilo");
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(RadixSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void ordenar() {
        sort();
    }

    private void imprimirArchivo() {
        try {
            int cant = obtenerCantidadRegistros() - 1;
            int contador = 0;
            raf.seek(0);
            while (contador != cant) {
                System.out.print(raf.readInt() + "\t");
                contador++;
            }
            System.out.println("");
        } catch (IOException ex) {
            System.out.println("errorrrrrr");
        }
    }

    public String toString() {
        return "RadixSort";
    }

}
