package act2;

import javax.swing.*;
import java.awt.*; // la primera libreria y la segunda para ejecutar el arbol de pitagoras
import java.util.Scanner; //usamos esta libreria para ingresar datos 
import java.util.stream.IntStream; //usamos esto para ejecutar el menu, sustituyendo el for que originalmente debia estar

//clase que dibuja el arbol de pitagoras usando recursión y el JPanel
public class ArbolDepitagoras extends JPanel {

    private int profundidad; // progundidad del arbol como entero (niveles de recursión)

    //metodo constructor que inicializa la profundidad del arbol
    public ArbolDepitagoras(int profundidad) {
        this.profundidad = profundidad;
        //se establece el tamaño para el Jpanel donde se dibujará el arbol
        setPreferredSize(new Dimension(800, 700));
    }

    @Override
    //metodo que se llama automaticamente para pintar el componente grafo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Se convierte a Graphics2D para permitir transformaciones
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK); // fondo negro
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.GREEN); //color de las lineas que se trazará del arbol de color verde
        trazaArbol(g2d, 350, 600, 100, -90, profundidad); // llama a la función recursiva para dibujar el arbol desde el punto inicial
    }

    //función recursiva para dibujar el arbol de pitagoras
    private void trazaArbol(Graphics2D g, int x, int y, int lado, double angulo, int nivel) {
        if (nivel == 0 || lado < 2) return; //conficion de parada, si el nivel es 0 o el lado es muy pequeño
        //cálculo del punto final de la línea con trigonometría
        int x2 = x + (int) (lado * Math.cos(Math.toRadians(angulo)));
        int y2 = y + (int) (lado * Math.sin(Math.toRadians(angulo)));
        //dibuja la línea desde el punto inicial al punto final
        g.drawLine(x, y, x2, y2);
        // para reducir el tamaño de la siguiente rama
        int nuevoLado = (int) (lado * 0.7);
        trazaArbol(g, x2, y2, nuevoLado, angulo - 45, nivel - 1); //rama izquierda y la que esta debajo derecha
        trazaArbol(g, x2, y2, nuevoLado, angulo + 45, nivel - 1);
    }

    public static void main(String[] args) { //metodo principal del programa que se ejecutará todo
        Scanner scanner = new Scanner(System.in); // para ingresar datos
        int opcion;

        do {
        	//en un arreglo están las opciones disponibles, luego se iterará cada una de ellas a tal punto de mostrar un menu, gracias a una función, luego se tiene que ingresar una opcion mediante un entero
            String[] opciones = {"Generar Árbol con nivel 6", "Generar Árbol con nivel 8", "Generar Árbol con nivel 10", "Salir"};
            System.out.println("=====================================");
            System.out.println("     MENÚ ÁRBOL DE PITÁGORAS         ");
            System.out.println("=====================================");
            IntStream.range(0, opciones.length).forEach(i -> System.out.println((i + 1) + ". " + opciones[i]));
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt(); //para poder ingresar una opcion disponible, en caso que se ingrese otro valor no disponible, el programa arrojará un error, hasta que el usuario ingrese un 4 se termina el programa
            int nivel = 0;

            switch (opcion) {
                case 1:
                    nivel = 6;
                    break;
                case 2:
                    nivel = 8;
                    break;
                case 3:
                    nivel = 10;
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.err.println("Opción no válida. Intente nuevamente.");
                    continue;
            }

            //en caso que si se seleccionó una opcion valida para generar arbol
            if (opcion >= 1 && opcion <= 3) {
                int finalNivel = nivel;
                // Se utiliza Swing para abrir una ventana con el árbol dibujado        
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Árbol de Pitágoras - Nivel " + finalNivel);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.add(new ArbolDepitagoras(finalNivel)); // Se agrega el panel con el dibujo
                    frame.pack();// Ajusta el tamaño de la ventana
                    frame.setLocationRelativeTo(null); // para centrar la ventana y que no se desvie
                    frame.setVisible(true); // para mostrar la ventana, este codigo es escencial para mostrar el arbol
                });
            }

        } while (opcion != 4); // se termina de iterar si se inserta un 4

        scanner.close(); // se cierra el scanner
    }
}
