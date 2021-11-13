/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto01archivos;

/**
 *
 * @author palit
 */
public class NodoM {
    private String nombre;
    private String autor;
    private String creador; 
    private String fechaCreacion; // 23
    private String fechaModificacion; // 23
    private String productor;
    private String version; // 8
    private int tamanio;
    private int paginas;
    private float tamaniox;
    private float tamanioy;
    private int imagenes;
    private String fuentes; 
    private NodoM siguiente;
    private NodoM anterior;
    //private int siguienteR;
    //private int contValores;

    public NodoM(){
        this.siguiente = null;
        this.anterior = null;
    }
    
     public void setSiguiente(NodoM siguiente)
    {
        this.siguiente = siguiente;
    }
    public NodoM getSiguiente()
    {
        return siguiente;
    } 
    
    public void setAnterior(NodoM anterior)
    {
        this.anterior = anterior;
    }
    public NodoM getAnterior()
    {
        return anterior;
    } 
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public float getTamaniox() {
        return tamaniox;
    }

    public void setTamaniox(float tamaniox) {
        this.tamaniox = tamaniox;
    }

    public float getTamanioy() {
        return tamanioy;
    }

    public void setTamanioy(float tamanioy) {
        this.tamanioy = tamanioy;
    }

    public int getImagenes() {
        return imagenes;
    }

    public void setImagenes(int imagenes) {
        this.imagenes = imagenes;
    }

    public String getFuentes() {
        return fuentes;
    }

    public void setFuentes(String fuentes) {
        this.fuentes = fuentes;
    }
    
    
    
}
