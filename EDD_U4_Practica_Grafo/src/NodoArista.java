/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yova_
 */
public class NodoArista {
    NodoVertice direccion;
    NodoArista arriba;
    NodoArista abajo;

    public NodoArista(NodoVertice d) {
        direccion = d;
        arriba = abajo = null;
    }     
}
