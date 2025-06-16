package Act5;

public class CorteVarillaDP { // Clase que resuelve el problema del corte de varilla
    // Método con programación dinámica
    static int obtenerMaximoValor(int[] precios, int longitud) {
        int[] dp = new int[longitud + 1];        // dp[i] almacenará el valor máximo para una varilla de longitud i

        for (int i = 1; i <= longitud; i++) {        // Recorremos todas las longitudes desde 1 hasta 'longitud'
            int maxValor = -1;
            //para cada posición j, tratamos de cortar en j+1 y sumamos el valor del corte + lo que queda
            for (int j = 0; j < i; j++) {
            	// precios[j] es el valor de un corte de longitud j+1
                // dp[i - j - 1] es el mejor valor que se puede obtener del resto de la varilla
                maxValor = Math.max(maxValor, precios[j] + dp[i - j - 1]);
            }
            //guardamos el mejor valor para una varilla de longitud i
            dp[i] = maxValor;
        }
        return dp[longitud];         // el resultado para la longitud total está en dp[longitud]
    }

    public static void main(String[] args) { //metodo principal donde se ejecutara todo el codigo
        int[] precios = {3, 7, 1, 3, 9}; // lista de precios
        int longitud = precios.length; // la longitud de la varilla es igual al tamaño de arreglo de precios
        System.out.println("Valor máximo obtenido: " + obtenerMaximoValor(precios, longitud)); // imrpime el valor maximo que se puede obtener
    }
}
