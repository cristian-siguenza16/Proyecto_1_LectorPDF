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
    
    public Lista()
    {
        this.inicio = null;
    }
    public Nodo getInicio()
    {
        return inicio;
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
