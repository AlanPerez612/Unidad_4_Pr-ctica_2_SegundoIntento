/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Trix
 * Contiene lista de nodos hijos
 * 
 * Contiene:
 * -Inserar -------> Enlazar
 * -Eliminar ------> Desenlazar
 * -buscarNodo ----> obtejerHijo
 */
public class NodoGeneral {
    char dato;
    NodoHijo ini,fin;
    
    public NodoGeneral(char v){
        dato=v;
        ini = fin = null;
    }
    
    public boolean enlazar(NodoGeneral hijo){
        NodoHijo enlace = new NodoHijo(hijo);
        if(enlace ==null)return false;
        
        if(ini==null&&fin==null){
            ini= fin = enlace;
            return true;
        }
        fin.sig=enlace;
        enlace.ant=fin;
        fin=enlace;
        return true;
    }
    
    public boolean esHoja(){
        return ini==null && fin==null;
    }
    
    public NodoGeneral obtenerHijo(char valorHijoBuscado){
       if(esHoja())return null; 
       
       for(NodoHijo buscar = ini; buscar!=null; buscar=buscar.sig){
           if(buscar.direccionHijo.dato==valorHijoBuscado){
               return buscar.direccionHijo;
           }
       }
       return null;
    }
    
    public boolean desenlazar(NodoGeneral hijo) {
        if(ini==fin){
            if(fin.direccionHijo == hijo){
                ini=fin=null;
                        return true;
            }
            return false;
        }
        NodoHijo temp =ini;
        if(temp.direccionHijo == hijo){
            ini = temp.sig;
            ini.ant = temp.sig = temp = null;
            return true;
        }
        if(fin.direccionHijo == hijo){
            temp=fin;
            fin=temp.ant;
            fin.sig = temp.ant = temp = null;
            return true;
        }
        temp = temp.sig;
        while(temp.direccionHijo != hijo && temp != null){
            temp = temp.sig;
        }
        if(temp==null)return false;
        temp.sig.ant = temp.ant;
        temp.ant.sig = temp.sig;
        temp.sig = temp.ant = temp =null;
        return true;
    }
}
