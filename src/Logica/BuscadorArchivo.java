package Logica;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

/**
 * Representa una interfaz grafica que me permite navegar entre las carpetas del
 * computador para localizar una direccion especifica
 * @author Jorge Galvis Cardenas
 */
public class BuscadorArchivo extends java.awt.Dialog {
    /**
     * Constructor
     * @param parent
     * @param modal 
     */
    public BuscadorArchivo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
   /**
    * Metodo que me permite navegar entre carpetas del computador y obtener la 
    * ruta de un archivo especifico
    * @return la ruta del archivo
    */
    public String buscarArchivo() {
        java.awt.FileDialog fd1 = new java.awt.FileDialog(this, "Seleccione el archivo a abrir", java.awt.FileDialog.LOAD);
        fd1.show();
        String archivoOrigen = fd1.getDirectory() + fd1.getFile();
        if (fd1.getFile() == null) {
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", ERROR_MESSAGE);
            dispose();
        }
        return archivoOrigen;
    }
    /**
     * Metodo que me permite navegar entre carpetas del computador y obtener la
     * ruta donde se desea guardar el archivo
     * @return la ruta en donde se va a guardar el archivo
     */
    public String preguntarArchivoDestino() {
        java.awt.FileDialog fd1 = new java.awt.FileDialog(this, "Seleccione donde va a guardar el archivo", java.awt.FileDialog.SAVE);
        fd1.show();
        String archivoDestino = fd1.getDirectory() + fd1.getFile();
        if (fd1.getFile() == null) {
            JOptionPane.showMessageDialog(this, "Error al preguntar archivo de destino", "Error", ERROR_MESSAGE);
            this.dispose();
        }

        return archivoDestino;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
