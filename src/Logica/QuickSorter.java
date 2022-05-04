package Logica;

import Presentacion.Grafica;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class QuickSorter extends Sorter {

    public QuickSorter(RandomAccessFile raf, Grafica g) {
        super(raf, g);
    }

    public void ordenar(int primero, int ultimo) {
        int i, j, central;
        double pivote;
        central = (primero + ultimo) / 2;
        pivote = obtenerValor(central);
        i = primero;
        j = ultimo;
        do {
            while (obtenerValor(i) < pivote) {
                i++;
            }
            while (obtenerValor(j) > pivote) {
                j--;
            }
            if (i <= j) {
                try {
                    g.repaint();
                    Thread.sleep(MILISEGUNDOS);
                    intercambiarValores(i, j);                 
                    i++;
                    j--;
                } catch (InterruptedException ex) {
                    System.out.println("Error en quick");
                }

            }
        } while (i <= j);
        /* intercambia a[i] con a[j] */
        if (primero < j) {
            ordenar(primero, j);/* mismo proceso con sublista izqda */

        }
        if (i < ultimo) {
            ordenar(i, ultimo); /* mismo proceso con sublista drcha */

        }
    }



    @Override
    public void ordenar() {
        try {
            ordenar(0, (int) raf.length() / 4 - 1);
        } catch (IOException ex) {
            Logger.getLogger(QuickSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String toString()
    {
        return "QuickSort";
    }

}
