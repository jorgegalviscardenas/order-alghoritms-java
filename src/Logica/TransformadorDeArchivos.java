package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que transforma un archivo de texto plano a seis archivos binarios
 * @author Jorge Galvis Cardenas
 * @version 1.0
 */
public class TransformadorDeArchivos {

    /**
     * Ruta del archivo donde estan los numeros en su configuracion inicial
     */
    private String ruta;
    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al QuickSorter
     */
    private RandomAccessFile rafQuick;
    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al ShellSorter
     */
    private RandomAccessFile rafShell;
    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al MergeSorter
     */
    private RandomAccessFile rafMerge;
    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al RadixSorter
     */
    private RandomAccessFile rafRadix;
    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al HeadSorter
     */
    private RandomAccessFile rafHead;
    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al CountingSorter
     */
    private RandomAccessFile rafCounting;

    /**
     * Constructor
     *
     * @param ruta
     */
    public TransformadorDeArchivos(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Metodo que toma los numeros del archivo de texto plano separados por
     * comas, y los copia a seis archivos binarios, cada uno para cada metodo de
     * ordenamiento
     */
    public void transformarArchivo() {
        Scanner scanner = null;
        try {

            File shell = new File("shell");
            shell.delete();
            File quick = new File("quick");
            quick.delete();
            File merge = new File("merge");
            merge.delete();
            File radix = new File("radix");
            radix.delete();
            File heap = new File("heap");
            heap.delete();
            File counting = new File("counting");
            counting.delete();
            File file = new File(getRuta());
            rafShell = new RandomAccessFile(shell, "rw");
            rafQuick = new RandomAccessFile(quick, "rw");
            rafMerge = new RandomAccessFile(merge, "rw");
            rafRadix = new RandomAccessFile(radix, "rw");
            rafHead = new RandomAccessFile(heap, "rw");
            rafCounting = new RandomAccessFile(counting, "rw");
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TransformadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        scanner.useDelimiter(",");
        int numero;
        while (scanner.hasNextInt()) {
            numero = scanner.nextInt();
            //numero=Integer.parseInt(scanner.next().trim());
            try {

                getRafShell().writeInt(numero);
                rafMerge.writeInt(numero);
                rafRadix.writeInt(numero);
                getRafHead().writeInt(numero);
                getRafCounting().writeInt(numero);
                rafQuick.writeInt(numero);
            } catch (IOException ex) {
                System.out.println("Error escribiendo en archivos");
            }
        }
        try {

            numero = Integer.parseInt(scanner.next().trim());

            getRafShell().writeInt(numero);
            rafMerge.writeInt(numero);
            rafRadix.writeInt(numero);
            getRafHead().writeInt(numero);
            getRafCounting().writeInt(numero);
            rafQuick.writeInt(numero);
        } catch (IOException ex) {
            System.out.println("Error escribiendo en archivos");
        }
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al QuickSorter
     *
     * @return the rafQuick
     */
    public RandomAccessFile getRafQuick() {
        return rafQuick;
    }

    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al ShellSorter
     *
     * @return the rafShell
     */
    public RandomAccessFile getRafShell() {
        return rafShell;
    }

    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al MergeSorter
     *
     * @return the rafMerge
     */
    public RandomAccessFile getRafMerge() {
        return rafMerge;
    }

    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al RadixSorter
     *
     * @return the rafRadix
     */
    public RandomAccessFile getRafRadix() {
        return rafRadix;
    }

    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al HeadSorter
     *
     * @return the rafHead
     */
    public RandomAccessFile getRafHead() {
        return rafHead;
    }

    /**
     * Archivo binario que guardara los numeros enteros, el cual va pertenecer
     * al CountingSorter
     *
     * @return the rafCounting
     */
    public RandomAccessFile getRafCounting() {
        return rafCounting;
    }

}
