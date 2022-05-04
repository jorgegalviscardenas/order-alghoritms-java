/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Presentacion.Grafica;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class ShellSorter extends Sorter {

    public ShellSorter(RandomAccessFile raf, Grafica g) {
        super(raf, g);
    }

    public void ordenar() {
        try {
            int intervalo, i, j, k;
            int n = (int) (raf.length() / 4);
            intervalo = n / 2;
            while (intervalo > 0) {
                for (i = intervalo; i < n; i++) {
                    j = i - intervalo;
                    while (j >= 0) {
                        k = j + intervalo;
                        if (obtenerValor(j) <= obtenerValor(k)) {
                            j = -1;
                        } else {
                            try {
                                g.repaint();
                                Thread.sleep(MILISEGUNDOS);
                                intercambiarValores(j, k);
                                j -= intervalo;
                            } catch (InterruptedException ex) {
                                System.out.println("Erro en shell");
                            }

                        }
                    }
                }
                intervalo = intervalo / 2;

            }

        } catch (IOException ex) {
            System.out.println("Error obteniendo longitud archivo");
        }
    }
    public String toString()
    {
        return "ShellSort";
    }

}
