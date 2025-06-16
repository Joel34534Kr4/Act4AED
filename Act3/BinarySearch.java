package Act3;

public class BinarySearch {
	// metodo recursivo para realizar una busqueda binaria
    int binarySearch(int arr[], int lo, int hi, int x) {
    	//verifica que el rango sea valido
        if (hi >= lo && lo < arr.length - 1) {
        	//calcula el indice del medio
            int mid = lo + (hi - lo) / 2;
            //caso base: el valor esta en el medio
            if (arr[mid] == x)
                return mid;

            // si el valor buscado es menor, buscar en la mitad izquierda
            if (arr[mid] > x)
                return binarySearch(arr, lo, mid - 1, x);
            //si el valor buscado es mayor, buscar en la mitad derecha
            return binarySearch(arr, mid + 1, hi, x);
        }
        // si el rango no es valido, el elemento no esta en el arreglo
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch ob = new BinarySearch();
        int arr[] = { 1, 2, 3, 4, 5 }; // arreglo ordenado
        int n = arr.length; // longitud del arreglo
        int x = 4; // elemento a buscar

        int position = ob.binarySearch(arr, 0, n - 1, x); // llamada al metodo 
        //imprime el resultado
        if (position == -1)
            System.out.println("Element not found !!!");
        else
            System.out.println("Element found at index: " + position);
    }

}

