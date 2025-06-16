package Act4;
import java.util.*; //utilizaremos listas, estructuras y métodos auxiliares del paquete java.util


class Limits { // clase que representa un subconjunto dentro de un arreglo
    int[] array;
    int inicio;
    int fin;
    //metodo constructor que recibe el arreglo y los índices que definen el subconjunto
    Limits(int[] array, int inicio, int fin) {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }
    //devuelve la longitud del subconjunto
    int length() {
        return fin - inicio + 1;
    }
    //verifica si todos los elementos dentro del subconjunto son iguales
    boolean esHomogeneo() {
        int val = array[inicio];
        for (int i = inicio + 1; i <= fin; i++) {
            if (array[i] != val) return false;
        }
        return true;
    }
}

class SetVectors {//representa un conjunto de subconjuntos
    List<Limits> subconjuntos = new ArrayList<>(); 

    void insertar(Limits l) { // inserta un nuevo subconjunto si los indices son validos
        if (l.inicio <= l.fin)
            subconjuntos.add(l);
    }

    boolean esVacio() { // verifica si el conjunto esta vacio
        return subconjuntos.isEmpty();
    }

    Limits mayor() { // extrae y devuelve el subconjunto con mayor longitud
        return subconjuntos.remove(mayorIndex());
    }

    int mayorIndex() { // encuentra el indice del subconjunto con mayor longitud
        int maxLen = -1, index = -1;
        for (int i = 0; i < subconjuntos.size(); i++) {
            if (subconjuntos.get(i).length() > maxLen) {
                maxLen = subconjuntos.get(i).length();
                index = i;
            }
        }
        return index;
    }

    int longMayor() { // devuelve la longitud del subconjunto mas largo
        return subconjuntos.stream().mapToInt(Limits::length).max().orElse(0);
    }
}
// clase para buscar la moda
public class ModaDivideConquista {
    public static int encontrarModa(int[] a) {
        //conjuntos para almacenar subconjuntos homogéneos y heterogéneos
        SetVectors homog = new SetVectors();
        SetVectors heterog = new SetVectors();
        //comenzamos con el arreglo completo como subconjunto inicial
        Limits inicial = new Limits(a, 0, a.length - 1);
        heterog.insertar(inicial);
        //mientras haya subconjuntos heterogéneos más grandes que los homogéneos
        while (heterog.longMayor() > homog.longMayor()) {
            Limits actual = heterog.mayor();
         // elegimos como punto de referencia la mediana del subconjunto
            int mediana = actual.array[(actual.inicio + actual.fin) / 2];

            // Dividir en tres partes
            List<Integer> p1 = new ArrayList<>(), p2 = new ArrayList<>(), p3 = new ArrayList<>();
            for (int i = actual.inicio; i <= actual.fin; i++) {
                if (a[i] < mediana) p1.add(a[i]);
                else if (a[i] == mediana) p2.add(a[i]);
                else p3.add(a[i]);
            }

            //insertamos los subconjuntos resultantes en los conjuntos correspondientes
            if (!p1.isEmpty()) heterog.insertar(new Limits(listToArray(p1), 0, p1.size() - 1));
            if (!p3.isEmpty()) heterog.insertar(new Limits(listToArray(p3), 0, p3.size() - 1));
            if (!p2.isEmpty()) homog.insertar(new Limits(listToArray(p2), 0, p2.size() - 1));
        }

        //si no se encontró ningun subconjunto homogeneo, devuelve el primer valor del arreglo
        if (homog.esVacio()) return a[0];
        // el subconjunto homogeneo mas largo contiene la moda
        Limits moda = homog.mayor();
        return moda.array[moda.inicio];
    }
    // metodo auxiliar para convertir una lista de enteros a arreglo
    static int[] listToArray(List<Integer> lista) {
        return lista.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] arreglo = {4, 2, 4, 4, 6, 4, 6, 2, 6, 6, 6};
        System.out.println("Moda encontrada: " + encontrarModa(arreglo));
    }
}
