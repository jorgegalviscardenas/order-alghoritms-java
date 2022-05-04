/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import Presentacion.Grafica;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class CountingSorter extends Sorter{

    public CountingSorter(RandomAccessFile raf, Grafica g) {
        super(raf, g);
    }
    
    
    private void countingSort(int min, int max){
	int[] count= new int[max - min + 1];
        int cantidadDatos=obtenerCantidadRegistros();
        int contador=0;
        while(contador<cantidadDatos)
        {
            count[obtenerValor(contador)-min]++;
            contador++;
        }
	int z= 0;
	for(int i= min;i <= max;i++){
		while(count[i - min] > 0){
                    try {
                        this.raf.seek(z*4);
                        this.raf.writeInt(i);
                    } catch (IOException ex) {
                        System.out.println("Error escribiendo en archivo");
                    }
			
			z++;
			count[i - min]--;
                        
                    try {
                        g.repaint();
                        Thread.sleep(MILISEGUNDOS);
                    } catch (InterruptedException ex) {
                        System.out.println("Error durmiendo hilo");
                    }
		}
	}
}

    @Override
    public void ordenar() {
        countingSort(buscarMenor(),buscarMayor());
    }

    @Override
    public String toString() {
        return "CountingSort";
    }
    
}
