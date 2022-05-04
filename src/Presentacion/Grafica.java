package Presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Jorge Galvis Cardenas
 * @version 1.0
 */
public class Grafica extends javax.swing.JPanel {

    /**
     * Archivo binario que contiene numeros enteros, el cual le va a servir de
     * base para ordenar
     */
    private RandomAccessFile raf;
    /**
     * Es la escala que representa una unidad entera, para cada barra
     */
    private double escala;
    /**
     * Es el ancho de cada barra
     */
    private double anchura;
    /**
     * Es el alto del panel
     */
    private int alto;
    /**
     * Es la cantidad de numeros que contiene del archivo
     */
    private int cantidadDatos;
    /**
     * Es el numero menor del archivo
     */
    private int menor;
    /**
     * Es el numero mayor del archivo
     */
    private int mayor;
    /**
     * Es el color con el cual voy a pintar las barras
     */
    private Color color;
    /**
     * Es la separacion entre barras
     */
    private static final double SEPARACION = 2;

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param color
     */
    public Grafica(int x, int y, int w, int h, Color color) {

        initComponents();
        this.setLocation(x, y);
        this.setSize(w, h);
        this.color = color;
    }

    /**
     * Constructor
     *
     * @param raf
     * @param x
     * @param y
     * @param w
     * @param h
     * @param color
     */
    public Grafica(RandomAccessFile raf, int x, int y, int w, int h, Color color) {
        try {
            this.color = color;
            initComponents();
            this.raf = raf;
            this.setLocation(x, y);
            this.setSize(w, h);
            cantidadDatos = (int) (raf.length()) / 4;
            anchura = (this.getWidth() - (cantidadDatos * 2)) / cantidadDatos;
            this.raf.seek(0);
        } catch (IOException ex) {
            System.out.println("Error obteniendo cantidad Datos");
        }

    }

    /**
     * Asigna el numero menor del archivo
     *
     * @param menor
     */
    public void setMenor(int menor) {
        this.menor = menor;

    }

    /**
     * Asigna el numero mayor del archivo
     *
     * @param mayor
     */
    public void setMayor(int mayor) {
        this.mayor = mayor;
        escala = this.getHeight() / mayor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        alto = this.getHeight();
        long contador = 0;
        g2.setColor(color);
        try {
            Rectangle2D r;
            raf.seek(0);
            while (contador != cantidadDatos) {
                int valor = raf.readInt();
                r = new Rectangle2D.Double(contador * (anchura + SEPARACION), alto - (escala * valor), anchura, valor * escala);
                g2.draw(r);
                g2.fill(r);
                contador++;
            }
        } catch (IOException ex) {
            repaint();
            System.out.println("Error");
        }
    }
    /**
     * Metodo que repinta dos componentes, y los intercambia graficamente
     * @param g
     * @param pos1
     * @param pos2
     * @param sinCambio 
     */
    public void repintarComponente(Graphics g, int pos1, int pos2, boolean sinCambio) {
        Graphics2D g2 = (Graphics2D) g;
        long contador = 0;
        try {
            Rectangle2D r;
            raf.seek(pos1 * 4);
            int valor = raf.readInt();
            if (sinCambio) {
                g2.setColor(Color.GREEN);
            } else {
                g2.setColor(Color.MAGENTA);
            }
            r = new Rectangle2D.Double(pos1 * (anchura + 2), alto - (escala * valor), anchura, valor * escala);
            g2.draw(r);
            g2.fill(r);
            raf.seek(pos2 * 4);
            valor = raf.readInt();
            if (sinCambio) {
                g2.setColor(Color.MAGENTA);
            } else {
                g2.setColor(Color.GREEN);
            }
            r = new Rectangle2D.Double(pos2 * (anchura + 2), alto - (escala * valor), anchura, valor * escala);
            g2.draw(r);
            g2.fill(r);
        } catch (IOException ex) {

            System.out.println("Error dibujando diagrama propio");

        }
        this.repaint();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
