/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Trix
 */
public class ArbolGeneral {

    NodoGeneral raiz;

    public ArbolGeneral() {
        raiz = null;
    }

    public boolean insertar(char dato, String path) {
        /*
        1.- Si raíz es null;
        2.- Si Path vacío.
        3.- buscar padre
        4.- crear hijo (siempre que no exista otro hijo con el mismo nombre)
        5.- enlazar padre con hijo
         */
        if (raiz == null) {
            raiz = new NodoGeneral(dato);
            if (raiz == null) {
                return false;
            }
            return true;
        }
        if (path.isEmpty()) {
            return false;
        }

        NodoGeneral padre = buscarNodo(path);
        if (padre == null) {
            return false;
        }

        //REVISAR SI YA EXISTE UN HIJO CON LA MISMA LETRA ANTES DE INSERTAR.
        NodoGeneral hijoYaExiste = buscarNodo(path + "/" + dato);

        //SI EXISTE HIJO ES DECIR !=null ENTONCES RETORNO UN FALSO.
        if (hijoYaExiste != null) {
            return false;
        }

        NodoGeneral nuevo = new NodoGeneral(dato);
        return padre.enlazar(nuevo);
    }

    public NodoGeneral buscarNodo(String path) {
        //  -> /F/W/R
        path = path.substring(1);
        //  -> F/W/R
        String vector[] = path.split("/");
        if (vector[0].charAt(0) == raiz.dato) {
            //El vector contiene solamente una letra:
            if (vector.length == 1) {
                return raiz;
            }

            NodoGeneral padre = raiz;

            for (int i = 1; i < vector.length; i++) {
                padre = padre.obtenerHijo(vector[i].charAt(0));
                if (padre == null) {
                    return null;
                }
            }
            return padre;
        }
        return null; //No coincidió celdilla (0) con raiz;
    }

    public NodoGeneral buscarConRecursividad(String path) {
        //  -> /F/W/R
        path = path.substring(1);
        //  -> F/W/R
        String vector[] = path.split("/");
        if (vector[0].charAt(0) == raiz.dato) {
            //El vector contiene solamente una letra:
            if (vector.length == 1) {
                return raiz;
            }
            NodoGeneral padre = raiz;
            //Se inicializa la variable que ayudará a cliclar la recursividad.
            int m=1;
            /*Se manda a llamar una recursión ciclada con la misma función de:
            
                for (int i = 1; i < vector.length; i++) {
                    padre = padre.obtenerHijo(vector[i].charAt(0));
                    if (padre == null) {
                        return null;
                    }
                }
            Con un parámetro inicializado en 1.
            */
            return buscarConRecursividad(padre, vector, m);
        }
        return null;
    }

    private NodoGeneral buscarConRecursividad(NodoGeneral padre, String[] vector,int m) {
        int aux = vector.length;
        if(m<aux) {
            //Se recibe el parámetro 1, ya que en el ciclo for, la iteración de 'i' está como: "int i=1;", en ese caso se revisa la celdilla 1 como parámetro.
            //Se copia y pega lo tenido en el ciclo "for".
            padre = padre.obtenerHijo(vector[m].charAt(0));
            if (padre == null) {
                return null;
            }
            //Se utiliza la recursión a la hora de llamar este mismo método con un incremento en 'm'.
     
            padre = buscarConRecursividad(padre, vector, m+1);
        } 
        //Se retorna padre.
        return padre;
    }

    public boolean eliminar(String path) {
        if (raiz == null) {
            return false;
        }

        NodoGeneral hijo = buscarNodo(path);
        if (hijo == null) {
            return false;
        }

        //Si es rama, entonces no se puede eliminar, se retorna un falso.
        if (!hijo.esHoja()) {
            return false;
        }

        if (hijo == raiz) {
            raiz = null;
            return true;
        }

        String pathPadre = obtenerPathPadre(path);
        NodoGeneral padre = buscarNodo(pathPadre);
        return padre.desenlazar(hijo);
    }

    private String obtenerPathPadre(String path) {
        int posAntesDeDiagonal = path.lastIndexOf("/") - 1;
        return path.substring(0, posAntesDeDiagonal);
    }

}
