/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto01archivos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author criss
 */
public class EscritorArchivo {
    private String nombre;
    private String autor;
    private String creador; 
    private String fechaCreacion;
    private String fechaModificacion;
    private String productor;
    private String version;
    private int tamanio;
    private int paginas;
    private String tamanioPaginas;
    private int imagenes;
    private String fuentes;    
    
     EscritorArchivo()
    {
        nombre = "";
        autor = "";
        creador = "";
        fechaCreacion = "";
        fechaModificacion = "";
        productor = "";
        version = "";
        tamanio = 0;
        paginas = 0;
        tamanioPaginas  = "";
        imagenes  = 0;
        fuentes = "";
    }
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setTamanioPaginas(String tamanioPaginas) {
        this.tamanioPaginas = tamanioPaginas;
    }

    public void setImagenes(int imagenes) {
        this.imagenes = imagenes;
    }

    public void setFuentes(String fuentes) {
        this.fuentes = fuentes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getCreador() {
        return creador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public String getProductor() {
        return productor;
    }

    public String getVersion() {
        return version;
    }

    public int getTamanio() {
        return tamanio;
    }

    public int getPaginas() {
        return paginas;
    }

    public String getTamanioPaginas() {
        return tamanioPaginas;
    }

    public int getImagenes() {
        return imagenes;
    }

    public String getFuentes() {
        return fuentes;
    }
    
  
    public void escrituraAleatoria()
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("Datos.bin", "rw");
            archivo.seek(archivo.length());
            archivo.write(nombre.getBytes());
            archivo.write("|".getBytes());
            archivo.write(autor.getBytes());
            archivo.write("|".getBytes());
            archivo.write(creador.getBytes());
            archivo.write("|".getBytes());
            archivo.write(fechaCreacion.getBytes());
            archivo.write("|".getBytes());
            archivo.write(fechaModificacion.getBytes());
            archivo.write("|".getBytes());
            archivo.write(productor.getBytes());
            archivo.write("|".getBytes());
            archivo.write(version.getBytes());
            archivo.write("|".getBytes());
            archivo.writeInt(tamanio);
            archivo.write("|".getBytes());
            archivo.writeInt(paginas);
            archivo.write("|".getBytes());
            archivo.write(tamanioPaginas.getBytes());
            archivo.write("|".getBytes());
            archivo.writeInt(imagenes);
            archivo.write("|".getBytes());
            archivo.write(fuentes.getBytes());
            archivo.write("!".getBytes());
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} 
