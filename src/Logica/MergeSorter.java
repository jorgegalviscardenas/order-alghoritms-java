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
public class MergeSorter extends Sorter {

    public MergeSorter(RandomAccessFile raf, Grafica g) {
        super(raf, g);
    }

    private void mergeSort(int tmpArray[], int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(tmpArray, left, center);
            mergeSort(tmpArray, center + 1, right);
            merge(tmpArray, left, center + 1, right);
        }
    }

    private void merge(int tmpArray[], int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        int a;
        int b;
        try {
            while (leftPos <= leftEnd && rightPos <= rightEnd) {
                raf.seek(leftPos * 4);
                a = raf.readInt();
                raf.seek(rightPos * 4);
                b = raf.readInt();
                if (a < b) {
                    raf.seek((leftPos++) * 4);
                    tmpArray[ tmpPos++] = raf.readInt();

                } else {
                    raf.seek((rightPos++) * 4);
                    tmpArray[ tmpPos++] = raf.readInt();
                }
            }
            while (leftPos <= leftEnd) {
                raf.seek((leftPos++) * 4);
                tmpArray[ tmpPos++] = raf.readInt();
            }
            while (rightPos <= rightEnd) {  
                raf.seek((rightPos++) * 4);
                tmpArray[ tmpPos++] = raf.readInt();
            }
            for (int i = 0; i < numElements; i++, rightEnd--) {
                try {
                    raf.seek(rightEnd * 4);
                    raf.writeInt(tmpArray[ rightEnd]);
                    g.repaint();
                    Thread.sleep(MILISEGUNDOS);
                } catch (InterruptedException ex) {
                    System.out.println("Error durmiendo hilo en merge");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error accediendo a archivo merge");
        }
    }

    @Override
    public void ordenar() {
        int tmpArray[] = new int[obtenerCantidadRegistros()];
        mergeSort(tmpArray, 0, obtenerCantidadRegistros() - 1);
    }

    public String toString() {
        return "MergeSort";
    }

}
