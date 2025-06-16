package Act3;
public class BinarySearchIterativo {

	// metodo recursivo para realizar una busqueda binaria
    int binarySearch(int arr[], int x) {
    	
        int lo = 0, hi = arr.length - 1;
        // Bucle hasta que el rango sea válido
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // Si el elemento está en el medio
            if (arr[mid] == x)
                return mid;
            // Si el elemento es mayor, ignorar la mitad izquierda
            if (arr[mid] < x)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        // Si no se encuentra el elemento
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchIterativo ob = new BinarySearchIterativo();
        int arr[] = { 1, 2, 3, 4, 5 }; // arreglo ordenado
        int n = arr.length;
        int x = 3;//elemento a buscar
        int position = ob.binarySearch(arr, x); //llamada al metodo
        //imprime resultado
        if (position == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index: " + position);
    }
}

