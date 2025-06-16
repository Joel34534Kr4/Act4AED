package act1;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.InputMismatchException;

public class Hanoi {
    // Método recursivo para resolver el problema de las Torres de Hanoi
    public static void torresHanoi(int discos, int torreOrigen, int torreAuxiliar, int torreDestino) {
        // Caso base: si solo hay un disco, se mueve directamente de la torre origen a la torre destino
        if (discos == 1) {
            System.out.println("Mover disco de torre " + torreOrigen + " a torre " + torreDestino);
        } else {
            // Paso 1: mover n-1 discos de la torre origen a la torre auxiliar
            torresHanoi(discos - 1, torreOrigen, torreDestino, torreAuxiliar);

            // Paso 2: mover el disco restante (el más grande) a la torre destino
            System.out.println("Mover disco de torre " + torreOrigen + " a torre " + torreDestino);

            // Paso 3: mover los n-1 discos desde la torre auxiliar a la torre destino
            torresHanoi(discos - 1, torreAuxiliar, torreOrigen, torreDestino);
        }
    }
    public static void mostrarMenu() { //menu de opciones, arreglo donde indicara las opciones, luego se iterara gracias a una funcion asi imprimiendo el menu
    	String[] opciones = {"Resolver torres de Hanoi", "Salir" };
        System.out.println("=====================================");
        System.out.println("     TORRES DE HANOI - RECURSIVO     ");
        System.out.println("=====================================");
   	    IntStream.range(0, opciones.length).forEach(i -> System.out.println((i +1 ) + ". " + opciones[i]));
   	    System.out.print("Seleccione una opcion: ");
    }
    
    public static void main(String[] args) {
    	Scanner entrada = new Scanner(System.in); // instanciamos el objeto entrada para poder ingresar los datos
    	int opcion;
    	do {
    		mostrarMenu();
    		try {
    			opcion = entrada.nextInt();
        		
        		switch(opcion) {
        		    case 1:
        		    	System.out.println("Ingrese el número de discos: ");
        		    	int discos = entrada.nextInt();
        		    	if(discos<= 0) { // por logica, el numero de discos debe ser si o si mayor que cero
        		    		System.err.println("El numero de discos debe ser mayor que cero"); 
        		    	} else {
        		    		System.out.println("\nREsolviendo Torres de Hanoi con " + discos + "discos: \n");
        		    		torresHanoi(discos, 1, 2, 3);
        		    		System.out.println("\nSolución completada");
        		    	}
        		    	break;
        		    case 2:
        		    	System.out.println("Saliendo del programa."); //al insertar 2, el programa termina por do-while;
        		    	break;
        		    default:
        		    	System.err.println("Error");
        		    	}   		    		
        		}catch(InputMismatchException e) {
        			System.out.println("Entrada invalida. Porfavor ingrese un numero"); // en tal caso arrojará una excepcion, asi para que siga corriendo el programa
        			entrada.next();
        			opcion =-1;
        		}
        		
        	}while(opcion != 2);
    	entrada.close(); // cierra la funcion para ingresar datos
    	}
    	
}

