/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto01archivos;

/**
 *
 * @author criss
 */
public class Lista {
    private Nodo inicio;
    private NodoM frente;
    private NodoM fondo;
    
    public Lista()
    {
        this.inicio = null;
    }
    public Nodo getInicio()
    {
        return inicio;
    }
    public NodoM getInicioM()
    {
        return frente;
    }
    
    public NodoM getFinalM()
    {
        return fondo;
    }
    

     public void InsertarM(String nombre, String autor, String creador, String fechaCreacion, String fechaModificacion, String productor, String version, int tamanio, int paginas, float tamaniox, float tamanioy, int imagenes, String fuentes)
    {
       frente = InsertarRegistro(nombre, autor, creador,fechaCreacion ,fechaModificacion,productor,version,tamanio,paginas,tamaniox,tamanioy,imagenes,fuentes,frente);
    }
    private NodoM InsertarRegistro(String nombre, String autor, String creador, String fechaCreacion, String fechaModificacion, String productor, String version, int tamanio, int paginas, float tamaniox, float tamanioy, int imagenes, String fuentes,NodoM actual)
    {
        if(actual==null){
                NodoM aux = new NodoM();
        aux.setNombre(nombre);
        aux.setAutor(autor);
        aux.setCreador(creador);
        aux.setFechaCreacion(fechaCreacion);
        aux.setFechaModificacion(fechaModificacion);
        aux.setProductor(productor);
        aux.setVersion(version);
        aux.setTamanio(tamanio);
        aux.setPaginas(paginas);
        aux.setTamaniox(tamaniox);
        aux.setTamanioy(tamanioy);
        aux.setImagenes(imagenes);
        aux.setFuentes(fuentes);
            aux.setSiguiente(null);
            aux.setAnterior(fondo);
            fondo=aux;
            actual = aux;
        }
        else
        {
            actual.setSiguiente(InsertarRegistro(nombre, autor, creador,fechaCreacion ,fechaModificacion,productor,version,tamanio,paginas,tamaniox,tamanioy,imagenes,fuentes,actual.getSiguiente()));
        }
        return actual;
    }
    public void MostrarM()
    {
        NodoM aux = frente;
        while(aux!=null)
        {
          System.out.println("nombre: " + aux.getNombre());
          System.out.println("autor: " + aux.getAutor());
          System.out.println("version: " + aux.getVersion());
          aux = aux.getSiguiente();
        }
    }
    public void Insertar(int numero, String indice, long ref)
    {
        Nodo aux = new Nodo();
        aux.setNumero(numero);
        aux.setIndice(indice);
        aux.setRef(ref);
        if (inicio == null)
        {
            inicio = aux;
            inicio.setSiguiente(null);
        }
        else 
        {
            Nodo aux2 = inicio;
            while(aux2!= null)
            {
                if (aux2.getSiguiente() == null)
                {
                    aux2.setSiguiente(aux);
                    aux.setSiguiente(null);
                }
                aux2 = aux2.getSiguiente();
            }
        }
    }
    public void Buscar(int numero)
    {
        Nodo aux = inicio;
        while(aux!=null)
        {
          if (aux.getNumero() == numero)
          {
          System.out.println("numero encontrado: " + aux.getNumero());
          }
          aux = aux.getSiguiente();
        }
    } 
    public void Mostrar()
    {
        Nodo aux = inicio;
        while(aux!=null)
        {
          System.out.println("numero: " + aux.getNumero());
          System.out.println("indice: " + aux.getIndice());
          System.out.println("ref: " + aux.getRef());
          aux = aux.getSiguiente();
        }
    }
    public int getNumero(Nodo aux)
    {
        return aux.getNumero();
    }
    public String getInicio(Nodo aux)
    {
        return aux.getIndice();
    }
    public long getRef(Nodo aux)
    {
        return aux.getRef();
    }
}
