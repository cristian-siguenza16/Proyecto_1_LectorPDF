/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto01archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author criss
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormAbrirArchivo form = new FormAbrirArchivo();
        form.setVisible(true);
    }
    
}
