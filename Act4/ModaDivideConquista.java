package Act4;
import java.util.*;

class Limits {
    int[] array;
    int inicio;
    int fin;

    Limits(int[] array, int inicio, int fin) {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }

    int length() {
        return fin - inicio + 1;
    }

    boolean esHomogeneo() {
        int val = array[inicio];
        for (int i = inicio + 1; i <= fin; i++) {
            if (array[i] != val) return false;
        }
        return true;
    }
}

class SetVectors {
    List<Limits> subconjuntos = new ArrayList<>();

    void insertar(Limits l) {
        if (l.inicio <= l.fin)
            subconjuntos.add(l);
    }

    boolean esVacio() {
        return subconjuntos.isEmpty();
    }

    Limits mayor() {
        return subconjuntos.remove(mayorIndex());
    }

    int mayorIndex() {
        int maxLen = -1, index = -1;
        for (int i = 0; i < subconjuntos.size(); i++) {
            if (subconjuntos.get(i).length() > maxLen) {
                maxLen = subconjuntos.get(i).length();
                index = i;
            }
        }
        return index;
    }

    int longMayor() {
        return subconjuntos.stream().mapToInt(Limits::length).max().orElse(0);
    }
}

public class ModaDivideConquista {
    public static int encontrarModa(int[] a) {
        SetVectors homog = new SetVectors();
        SetVectors heterog = new SetVectors();

        Limits inicial = new Limits(a, 0, a.length - 1);
        heterog.insertar(inicial);

        while (heterog.longMayor() > homog.longMayor()) {
            Limits actual = heterog.mayor();
            int mediana = actual.array[(actual.inicio + actual.fin) / 2];

            // Dividir en tres partes
            List<Integer> p1 = new ArrayList<>(), p2 = new ArrayList<>(), p3 = new ArrayList<>();
            for (int i = actual.inicio; i <= actual.fin; i++) {
                if (a[i] < mediana) p1.add(a[i]);
                else if (a[i] == mediana) p2.add(a[i]);
                else p3.add(a[i]);
            }

            if (!p1.isEmpty()) heterog.insertar(new Limits(listToArray(p1), 0, p1.size() - 1));
            if (!p3.isEmpty()) heterog.insertar(new Limits(listToArray(p3), 0, p3.size() - 1));
            if (!p2.isEmpty()) homog.insertar(new Limits(listToArray(p2), 0, p2.size() - 1));
        }

        if (homog.esVacio()) return a[0];

        Limits moda = homog.mayor();
        return moda.array[moda.inicio];
    }

    static int[] listToArray(List<Integer> lista) {
        return lista.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] arreglo = {4, 2, 4, 4, 6, 4, 6, 2, 6, 6, 6};
        System.out.println("Moda encontrada: " + encontrarModa(arreglo));
    }
}
