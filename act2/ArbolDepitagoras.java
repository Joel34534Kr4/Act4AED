package act2;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ArbolDepitagoras extends JPanel {

    private int profundidad;

    public ArbolDepitagoras(int profundidad) {
        this.profundidad = profundidad;
        setPreferredSize(new Dimension(800, 700));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Se convierte a Graphics2D para permitir transformaciones
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.GREEN);
        trazaArbol(g2d, 350, 600, 100, -90, profundidad);
    }

    private void trazaArbol(Graphics2D g, int x, int y, int lado, double angulo, int nivel) {
        if (nivel == 0 || lado < 2) return;

        int x2 = x + (int) (lado * Math.cos(Math.toRadians(angulo)));
        int y2 = y + (int) (lado * Math.sin(Math.toRadians(angulo)));

        g.drawLine(x, y, x2, y2);

        int nuevoLado = (int) (lado * 0.7);
        trazaArbol(g, x2, y2, nuevoLado, angulo - 45, nivel - 1);
        trazaArbol(g, x2, y2, nuevoLado, angulo + 45, nivel - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            String[] opciones = {"Generar Árbol con nivel 6", "Generar Árbol con nivel 8", "Generar Árbol con nivel 10", "Salir"};
            System.out.println("=====================================");
            System.out.println("     MENÚ ÁRBOL DE PITÁGORAS         ");
            System.out.println("=====================================");
            IntStream.range(0, opciones.length).forEach(i -> System.out.println((i + 1) + ". " + opciones[i]));
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
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

            if (opcion >= 1 && opcion <= 3) {
                int finalNivel = nivel;
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Árbol de Pitágoras - Nivel " + finalNivel);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.add(new ArbolDepitagoras(finalNivel));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });
            }

        } while (opcion != 4);

        scanner.close();
    }
}