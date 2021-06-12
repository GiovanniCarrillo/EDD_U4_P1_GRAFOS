/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yova_
 */
public class Grafo {
    NodoVertice vertice;
    
    public Grafo(){
        vertice=null;
    }
    public boolean insertarVertice(char dato){
        NodoVertice nuevo= new NodoVertice(dato);
        if(nuevo==null) return false;
        
        if(vertice==null){
            vertice=nuevo;
            return true;
        }
        
        //el nuevo se enlaza al final de la lista de VERTICE
        irUltimo();
        vertice.sig=nuevo; // [primero]--->[nuevo]
        nuevo.ant=vertice; // [primero]<---[nuevo]
        return true;
    }

    private void irUltimo() {
        while(vertice.sig!=null){ 
            vertice=vertice.sig;
        }
    }
    private void irPrimero(){
        while(vertice.ant!=null){
            vertice=vertice.ant;
        }
    }
    public String VerificarCamino(char[] camino){
        String cam = "El camino si es válido";
        for(int i=0; i<camino.length-1; i++){
            if(buscarVertice(camino[i]).buscarArista(buscarVertice(camino[i + 1])) == null){
                cam = "El camino no existe";
            }
        }
        return cam;
    }
    
    private NodoVertice buscarVertice(char dato) {
        if (vertice == null) {
            return null;
        }

        irPrimero();
        for (NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig) {
            if (buscar.dato == dato) {
                return buscar;
            }
        }

        return null;
    }
    public int NumeroVertices() {
        if (vertice == null) {
            return 0;
        }
        int cont = 0;
        irPrimero();
        NodoVertice temp = vertice;
        while (temp != null) {
            cont++;
            temp = temp.sig;
        }
        return cont;
    }
    
    public boolean insertarArista(char origen, char destino){
        NodoVertice nodoOrigen=buscarVertice(origen);
        NodoVertice nodoDestino=buscarVertice(destino);
        
        if(nodoOrigen==null || nodoDestino==null){
            return false;
        }
        
        return nodoOrigen.insertarArista(nodoDestino);
    }
    public boolean eliminarArista(char origen, char destino){
        NodoVertice nodoOrigen=buscarVertice(origen);
        NodoVertice nodoDestino=buscarVertice(destino);
        if(nodoOrigen==null || nodoDestino==null){
            return false;
        }
        return nodoOrigen.eliminarArista(nodoDestino);
    }
    public boolean unSoloVertice(){
        return vertice.ant==null && vertice.sig==null;
    }
    
    public String listaAdyacencia(char dato) {
        return buscarVertice(dato) == null? "" : buscarVertice(dato).toString();    
    }    
    public boolean eliminarVertice(char dato){
        if(vertice==null) return false;
        NodoVertice temp=buscarVertice(dato);
        if(temp==null) return false;
        
        //1. que el vertice no tenga aristas a otros vertices
        if(temp.arista!=null) return false;
        //que otros vertices no tengan arista a este vertice a eliminar
        quitaAristasDeOtrosVertice(temp);
        //Esta temp en el 1ero?
        if(temp==vertice){
            if(unSoloVertice()) vertice=null;
            else{
                vertice = temp.sig;
                temp.sig.ant=temp.sig=null;
            }
            return true;
        }
         //esta en el ultimo?
         if(temp.sig==null){
             temp.ant.sig=temp.ant=null;
             return true;
         }
         //temp esta en medio
         temp.ant.sig=temp.sig;
         temp.sig.ant=temp.ant;
         temp.sig=temp.ant=null;
         return true;
    }

    private void quitaAristasDeOtrosVertice(NodoVertice NodoEliminar) {
        irPrimero();
        for(NodoVertice buscar=vertice; buscar!=null; buscar=buscar.sig){
            buscar.eliminarArista(NodoEliminar);
        }
    }    
    
    public boolean[][] matrizAdyacencia() {
        if (vertice == null) {
            return null;
        }
        int n = NumeroVertices();
        int j;
        boolean matriz[][] = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                matriz[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            j = 0;
            while (i != j) {
                j++;
                vertice = vertice.sig;
            }
            NodoArista temp = vertice.arista;
            irPrimero();
            while (temp != null) {
                j = 0;
                while (temp.direccion != vertice) {
                    vertice = vertice.sig;
                    j++;
                }
                matriz[i][j] = true;
                temp = temp.abajo;
                irPrimero();
            }
        }
        return matriz;
    }
}
