package Logica;

import Presentacion.Grafica;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase General de un Ordenador de numeros enteros
 * @author Jorge Galvis Cardenas
 * @version 1.0
 */
public abstract class Sorter implements Runnable {
    /**
     * La cantidad de milisegundos que se duerme el hilo, para que la animacion
     * grafica se vea mas claro
     */
    public static final long MILISEGUNDOS = 30;
    /**
     * Archivo binario de acceso aleatorio, que contienes los numeros a ordenar
     */
    protected RandomAccessFile raf;
    /**
     * Es la grafica sobre la cual se va animar el algoritmo de ordenamiento
     */
    protected Grafica g;
    /**
     * Constructor
     * @param raf
     * @param g 
     */
    public Sorter(RandomAccessFile raf, Grafica g) {
        this.raf = raf;
        this.g = g;
        g.setMayor(buscarMayor());
        g.setMenor(buscarMenor());
        try {
            FileDescriptor d = raf.getFD();

        } catch (IOException ex) {
            Logger.getLogger(Sorter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Metodo que obtiene un numero localizado en la posicion indicada por 
     * parametro
     * @param posicion la posicion del numero 
     * @return el numero localizado en esa posicion
     */
    protected int obtenerValor(int posicion) {
        try {
            raf.seek(posicion * 4);
            return raf.readInt();
        } catch (IOException ex) {
            System.out.println("Error obteniendo valor");
            return Integer.MAX_VALUE;
        }
    }
    /**
     * Metodo que dadas dos posiciones, intercambia los valores pertenecientes
     * a cada posicion
     * @param pos1 
     * @param pos2 
     */
    protected void intercambiarValores(int pos1, int pos2) {
        try {
            raf.seek(pos1 * 4);
            int tmp1 = raf.readInt();
            raf.seek(pos2 * 4);
            int tmp2 = raf.readInt();
            raf.seek(pos2 * 4);
            raf.writeInt(tmp1);
            raf.seek(pos1 * 4);
            raf.writeInt(tmp2);

        } catch (IOException ex) {
            System.out.println("Error intercambiando ");
        }
    }
    /**
     * Metodo para ordenar
     */
    public abstract void ordenar();
    /**
     * Metodo que obtiene la cantidad de registros localizadas en el archivo
     * @return la cantidad de registros
     */
    public int obtenerCantidadRegistros() {
        try {
            return (int) (raf.length() / 4);
        } catch (IOException ex) {
            System.out.println("Error obteniendo cantidad de registros");
        }
        return -1;
    }
    @Override
    public void run() {
        try {
            ordenar();
            Thread.sleep(MILISEGUNDOS);
            g.repaint();
        } catch (InterruptedException ex) {
            System.out.println("Error durmiendo hilo");;
        } catch (ClassCastException ex) {
            System.out.println("Error corriendo hilo");
        }

    }
    /**
     * Metodo que busca el mayor de los numeros en el archivo
     * @return el numero mayor
     */
    protected int buscarMayor() {
        int valor = 0;
        try {

            int contador = 1;
            raf.seek(0);
            valor = raf.readInt();
            int tmp;
            int cantidadDatos = obtenerCantidadRegistros();
            while (contador < cantidadDatos) {
                tmp = raf.readInt();
                if (tmp > valor) {
                    valor = tmp;
                }
                contador++;
            }
        } catch (IOException ex) {
            System.out.println("Error obteniendo numero mayor");
        }
        return valor;
    }
    /**
     * Metodo que busca el menor de los numeros en el archivo
     * @return el  numero menor
     */
    protected int buscarMenor() {
        int valor = 0;
        try {

            int contador = 1;
            raf.seek(0);
            valor = raf.readInt();
            int tmp;
            int cantidadDatos = obtenerCantidadRegistros();
            while (contador < cantidadDatos) {
                tmp = raf.readInt();
                if (tmp < valor) {
                    valor = tmp;
                }
                contador++;
            }
        } catch (IOException ex) {
            System.out.println("Error obteniendo numero mayor");
        }
        return valor;
    }

    @Override
    public abstract String toString();

}
