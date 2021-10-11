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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */

public class LectorPDF {
    private File ruta;
    private int Startxref=0;
    private int root=0;
    private int catalogo=0;

    LectorPDF(File ruta){
        this.ruta=ruta;
    }
    
    public String LecturaMetaDatos(){
        String cadena="";
        String nombres = "";
        String info = "";
        int refMetadatos=0;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta,"r");
            byte ref[] = new byte[9];
          
            
            //regresamos 300 bytes para buscar la posicion de la tabla de las referencias
            archivo.seek(archivo.length() - 350);
            while((cadena = archivo.readLine()) != null){
             //System.out.println( cadena);             
             if("startxref".equals(cadena)){               
                 cadena = archivo.readLine();
                 //System.out.println( cadena);
                 System.out.println("-------------------");
                 Startxref = Integer.parseInt(cadena);
                 //System.out.println("Referencia: " + referencia);
                 break;
             }
             else if("trailer".equals(cadena)){                      
                info = archivo.readLine();
                //  System.out.println( info);
                if("<<".equals(info)){
                     info += archivo.readLine();
                     info += archivo.readLine();
                     info += archivo.readLine();
                }
             }
            }
            
             String[] partir = info.split("/");
             int a = info.split("/").length;
             int auxRef = 0;
             String buscador ="";
             for(int j=1; j<a; j++){
                 for(int k=0; k<4; k++){
                    buscador+= partir[j].charAt(k);
                 }
                 
                 if("Info".equals(buscador)){
                    String[] auxPartir = partir[j].split(" ");
                    auxRef = Integer.parseInt(auxPartir[1]);
                 }
                 
                 else if ("Root".equals(buscador)) {
                     String[] auxPartir = partir[j].split(" ");
                     root = Integer.parseInt(auxPartir[1]);
                     buscador="";
                 }
                 
                 else{
                     buscador="";
                 }
             }
              
               
            archivo.seek(Startxref);
            cadena = archivo.readLine(); // lee xref
            cadena = archivo.readLine(); // lee el valor a donde tenemos que ir para leer los metadatos
            int aux =0;
            String dato = "";
            
