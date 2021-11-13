/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto01archivos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    private char[] fechaCreacion; // 23
    private char[] fechaModificacion; // 23
    private String productor;
    private char[] version; // 8
    private int tamanio;
    private int paginas;
    private float tamaniox;
    private float tamanioy;
    private int imagenes;
    private String fuentes;    
    private long siguienteR;
    private int contValores;
    Lista listado;
    
    
     EscritorArchivo()
    {
        nombre = "";
        autor = "";
        creador = "";
        fechaCreacion = new char [23];
        fechaModificacion = new char [23];
        productor = "";
        version = new char [8];
        tamanio = 0;
        paginas = 0;
        tamaniox = 0;
        tamanioy = 0;
        imagenes  = 0;
        fuentes = "";
        siguienteR = 0;
        contValores = 0;
        listado = new Lista();
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
        for(int i=0; i<23; i++)
        {
            this.fechaCreacion[i] = fechaCreacion.charAt(i);
        }
    }

    public void setFechaModificacion(String fechaModificacion) {
        for(int i=0; i<23; i++)
        {
            this.fechaModificacion[i] = fechaModificacion.charAt(i);
        }
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public void setVersion(String version) {
        for(int i=0; i<8; i++)
        {
            this.version[i] = version.charAt(i);
        }
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setTamanioPaginas(float tamaniox, float tamanioy) {
        this.tamaniox = tamaniox;
        this.tamanioy = tamanioy;
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
        return new String(fechaCreacion);
    }

    public String getFechaModificacion() {
        return new String(fechaModificacion);
    }

    public String getProductor() {
        return productor;
    }

    public String getVersion() {
        return new String(version);
    }

    public int getTamanio() {
        return tamanio;
    }

    public int getPaginas() {
        return paginas;
    }

    public float getTamanioPaginasX() {
        return tamaniox;
    }
    public float getTamanioPaginasY() {
        return tamanioy;
    }

    public int getImagenes() {
        return imagenes;
    }

    public String getFuentes() {
        return fuentes;
    }
    
  
    public void escrituraAleatoria()
    {
        int contador = 0;
        try {
            RandomAccessFile archivo = new RandomAccessFile("Datos.bin", "rw");
            if(archivo.length() == 0)
            {
            archivo.seek(0);
            archivo.writeByte(nombre.length());  //1
            contador++;
            archivo.writeBytes(nombre);
            contador = contador + nombre.length();
            archivo.writeByte(autor.length());  //1
            contador++;
            archivo.writeBytes(autor);
            contador = contador + autor.length();
            archivo.writeByte(creador.length()); //1
            contador++;
            archivo.writeBytes(creador);
            contador = contador + creador.length();
            archivo.writeBytes(new String(fechaCreacion)); //23
            contador = contador + 23;
            archivo.writeBytes(new String(fechaModificacion));//23
            contador = contador + 23;
            archivo.writeByte(productor.length()); //1
            contador++;
            archivo.writeBytes(productor);
            contador = contador + productor.length();
            archivo.writeBytes(new String(version)); //8
            contador = contador + 8;
            archivo.writeInt(tamanio); //4
            contador = contador + 4;
            archivo.writeInt(paginas); //4
            contador = contador + 4;
            archivo.writeFloat(tamaniox); //4
            contador = contador + 4;
            archivo.writeFloat(tamanioy); //4
            contador = contador + 4;
            archivo.writeInt(imagenes); //4
            contador = contador + 4;
            archivo.writeByte(fuentes.length()); //1
            contador++;
            archivo.writeBytes(fuentes); // +fuentes.length 
            contador = contador + fuentes.length();
            archivo.write('\n'); //1
            contador++;
            siguienteR = contador;
            archivo.write("Indice".getBytes());
            archivo.write('\n');
            String indice = nombre +"/" + autor + " ";
            archivo.writeInt(nombre.length() + autor.length() + 2);
            archivo.writeBytes(indice);
            contador = contador + nombre.length() + autor.length() + 2;
             long ref = 0L;
             archivo.writeLong(ref);
           
            archivo.write('\n');
            contador = contador + 11;
            contValores = 1;
            }
            else{
                leerIndices();
                this.siguienteR = Integer.parseInt(getSiguienteR());
                contador = 0;
            archivo.seek(siguienteR);
            archivo.writeByte(nombre.length());  //1
            contador++;
            archivo.writeBytes(nombre);
            contador = contador + nombre.length();
            archivo.writeByte(autor.length());  //1
            contador++;
            archivo.writeBytes(autor);
            contador = contador + autor.length();
            archivo.writeByte(creador.length()); //1
            contador++;
            archivo.writeBytes(creador);
            contador = contador + creador.length();
            archivo.writeBytes(new String(fechaCreacion)); //23
            contador = contador + 23;
            archivo.writeBytes(new String(fechaModificacion));//23
            contador = contador + 23;
            archivo.writeByte(productor.length()); //1
            contador++;
            archivo.writeBytes(productor);
            contador = contador + productor.length();
            archivo.writeBytes(new String(version)); //8
            contador = contador + 8;
            archivo.writeInt(tamanio); //4
            contador = contador + 4;
            archivo.writeInt(paginas); //4
            contador = contador + 4;
            archivo.writeFloat(tamaniox); //4
            contador = contador + 4;
            archivo.writeFloat(tamanioy); //4
            contador = contador + 4;
            archivo.writeInt(imagenes); //4
            contador = contador + 4;
            archivo.writeByte(fuentes.length()); //1
            contador++;
            archivo.writeBytes(fuentes); // +fuentes.length 
            contador = contador + fuentes.length();
            archivo.write('\n'); //1
            contador++;
            siguienteR = siguienteR + contador;
            
            //siguienteI = Integer.parseInt(getSiguienteI());
            //siguienteI = siguienteI + contador;
            //archivo.seek(siguienteI);
            //contador = siguienteI;
            archivo.write("Indice".getBytes());
            archivo.write('\n');
            //contador = contador + 7;
            String indice = nombre +"/" + autor + " ";
            long ref = 0L;
            String refactual = getSiguienteR();
            ref = Long.parseLong(refactual);
           // int cont = 1;
          
            listado.Insertar(indice.length(), indice, ref);  // listamos el nuevo
            listado.Mostrar();
            Nodo aux = listado.getInicio();
            contValores = 0;
            while(aux!=null)
            {
                archivo.writeInt(aux.getNumero());
                archivo.writeBytes(aux.getIndice());
                archivo.writeLong((aux.getRef()));
                archivo.write('\n');
                contValores++;
                aux = aux.getSiguiente();
            }
                    }
            long indice = siguienteR; 
            archivo.write("EndOfIndice".getBytes());
            archivo.write('\n');
            archivo.write("StartIndice".getBytes());
            archivo.writeLong(indice);
            System.out.println(siguienteR);
            crearArchivoValores(siguienteR, contValores);
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void crearArchivoValores(long siguienteR, int contValores)
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("Valores.txt", "rw");
            
            archivo.writeBytes(String.valueOf(siguienteR));
            archivo.write('\n');
            archivo.writeBytes(String.valueOf(contValores));
            archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String getSiguienteR()
    {
        String resultado ="";
        try {
            RandomAccessFile archivo = new RandomAccessFile("Valores.txt", "r");
            resultado = archivo.readLine();
            archivo.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo solicitado");
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            System.out.println("Tengo problemas para cerrar el archivo");
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    private String getContValores()
    {
        String resultado ="";
        try {
            RandomAccessFile archivo = new RandomAccessFile("Valores.txt", "r");
            resultado = archivo.readLine();
            resultado = archivo.readLine();
            archivo.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo solicitado");
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            System.out.println("Tengo problemas para cerrar el archivo");
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    private void leerIndices()
    {int aux;
     int numero;
     String salto;
     byte [] indice;
     long ref = 0L;
        String resultado ="";
        try {
            RandomAccessFile archivo = new RandomAccessFile("Datos.bin", "rw");
            contValores = Integer.parseInt(getContValores());
            archivo.seek(archivo.length() - 8);
            ref = archivo.readLong();
            archivo.seek(ref + 7);
            for(int i = 0; i <contValores; i++)
            {
            numero = archivo.readInt();
            indice = new byte [numero];
            archivo.read(indice);
            ref = archivo.readLong();
           
            archivo.readLine();
            listado.Insertar(numero, new String (indice),(ref));
            }
            archivo.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo solicitado");
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            System.out.println("Tengo problemas para cerrar el archivo");
            Logger.getLogger(EscritorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 
