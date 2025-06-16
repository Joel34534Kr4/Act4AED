package Act5;

public class CorteVarillaDP {
    // Método con programación dinámica
    static int obtenerMaximoValor(int[] precios, int longitud) {
        int[] dp = new int[longitud + 1];

        for (int i = 1; i <= longitud; i++) {
            int maxValor = -1;
            for (int j = 0; j < i; j++) {
                maxValor = Math.max(maxValor, precios[j] + dp[i - j - 1]);
            }
            dp[i] = maxValor;
        }
        return dp[longitud];
    }

    public static void main(String[] args) {
        int[] precios = {3, 7, 1, 3, 9};
        int longitud = precios.length;

        System.out.println("Valor máximo obtenido: " + obtenerMaximoValor(precios, longitud));
    }
}