            try{
                for(int i = 2; i< cadena.length(); i++){
                    dato += cadena.charAt(i);
                }                          
            
                aux = Integer.parseInt(dato); // convertir el valor de los metadatos a entero
            }
            catch(NumberFormatException e){
                System.out.println("No se puede convertir");
                aux = 0;
            }            
            if(aux != auxRef + 1){
                if(auxRef == 0){
                    auxRef = aux;
                    nombres = "No se encontro ninguna referencia de los metadatos";
                }
                aux = auxRef+1;
            }
            
            
            //System.out.println("Numero: " + aux);
           
            
            byte meta[] = new byte[10];
            int contador = 0;
            archivo.seek(Startxref);
            if(!"No se encontro ninguna referencia de los metadatos".equals(nombres)){
                while((cadena = archivo.readLine()) != null){                    
                    //System.out.println( cadena);
                    if(contador == aux){  
                        archivo.read(meta);
                        refMetadatos = Integer.parseInt(new String(meta));
                        //System.out.println("Byte de referencia: " + refMetadatos);
                        archivo.seek(refMetadatos);
                        //System.out.println("leyendo metadatos");
                   
                        for(int i = 0; i< 5; i++){
                            cadena = archivo.readLine();
                            if(cadena.equals("endobj")){
                                i =5;
                            }
                            else{
                                nombres += cadena + '\n';
                            }
                        }
                        break;
                    }
                    contador++;
                }            
            }            
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombres;
    }
    
    public String LecturaVersion(){        
        String version="";
        try {            
            RandomAccessFile archivo = new RandomAccessFile(ruta,"r");         
            version=archivo.readLine();
            archivo.close();
            //regresamos 300 bytes para buscar la posicion de la tabla de las referencias       
          
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return version; 
    }
    
    public String Tamanio(){        
        String tamanio="";
            
        try {            
            RandomAccessFile archivo = new RandomAccessFile(ruta,"r");              
            tamanio="Tamaño del archivo: " + archivo.getChannel().size() + " Bytes";
            archivo.close();
            //regresamos 300 bytes para buscar la posicion de la tabla de las referencias       
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tamanio;
    }
    
    public int Numpaginas(){
        int paginas=0;
        String cadena="";
        String info="";      
        int referencia=0;
        try {            
            RandomAccessFile archivo = new RandomAccessFile(ruta,"r");
            byte meta[] = new byte[10];
            int contador = 0;
            archivo.seek(Startxref);           
            
            while((cadena = archivo.readLine()) != null){
            
               if(contador == root+1){                       
                   archivo.read(meta);
                   referencia = Integer.parseInt(new String(meta));
                   //System.out.println("Byte de referencia: " + referencia);
                   archivo.seek(referencia);
                   info = archivo.readLine();
                   info = archivo.readLine();
                    if("<<".equals(info)){
                       info += archivo.readLine();
                       info += archivo.readLine();
                       info += archivo.readLine();                     
                    }            
                   String[] partir = info.split("/");
                   int a = info.split("/").length;       
                               
                   for(int j=1; j<a; j++){
                        if("Catalog".equals(partir[j])){
                           String[] auxPartir = partir[j+1].split(" ");
                           catalogo = Integer.parseInt(auxPartir[1]);
                           break;
                        }               
                    }
                    break;                   
               }
             contador++;
            }
         
            archivo.seek(Startxref);
            contador=0;
            while((cadena = archivo.readLine()) != null){
               // System.out.println( cadena);
                //System.out.println( auxRef);
                
               if(contador == catalogo+1){                   
                   archivo.read(meta);
                   referencia = Integer.parseInt(new String(meta));
                 // System.out.println("Byte de referencia: " + referencia);
                   archivo.seek(referencia);
                   info = archivo.readLine();
                   info = archivo.readLine();
                   
                   if("<<".equals(info)){
                       info += archivo.readLine();
                       info += archivo.readLine();
                       info += archivo.readLine();
                    }                   
                 //  System.out.println(info);
                   String[] partir = info.split("/");
                   int a = info.split("/").length;           
            
                   for(int j=1; j<a; j++){
                        if("Pages".equals(partir[j])){
                           String[] auxPartir = partir[j+1].split(" ");
                           paginas = Integer.parseInt(auxPartir[1]);
                        }                  
                    }
                    break;
               }
                contador++;
            }
                        
            archivo.close();
      
            //regresamos 300 bytes para buscar la posicion de la tabla de las referencias
           
        }
          catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paginas;
    }
    
    public String TamanioPag(){        
        int x=0;
        int y=0;
        float x2=0;
        float y2=0;
        String Tamanio="";
        String cadena="";
        String info="";
        int auxRef = 0;
        int referencia=0;
        String buscador="";
        double pixel=0.35277777778;
     try {
            RandomAccessFile archivo = new RandomAccessFile(ruta,"r");
          byte meta[] = new byte[10];
            int contador = 0;
            archivo.seek(Startxref);
            while((cadena = archivo.readLine()) != null){
            
               if(contador == catalogo+1){
                   archivo.read(meta);
                   referencia = Integer.parseInt(new String(meta));
                   //System.out.println("Byte de referencia: " + referencia);
                   archivo.seek(referencia);
                   info = archivo.readLine();
                   info = archivo.readLine();
                    if("<<".equals(info)){
                       info += archivo.readLine();
                       info += archivo.readLine();
                       info += archivo.readLine();                    
                    }            
                    String[] partir = info.split("/");                 
                    int a = info.split("/").length;
       
           
                    for(int j=1; j<a; j++){  
                        for (int k = 0; k < 4; k++) {
                            buscador+=partir[j].charAt(k);
                        }
                        
                        if("Kids".equals(buscador)){
                            String[] auxPartir = partir[j].split(" ");
                            auxRef = Integer.parseInt(auxPartir[1]);                    
                     break;
                 } 
                 else{
                     buscador="";
                 }
                     
             }
                   break;
                   
               }
             contador++;
            }
        // System.out.println("Referencia de hijos: " +auxRef);
            archivo.seek(Startxref);
            contador=0;
            buscador="";
            while((cadena = archivo.readLine()) != null){
               // System.out.println( cadena);
               // System.out.println( auxRef);
                
               if(contador == auxRef+1){
                   archivo.read(meta);
                   referencia = Integer.parseInt(new String(meta));
                  //System.out.println("Byte de referencia: " + referencia);
                   archivo.seek(referencia);
                   info = archivo.readLine();
                    info = archivo.readLine();
                    if("<<".equals(info)){
                        info += archivo.readLine();
                        info += archivo.readLine();
                        info += archivo.readLine();
                    }
                    // System.out.println(info);
                    String[] partir = info.split("/");
                    int a = info.split("/").length;
                    for(int j=1; j<a; j++){
                        for (int k = 0; k < 1; k++) {
                            buscador+=partir[j].charAt(k);
                        }
                        
                        if("M".equals(buscador)){
                            String[] auxPartir = partir[j].split(" ");
                            x = Integer.parseInt(auxPartir[3]);
                            String auxY="";
                            for (int i = 0; i < auxPartir[4].length()-1; i++) {
                                auxY+=auxPartir[4].charAt(i);
                            }
                            y=Integer.parseInt(auxY);
                        } 
                        else {
                            buscador="";
                        }                 
                    }
                    break;
               }
             contador++;
            }
            x2=(float) (x*pixel);
            y2=(float) (y*pixel);
            Tamanio=String.valueOf(x2);
            Tamanio+=" X "+ String.valueOf(y2);
            archivo.close();
      
            //regresamos 300 bytes para buscar la posicion de la tabla de las referencias
          
          
            }
          catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
     return Tamanio;
    }
    //Metodo de Listas Imagenes y Fuentes
    //Metadatos
    //Version del PDF
    //Metodo de numero de paginas
    //Metodo de tamaño de paginas
    //Metodo de Tamaño de archivo
    public int LectorImagenes(){
        int Tamanio=0;
        String cadena="";
        String info="";  
        int referencia=0;
        String buscador="";
        int hijos[]= new int [2];
        int hojas = 0;
        
        try {            
            RandomAccessFile archivo = new RandomAccessFile(ruta,"r");
            byte meta[] = new byte[10];
            int contador = 0;
            archivo.seek(Startxref);
            while((cadena = archivo.readLine()) != null){            
               if(contador == catalogo+1){
                   archivo.read(meta);
                   referencia = Integer.parseInt(new String(meta));
                   //System.out.println("Byte de referencia: " + referencia);
                   archivo.seek(referencia);
                   info = archivo.readLine();
                   info = archivo.readLine();                   
                   if("<<".equals(info)){
                       info += archivo.readLine();
                       info += archivo.readLine();
                       info += archivo.readLine();                     
                    }            
                   String[] partir = info.split("/");
                                            
                   int a = info.split("/").length;
                         
           
                    for(int j=1; j<a; j++){
                        int contaux = 0;
                        for (int k = 0; k < 4; k++) {
                            buscador+=partir[j].charAt(k);
                        }
                
                        if("Kids".equals(buscador)){
                            String[] auxPartir = partir[j].split(" ");
                            hijos = new int [partir[j].split(" ").length / 3];                     
                            hojas = partir[j].split(" ").length / 3;
                            for (int k = 1; k < partir[j].split(" ").length - 2; k = k+3) {                       
                                hijos[contaux]=Integer.parseInt(auxPartir[k]);                     
                                contaux++;
                            }
                            break;
                        }   
                        else{
                            buscador="";
                        }                     
                    }
                   break;                   
               }
             contador++;
            }
        // System.out.println("Referencia de hijos: " +auxRef);
            
            
            for(int c=0; c<hojas; c++){                
                archivo.seek(Startxref);
                contador=0;
                
                while((cadena = archivo.readLine()) != null){
                  
                    if(contador==hijos[c]+1){
                        archivo.read(meta);
                        referencia = Integer.parseInt(new String(meta));
                        archivo.seek(referencia);
                        info = archivo.readLine();
                        info = archivo.readLine();
                        if("<<".equals(info)){
                            info += archivo.readLine();
                            info += archivo.readLine();
                            info += archivo.readLine();
                        }
                        // System.out.println(info);
                        String[] partir = info.split("/");
                        int a = info.split("/").length;
                        
                        for(int j=1; j<a; j++){
                            for (int k = 0; k < 1; k++) {
                                buscador+=partir[j].charAt(k);
                            }
                
                            if("X".equals(buscador)){                               
                                do{
                                    j++;
                                    buscador = "";
                                    for (int k = 0; k < 5; k++) {
                                        buscador+=partir[j].charAt(k);
                                    }
                                    if("Image".equals(buscador)){
                                        Tamanio++;
                                    }
                                }while(buscador.equals("Image"));                            
                            } 
                            else {
                                buscador="";
                            }                 
                        }  
                                        
                        break;
                    }
                    contador++;
                }
            }
            
            
            archivo.close();
      
            //regresamos 300 bytes para buscar la posicion de la tabla de las referencias
          
          
            }
          catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Tamanio;
        
    }
}
