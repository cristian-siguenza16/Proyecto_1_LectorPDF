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
 * @author 
 */


public class LecturaArchivo {
    private byte[] nombre;
    private  byte[] autor;
    private  byte[] creador; 
    private byte[] fechaCreacion; // 23
    private byte[] fechaModificacion; // 23
    private  byte[] productor;
    private byte[] version; // 8
    private int tamanio;
    private int paginas;
    private float tamaniox;
    private float tamanioy;
    private int imagenes;
    private  byte[] fuentes;    
    //private int siguienteR;
    private int contValores;
    
    
  public  LecturaArchivo()
    {
     //nombre = ;
        //autor = "";
        //creador = "";
        fechaCreacion = new byte [23];
        fechaModificacion = new byte [23];
        //productor = "";
        version = new byte [8];
        tamanio = 0;
        paginas = 0;
        tamaniox = 0;
        tamanioy = 0;
        imagenes  = 0;
        //fuentes = "";
       // siguienteR = 0;
        contValores = 0;
    }
  public String getNombre() {
        return new String(nombre);
    }

    public String getAutor() {
        return  new String(autor);
    }

    public String getCreador() {
        return  new String(creador);
    }

    public String getFechaCreacion() {
        return new String(fechaCreacion);
    }

    public String getFechaModificacion() {
        return new String(fechaModificacion);
    }

    public String getProductor() {
        return new String(productor);
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
        return new String(fuentes);
    }
    
  
  //busqueda
  public String busquedaArchivo(String busqueda)
  {
 byte aux =0;  
    int numero;
     String salto = "";
     byte [] indice;
     long ref= 0L;
     int referencia=0;
     String resultado="";
      String partir1 = "";
     
try {
RandomAccessFile archivo = new RandomAccessFile("Datos.bin", "r");
contValores = Integer.parseInt(getContValores());
int cont = 0;

while(cont != contValores)
{
    
archivo.seek(archivo.length()-8);
ref = archivo.readLong();
 archivo.seek(ref + 7);
 
            for(int i = 0; i <=cont; i++)
            {
            numero = archivo.readInt();
            indice = new byte [numero];
            archivo.read(indice);
            salto = new String(indice);
             ref = archivo.readLong();
             archivo.read();
            }
            String partir[] = salto.split("/");
            partir1="";
            for(int j = 0; j < partir[1].length()-1; j++)
            {
                partir1 +=partir[1].charAt(j);
                      
            }
            if(partir[0].equals(busqueda) || partir1.equals(busqueda) || (partir[0]+".pdf").equals(busqueda) )
            {
                 
                 
                
            archivo.seek(ref);
            
            aux = archivo.readByte();
            nombre = new byte[aux];
            archivo.read(nombre);
             aux = archivo.readByte();
            autor = new byte[aux];
            archivo.read(autor);
             aux = archivo.readByte();
            creador = new byte[aux];
            archivo.read(creador);
            archivo.read(fechaCreacion);
            archivo.read(fechaModificacion);
            aux = archivo.readByte();
            productor = new byte[aux];
            archivo.read(productor);
            archivo.read(version);
            tamanio = archivo.readInt();
            paginas = archivo.readInt();
            tamaniox =archivo.readFloat();
            tamanioy = archivo.readFloat();
            imagenes = archivo.readInt();
            aux = archivo.readByte();
            fuentes = new byte[aux];
            archivo.read(fuentes);
            archivo.read();
              resultado += new String(nombre)+'\n';
            resultado += new String(autor)+'\n';
            resultado += new String(creador)+'\n';
            resultado += new String(fechaCreacion)+'\n';
            resultado += new String(fechaModificacion)+'\n';
            resultado += new String(productor)+'\n';
            resultado += new String(version)+'\n';
            resultado +=  String.valueOf(tamanio)+'\n';
            resultado +=  String.valueOf(paginas)+'\n';
            resultado +=  String.valueOf(tamaniox)+ " X " ;
            resultado +=  String.valueOf(tamanioy)+ " mm"+'\n';
            resultado +=  String.valueOf(imagenes)+'\n';
            resultado += new String(fuentes)+'\n'; 
            }
            cont++;
            }
            
archivo.close();
} catch (FileNotFoundException ex) {
Logger.getLogger(LecturaArchivo.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
Logger.getLogger(LecturaArchivo.class.getName()).log(Level.SEVERE, null, ex);
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
  
    public String lecturaAleatoria()
{
    byte aux =0; 
    long aux2 = 0L;
    int numero=0;
     String salto;
     byte [] indice;
     long ref = 0L;
     int referencia=0;
    String resultado="";

try {
RandomAccessFile archivo = new RandomAccessFile("Datos.bin", "r");
contValores = Integer.parseInt(getContValores());
archivo.seek(0);

            for(int i = 0; i <contValores; i++)
            {
              aux = archivo.readByte();;
             System.out.println(aux);
            nombre = new byte[aux];
            archivo.read(nombre);
            aux = archivo.readByte();
             System.out.println(aux);
            autor = new byte[aux];
            archivo.read(autor);
            
             aux = archivo.readByte();
              System.out.println(aux);
            creador = new byte[aux];
            
            archivo.read(creador);
            archivo.read(fechaCreacion);
            archivo.read(fechaModificacion);
            aux = archivo.readByte();
             System.out.println(aux);
            productor = new byte[aux];
            archivo.read(productor);
            archivo.read(version);
            tamanio = archivo.readInt();
            paginas = archivo.readInt();
            tamaniox =archivo.readFloat();
            tamanioy = archivo.readFloat();
            imagenes = archivo.readInt();
            aux = archivo.readByte();
            fuentes = new byte[aux];
            archivo.read(fuentes);
            archivo.read();
      
            resultado += new String(nombre)+'\n';
            
            resultado += new String(autor)+'\n';
            resultado += new String(creador)+'\n';
            resultado += new String(fechaCreacion)+'\n';
            resultado += new String(fechaModificacion)+'\n';
            resultado += new String(productor)+'\n';
            resultado += new String(version)+'\n';
            resultado +=  String.valueOf(tamanio)+'\n';
            resultado +=  String.valueOf(paginas)+'\n';
            resultado +=  String.valueOf(tamaniox)+ " X " ;
            resultado +=  String.valueOf(tamanioy)+ " mm"+'\n';
            resultado +=  String.valueOf(imagenes)+'\n';
            resultado += new String(fuentes)+'\n'; 
            
            
            }
            

//leer string
//byte letras[] = new byte[8];
//archivo.read(letras);
//System.out.println(new String(letras));

archivo.close();
} catch (FileNotFoundException ex) {
Logger.getLogger(LecturaArchivo.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
Logger.getLogger(LecturaArchivo.class.getName()).log(Level.SEVERE, null, ex);
}
return resultado;
}
}
